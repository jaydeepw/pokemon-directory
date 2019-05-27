package com.github.jaydeepw.pokemondirectory.models.datasource

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

interface MainDataSource {
    fun getData(callback: PokemonsCallback)
}

interface PokemonsCallback {
    fun onSuccess(list : MutableList<Pokemon>)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}
