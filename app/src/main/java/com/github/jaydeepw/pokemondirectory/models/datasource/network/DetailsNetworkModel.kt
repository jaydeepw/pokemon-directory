package com.github.jaydeepw.pokemondirectory.models.datasource.network

import android.util.Log
import com.github.jaydeepw.pokemondirectory.Utils
import com.github.jaydeepw.pokemondirectory.contracts.DetailsContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.DetailsSourceCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.network.client.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailsNetworkModel: DetailsContractInterface.Model {

    @Inject
    lateinit var apiClient: ApiInterface

    override fun getDetails(id: Int, callback: DetailsSourceCallback) {
        val call = apiClient.getPokemonDetails(id)
        call.enqueue(object : Callback<Pokemon> {

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    var body = response.body()
                    if (body == null) {
                        body = Pokemon()
                    }

                    Log.d(TAG, "Response received for details")

                    callback.onSuccess(body)
                } else {
                    callback.onNotSuccess(Utils.Companion.parseNetworkCode(response.code()))
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                callback.onFailure(t.message ?: t.localizedMessage)
            }
        })
    }

    companion object {
        val TAG = DetailsNetworkModel::class.simpleName
    }
}