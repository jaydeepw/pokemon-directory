package com.github.jaydeepw.pokemondirectory.models.datasource

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

interface MainDataSource {
    fun getData(callback: PokemonsSourceCallback)
}

interface PokemonsCallback {
    fun onSuccess(list : MutableList<Pokemon>)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}

interface PokemonsSourceCallback {
    fun onSuccess(page : Page)
    fun onNotSuccess(messageResId: Int)
    fun onFailure(message: String)
}
