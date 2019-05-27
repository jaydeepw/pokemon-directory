package com.github.jaydeepw.pokemondirectory.models.datasource.network

import com.github.jaydeepw.pokemondirectory.Utils
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.network.client.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainNetworkModel: MainContractInterface.Model {

    @Inject
    lateinit var apiClient: ApiInterface

    override fun getData(callback: PokemonsCallback) {

        val list = apiClient.getPokemons()
        list.enqueue(object : Callback<MutableList<Pokemon>> {

            override fun onResponse(call: Call<MutableList<Pokemon>>, response: Response<MutableList<Pokemon>>) {
                if (response.isSuccessful) {
                    var body = response.body()
                    if (body == null) {
                        body = mutableListOf()
                    }

                    callback.onSuccess(body)
                } else {
                    callback.onNotSuccess(Utils.Companion.parseNetworkCode(response.code()))
                }
            }

            override fun onFailure(call: Call<MutableList<Pokemon>>, t: Throwable) {
                callback.onFailure(t.message ?: t.localizedMessage)
            }
        })
    }
}