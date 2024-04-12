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
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import com.example.demineur.modeles.Case

private const val GRID_SIZE = 9
private const val BOMB_COUNT = 10

fun generateGrid(bombCount: Int): List<List<Case>> {
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


    val casesCoordinates = (1..GRID_SIZE).map { i ->
        (1..GRID_SIZE).map { j ->
            Case(
                bomb = (i to j) in bombCoordinates,
                adjacentBombs = bombCountMap[i to j] ?: 0,
                selected = false
            )
        }
    }
    return casesCoordinates


}


@Composable
fun Grille(modifier: Modifier = Modifier) {
    val cases by remember {
        mutableStateOf(generateGrid(bombCount = BOMB_COUNT).flatten())
    }

    Surface(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_SIZE)
        ) {
            items(cases) {
                var clicked by remember {
                    mutableStateOf(false)
                }
                Cell(Modifier.aspectRatio(1f), case = it.copy(selected = clicked)) {
                    clicked = true
                }
            }
        }
    }
}

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    case: Case,
    onClick: () -> Unit

) {
    Box(
        modifier
            .border(1.dp, Color.Black)
            .clickable {
                onClick()
            }
            .background(
                if (case.selected && case.bomb) {
                    Color.Red
                } else if (case.selected) {
                    Color.Transparent
                } else {
                    Color.LightGray
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (case.selected) {
            Text(text = case.adjacentBombs.toString())
        }
    }
}