package com.github.jaydeepw.pokemondirectory.models.dataclasses

import androidx.room.PrimaryKey

data class Pokemon2(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val title: String = "",
        val userId: Int = 0
)