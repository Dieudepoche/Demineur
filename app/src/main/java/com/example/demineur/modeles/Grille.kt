package com.example.demineur.modeles

data class Grille (
    val cells : List<Case>,
    var generated : Boolean,
)