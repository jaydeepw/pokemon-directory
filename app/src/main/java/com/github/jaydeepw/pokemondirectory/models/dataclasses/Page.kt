package com.github.jaydeepw.pokemondirectory.models.dataclasses

data class Page(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
    var results: List<Pokemon>? = null
)