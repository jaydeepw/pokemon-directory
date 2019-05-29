package com.github.jaydeepw.pokemondirectory.models.datasource.mock

import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsSourceCallback

class MainMockModel: MainContractInterface.Model {
    override fun getData(callback: PokemonsSourceCallback) {
        val page = Page()
        val pokemon1 = Pokemon()
        val pokemon2 = Pokemon()
        page.results = ArrayList()
        page.results?.plus(pokemon1)
        page.results?.plus(pokemon2)
        callback.onSuccess(page)
    }

}