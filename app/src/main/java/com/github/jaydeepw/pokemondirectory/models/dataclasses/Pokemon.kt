package com.github.jaydeepw.pokemondirectory.models.dataclasses

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.URI

@Entity(tableName = "pokemons")
data class Pokemon(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                   val name: String = "",
                   val url: String = "") {

    fun getPokemonId() : Int {
        val uri = URI(url)
        val segments = uri.path.split("/")
        Log.d("Pokemon", uri.path)
        val idStr = segments[segments.size - 2]
        Log.d("Pokemon", segments.toString())
        Log.d("Pokemon", idStr)
        return Integer.parseInt(idStr)
    }
}