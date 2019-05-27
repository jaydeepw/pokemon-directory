package com.github.jaydeepw.pokemondirectory.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.jaydeepw.pokemondirectory.db.dao.PokemonDao
import com.github.jaydeepw.pokemondirectory.models.dataclasses.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema =false)
//@TypeConverters(CarConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}