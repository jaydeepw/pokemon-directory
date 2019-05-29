package com.github.jaydeepw.pokemondirectory.models.datasource.network

import android.util.Log
import com.github.jaydeepw.pokemondirectory.Utils
import com.github.jaydeepw.pokemondirectory.contracts.MainContractInterface
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.network.client.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainNetworkModel: MainContractInterface.Model {

    companion object {
        val TAG = MainNetworkModel::class.simpleName
    }

    @Inject
    lateinit var apiClient: ApiInterface

    override fun getData(callback: PokemonsCallback) {

        val list = apiClient.getPokemons()
        list.enqueue(object : Callback<Page> {

            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                if (response.isSuccessful) {
                    var body = response.body()
                    if (body == null) {
                        body = Page()
                    }

                    Log.d(TAG, "Response received")

                    // callback.onSuccess(body)
                } else {
                    callback.onNotSuccess(Utils.Companion.parseNetworkCode(response.code()))
                }
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                callback.onFailure(t.message ?: t.localizedMessage)
            }
        })
    }
}