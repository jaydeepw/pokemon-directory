package com.github.jaydeepw.pokemondirectory.db

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.room.Room
import com.github.jaydeepw.pokemondirectory.Constants
import com.github.jaydeepw.pokemondirectory.R
import com.github.jaydeepw.pokemondirectory.db.dao.PokemonDao
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import com.github.jaydeepw.pokemondirectory.models.datasource.PokemonsCallback
import com.github.jaydeepw.pokemondirectory.models.datasource.network.MainNetworkModel
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class PokemonRepository internal constructor(application: Application) {

    private val pokemonDao: PokemonDao

    @Inject
    lateinit var mainModel: MainNetworkModel

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
        mainModel.getData(object : PokemonsCallback {
            override fun onSuccess(list: MutableList<Pokemon>) {
                Log.d("MainPresenter", "network.list.size ${list.size}")
                insertAll(list)

                // notify the subscriber about this event.
                // in our case, it will be the UI.
                EventBus.getDefault().post(list)
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

        override fun onPostExecute(result: List<Pokemon>?) {
            super.onPostExecute(result)

            try {
                if (result != null) {
                    callback.onSuccess(result as MutableList<Pokemon>)
                } else {
                    callback.onNotSuccess(R.string.msg_failure_get_lists_db)
                }
            } catch (e: Exception) {
                callback.onFailure(e.message ?: e.localizedMessage)
            }
        }
    }
}