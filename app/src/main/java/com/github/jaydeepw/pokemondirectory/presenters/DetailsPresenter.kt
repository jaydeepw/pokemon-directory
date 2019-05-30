package com.github.jaydeepw.pokemondirectory.presenters

import android.util.Log
import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.db.PokemonRepository
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.DetailsViewCallback

class DetailsPresenter(
    var view: DetailsContractInterface.View?,
    var pokemonRepository: PokemonRepository
): BasePresenter, DetailsContractInterface.Presenter {

    override fun onGetDetails(id: Int) {
        view?.showProgress()
        pokemonRepository.getDetails(id, object : DetailsViewCallback {
            override fun onNotSuccess(messageResId: Int) {
                view?.hideProgress()
                view?.showError(messageResId)
            }

            override fun onFailure(message: String) {
                view?.hideProgress()
                view?.showError(message)
            }

            override fun onSuccess(pokemon: Pokemon) {
                Log.d("MainPresenter", "pokemon.height ${pokemon.height}")
                view?.hideProgress()
                view?.showDetails(pokemon)
            }
        })
    }
}