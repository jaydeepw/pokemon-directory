package com.github.jaydeepw.pokemondirectory.models.datasource.network.client

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/albums")
    fun getPokemons(): Call<MutableList<Pokemon>>
}