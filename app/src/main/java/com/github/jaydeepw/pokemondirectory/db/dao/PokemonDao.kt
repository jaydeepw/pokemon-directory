package com.github.jaydeepw.pokemondirectory.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

@Dao
interface PokemonDao {
    @get:Query("SELECT * FROM pokemons")
    val all: List<Pokemon>

    @Insert
    fun insertAll(pokemons: MutableList<Pokemon>)

    @Insert
    fun insert(pokemons: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)

    @Delete
    fun deleteAll(pokemons: MutableList<Pokemon>)

    @Query("DELETE from pokemons")
    fun deleteAll()
}