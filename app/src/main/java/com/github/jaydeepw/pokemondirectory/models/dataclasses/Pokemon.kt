package com.github.jaydeepw.pokemondirectory.models.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class Pokemon(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   val name: String = "",
                   val url: String = "")