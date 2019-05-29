package com.github.jaydeepw.pokemondirectory.contracts

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.MainDataSource
import com.github.jaydeepw.pokemondirectory.views.BaseView

interface DetailsContractInterface {

    interface View: BaseView {
        fun showError(messageResId: Int)
        fun showDetails(pokemon: Pokemon?)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onGetDetails()
        fun onItemClick(pokemon: Pokemon?)
    }

    interface Model : MainDataSource
}