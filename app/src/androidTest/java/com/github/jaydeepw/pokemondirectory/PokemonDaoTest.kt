package com.github.jaydeepw.pokemondirectory

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.jaydeepw.pokemondirectory.db.AppDatabase
import com.github.jaydeepw.pokemondirectory.db.dao.PokemonDao
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {
    lateinit var pokemonDao: PokemonDao
    lateinit var database: AppDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        pokemonDao = database.pokemonDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertedAndRetrievedUsersMatch() {
        val pokemon1Expected = Pokemon(1, "My Title", "url12")
        val pokemon2Expected = Pokemon()
        val users = mutableListOf(pokemon1Expected, pokemon2Expected)
        pokemonDao.deleteAll()
        pokemonDao.insertAll(users)

        val allUsers = pokemonDao.all
        val pokemon1Actual = allUsers[0]
        Assert.assertEquals(pokemon1Actual, pokemon1Expected)
    }

    @Test
    fun testInsertedAndRetrievedUsersCountMatch() {
        val pokemon1Expected = Pokemon(1, "My Title", "url1")
        val pokemon2Expected = Pokemon()
        val users = mutableListOf(pokemon1Expected, pokemon2Expected)
        pokemonDao.deleteAll()
        pokemonDao.insertAll(users)

        val allUsers = pokemonDao.all
        Assert.assertEquals(allUsers.size, users.size)
    }

    @Test
    fun testSingleRowDataIntegrity() {
        val pokemon1Expected = Pokemon(21, "My Title", "url1")
        pokemonDao.deleteAll()
        pokemonDao.insert(pokemon1Expected)

        val allUsers = pokemonDao.all
        val pokemon1Actual = allUsers[0]
        Assert.assertEquals(pokemon1Actual.id, pokemon1Expected.id)
        Assert.assertEquals(pokemon1Actual.name, pokemon1Expected.name)
        Assert.assertEquals(pokemon1Actual.url, pokemon1Expected.url)
    }
}