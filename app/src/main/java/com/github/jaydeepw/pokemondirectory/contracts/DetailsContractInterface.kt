package com.github.jaydeepw.pokemondirectory.contracts

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.DetailsDataSource
import com.github.jaydeepw.pokemondirectory.views.BaseView

interface DetailsContractInterface {

    interface View: BaseView {
        fun showDetails(pokemon: Pokemon?)
    }

    interface Presenter {
        fun onGetDetails(id: Int)
    }

    interface Model : DetailsDataSource
}