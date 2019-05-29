package com.github.jaydeepw.pokemondirectory.contracts

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.MainDataSource
import com.github.jaydeepw.pokemondirectory.views.BaseView

interface MainContractInterface {

    interface View: BaseView {
        fun showData(list: ArrayList<Pokemon>?)
        fun showError(messageResId: Int)
        fun showDetails(pokemon: Pokemon?)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onGetData()
        fun onItemClick(pokemon: Pokemon?)
    }

    interface Model : MainDataSource
}