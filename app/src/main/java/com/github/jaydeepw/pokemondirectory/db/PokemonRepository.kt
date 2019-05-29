package com.github.jaydeepw.pokemondirectory.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room
import com.github.jaydeepw.pokemondirectory.Constants
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.db.dao.PokemonDao
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Page
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.DetailsSourceCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.DetailsViewCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsSourceCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.network.DetailsNetworkModel
import com.github.jaydeepw.pokemondirectory.models.datasource.network.MainNetworkModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class PokemonRepository internal constructor(application: Application) {

    private val pokemonDao: PokemonDao

    @Inject
    lateinit var mainModel: MainNetworkModel

    @Inject
    lateinit var detailsModel: DetailsNetworkModel

    init {
        val db = Room.databaseBuilder(application,
            AppDatabase::class.java, Constants.Companion.DB_NAME).build()
        pokemonDao = db.pokemonDao()
    }

    private fun insertAll(list: List<Pokemon>) {
        InsertAllAsyncTask(pokemonDao).execute(list)
    }

    fun getAll(callback: PokemonsCallback) {
        RetrieveAllAsyncTask(pokemonDao, callback).execute()

        // update the DB
        mainModel.getData(object : PokemonsSourceCallback {

            override fun onSuccess(page: Page) {
                Log.d("MainPresenter", "network.list.size ${page.results?.size}")
                insertAll(page.results!!)

                // todo save page number here

                // notify the subscriber about this event.
                // in our case, it will be the UI.
                EventBus.getDefault().post(page.results)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onNotSuccess(messageResId: Int) {
                callback.onNotSuccess(messageResId)
            }
        })
    }

    private class InsertAllAsyncTask internal constructor(private val pokemonDao: PokemonDao) :
        AsyncTask<List<Pokemon>, Void, Void>() {

        override fun doInBackground(vararg params: List<Pokemon>): Void? {
            val list = params[0] as MutableList<Pokemon>
            pokemonDao.deleteAll()
            pokemonDao.insertAll(list)
            return null
        }
    }

    private class RetrieveAllAsyncTask internal constructor(
        private val asyncTaskDao: PokemonDao,
        private val callback: PokemonsCallback
    ) :
        AsyncTask<PokemonsCallback, Void, List<Pokemon>>() {
        override fun doInBackground(vararg params: PokemonsCallback): List<Pokemon>? {
            return asyncTaskDao.all
        }

        override fun onPostExecute(page: List<Pokemon>?) {
            super.onPostExecute(page)

            try {
                if (page != null) {
                    callback.onSuccess(page as MutableList<Pokemon>)
                } else {
                    callback.onNotSuccess(R.string.msg_failure_get_lists_db)
                }
            } catch (e: Exception) {
                callback.onFailure(e.message ?: e.localizedMessage)
            }
        }
    }

    fun getDetails(id: Int, callback: DetailsViewCallback) {
        detailsModel.getDetails(id, object : DetailsSourceCallback {
            override fun onSuccess(pokemon: Pokemon) {
                Log.d("PokemonRepository", "pokemon.height ${pokemon.height}")

                callback.onSuccess(pokemon)
                // notify the subscriber about this event.
                // in our case, it will be the UI.
                // EventBus.getDefault().post(page.results)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onNotSuccess(messageResId: Int) {
                callback.onNotSuccess(messageResId)
            }
        })
    }
}