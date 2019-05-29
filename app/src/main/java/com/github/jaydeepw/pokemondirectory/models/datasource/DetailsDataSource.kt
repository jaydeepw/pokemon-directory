package com.github.jaydeepw.pokemondirectory.models.datasource

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

interface DetailsDataSource {
    fun getData(callback: DetailsSourceCallback)
}

interface DetailsViewCallback {
    fun onSuccess(pokemon: Pokemon)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}

interface DetailsSourceCallback {
    fun onSuccess(pokemon: Pokemon)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}
