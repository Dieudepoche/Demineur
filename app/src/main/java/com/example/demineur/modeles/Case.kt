package com.example.demineur.modeles

data class Case(
    val bomb: Boolean,
    var adjacentBombs: Int,
    val selected: Boolean,
    var coordonnees : Pair<Int, Int>
)