package com.github.jaydeepw.pokemondirectory.models.datasource.mock

import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback

class MainMockModel: MainContractInterface.Model {
    override fun getData(callback: PokemonsCallback) {
        val pokemon1 = Pokemon()
        val pokemon2 = Pokemon()

        callback.onSuccess(mutableListOf(pokemon1, pokemon2))
    }
}