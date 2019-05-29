package com.github.jaydeepw.pokemondirectory.models.datasource.network.client

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/v2/pokemon")
    fun getPokemons(): Call<Page>

    @GET("api/v2/pokemon/{id}")
    fun getPokemonDetails(@Path("id") id :Int ) : Call<Pokemon>
}