package com.geotec.geologger.presentation.ui.composables

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demineur.R
import com.example.demineur.modeles.Case
import com.example.demineur.modeles.Grille
import kotlinx.coroutines.launch

private const val GRID_SIZE = 9
private const val BOMB_COUNT = 6


fun generateBaseGrid(firstClickCoordinates: Pair<Int, Int>): Grille {

    val bombCoordinates = mutableSetOf<Pair<Int, Int>>()

    val bombCountMap = mutableMapOf<Pair<Int, Int>, Int>()

    val adjacentClick = firstClickCoordinates.adjacents(self = true)

    while (bombCoordinates.size < BOMB_COUNT) {

        val randomCoordinates = (1..GRID_SIZE).random() to (1..GRID_SIZE).random()

        if (randomCoordinates in adjacentClick) {
            continue
        } else {
            val added = bombCoordinates.add(randomCoordinates)
            if (added) {
                randomCoordinates.adjacents(self = false).forEach { element ->
                    bombCountMap[element] = (bombCountMap[element] ?: 0) + 1
                }
            }
        }
    }

    return Grille(
        cells = (1..GRID_SIZE).flatMap { i ->
            (1..GRID_SIZE).map { j ->
                val cellCoordinates = i to j
                Case(
                    bomb = (cellCoordinates) in bombCoordinates,
                    adjacentBombs = bombCountMap[cellCoordinates] ?: 0,
                    selected = false,
                    coordonnees = cellCoordinates
                )
            }
        },
        generated = true
    )
}


fun generateEmptyGrid() = Grille(
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
fun GrilleUI(modifier: Modifier = Modifier) {
    var grid by remember {
        mutableStateOf(generateEmptyGrid())
    }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_SIZE)
        ) {
            items(grid.cells) {
                Cell(Modifier.aspectRatio(1f), case = it) {
                    if (!grid.generated) {
                        grid = generateBaseGrid(it)
                    }
                    coroutineScope.launch {
                        grid = grid.onCellSelected(it)
                    }
                }
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

private fun Pair<Int, Int>.adjacents(self: Boolean): List<Pair<Int, Int>> {
    val (i, j) = first to second
    return listOfNotNull(
        (i - 1) to (j - 1),
        i to (j - 1),
        (i + 1) to (j - 1),
        (i - 1) to j,
        if (self) i to j else null,
        (i + 1) to j,
        (i - 1) to (j + 1),
        i to (j + 1),
        (i + 1) to (j + 1),
    )
}

private fun Grille.adjacents(case: Case, self: Boolean): List<Case> {
    val adjacents = case.coordonnees.adjacents(self)
    return cells.filter { it.coordonnees in adjacents }
}

private suspend fun Grille.onCellSelected(clickCoordinates: Pair<Int, Int>): Grille {
    val cell = this.cells.first { it.coordonnees == clickCoordinates }
    return if (cell.adjacentBombs == 0) {
        val selectedCells = findRecursiveAdjacentCells(cell, mutableListOf())
        val newCells = cells.toMutableList().apply {
            selectedCells.forEach {
                val cellIndex = cells.indexOf(it)
                set(cellIndex, it.copy(selected = true))
            }
        }
        this.copy(cells = newCells)
    } else {
        val cellIndex = cells.indexOf(cell)
        this.copy(
            cells = cells.toMutableList().apply { set(cellIndex, cell.copy(selected = true)) }
        )
    }
}

private fun Grille.findRecursiveAdjacentCells(cell: Case, selected: MutableList<Pair<Int, Int>>): List<Case> {
    val adjacents = adjacents(cell, true).filter {
        it.coordonnees !in selected
    }.onEach {
        selected.add(it.coordonnees)
    }
    return adjacents + adjacents.flatMap {
        if (it.adjacentBombs > 0) {
            emptyList()
        } else {
            findRecursiveAdjacentCells(it, selected)
        }
    }.distinct()
}
