package com.example.demineur.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demineur.R
import com.example.demineur.modeles.Case
import com.example.demineur.modeles.Grille

private const val GRID_SIZE = 9
private const val BOMB_COUNT = 10
private var firstclick = false


fun generateGrid() : Grille {

    val bombCoordinates = mutableSetOf<Pair<Int, Int>>()

    val bombCountMap = mutableMapOf<Pair<Int, Int>, Int>()


    while (bombCoordinates.size < BOMB_COUNT) {


        val coordonnees = (1..GRID_SIZE).random() to (1..GRID_SIZE).random()

        val resultat = bombCoordinates.add(coordonnees)

        if (resultat) {

            val i = coordonnees.first
            val j = coordonnees.second
            val adjacentCoordinates = listOf(
                (i - 1) to (j - 1),
                i to (j - 1),
                (i + 1) to (j - 1),
                (i - 1) to j,
                (i + 1) to j,
                (i - 1) to (j + 1),
                i to (j + 1),
                (i + 1) to (j + 1),
            )

            adjacentCoordinates.forEach { element ->
                bombCountMap[element] = (bombCountMap[element] ?: 0) + 1
            }
        }
    }

    return Grille(
        cells = (1..GRID_SIZE).flatMap { i ->
            (1..GRID_SIZE).map { j ->
                Case(
                    bomb = (i to j) in bombCoordinates,
                    adjacentBombs = bombCountMap[i to j] ?: 0,
                    selected = false,
                    coordonnees = i to j
                )
            }
        },
        generated = true
    )
}



    fun generateDefaultGrid() = Grille(

        cells = (1..GRID_SIZE).flatMap { i ->
            (1..GRID_SIZE).map { j ->
                Case(
                    bomb = false,
                    adjacentBombs = 0,
                    selected = false,
                    coordonnees = i to j
                )
            }
        },
        generated = false

    )


    @Composable
    fun Grille(modifier: Modifier = Modifier) {
        val cases by generateDefaultGrid().flatten()

        Surface(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(GRID_SIZE)
            ) {
                items(cases) {
                    Cell(Modifier.aspectRatio(1f), case = it){}
                }
            }
        }
    }

    @Composable
    fun Cell(
        modifier: Modifier = Modifier,
        case: Case,
        onClick: (Pair<Int, Int>) -> Unit

    ) {
        Box(
            modifier
                .border(1.dp, Color.Black)
                .clickable {
                    onClick(case.coordonnees)

                }
                .background(
                    if (case.selected) {
                        if (case.bomb) {
                            Color.Red
                        } else {
                            Color.LightGray
                        }
                    } else {
                        Color.Transparent
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (case.selected) {
                if (case.bomb) {
                    Icon(painter = painterResource(id = R.drawable.bomb), contentDescription = null)
                } else {
                    if (case.adjacentBombs > 0) {
                        Text(text = case.adjacentBombs.toString())
                    }
                }
            }

        }
    }