package com.github.jaydeepw.pokemondirectory.presenters

import android.util.Log
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback

class MainPresenter(
    var view: MainContractInterface.View?,
    private var pokemonRepository: PokemonRepository
): BasePresenter, MainContractInterface.Presenter {
    override fun onItemClick(pokemon: Pokemon?) {
        view?.showDetails(pokemon)
    }

    override fun onGetData() {

        view?.showProgress()
        pokemonRepository.getAll(object : PokemonsCallback {
            override fun onNotSuccess(messageResId: Int) {
                view?.hideProgress()
                view?.showError(messageResId)
            }

            override fun onFailure(message: String) {
                view?.hideProgress()
            }

            override fun onSuccess(list: MutableList<Pokemon>) {
                Log.d("MainPresenter", "DB.list.size ${list.size}")
                view?.hideProgress()
                view?.showData(list as ArrayList<Pokemon>)
            }
        })
    }
}