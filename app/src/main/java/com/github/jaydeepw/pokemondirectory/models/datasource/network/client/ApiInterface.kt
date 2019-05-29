package com.github.jaydeepw.pokemondirectory.models.datasource.network.client

import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/v2/pokemon")
    fun getPokemons(): Call<Page>
}