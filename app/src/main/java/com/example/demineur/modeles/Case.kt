package com.example.demineur.modeles

data class Case(
    val bomb: Boolean,
    val adjacentBombs: Int,
    val selected: Boolean
)