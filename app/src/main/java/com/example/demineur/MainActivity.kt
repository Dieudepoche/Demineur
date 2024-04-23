package com.example.demineur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.demineur.modeles.Case
import com.example.demineur.modeles.Grille
import com.example.demineur.ui.composables.GrilleUI
import com.example.demineur.ui.theme.DemineurTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            var grid by remember {
                mutableStateOf(generateEmptyGrid())
            }

            DemineurTheme {
                Column {
                    GrilleUI(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.7f), cells = grid.cells
                    ) {
                        if (!grid.generated) {
                            grid = generateBaseGrid(it)
                        }
                        coroutineScope.launch {
                            grid.computeSelectedCells(it) { coordinates ->
                                grid = grid.selectCell(coordinates)
                            }
                        }
                    }
                    Button(onClick = { grid = generateEmptyGrid() }) {
                        Text("RESET")
                    }
                }
            }
        }
    }
}


private const val GRID_SIZE = 15
private const val BOMB_COUNT = 10


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

fun Grille.selectCell(coordinates: Pair<Int, Int>): Grille = copy(
    cells = cells.toMutableList()
        .apply {
            cells.find { it.coordonnees == coordinates }?.let {
                set(cells.indexOf(it), it.copy(selected = true))
            }
        }
)

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

private suspend fun Grille.computeSelectedCells(
    clickCoordinates: Pair<Int, Int>,
    onCellSelected: (Pair<Int, Int>) -> Unit
) {
    val cell = cells.first { it.coordonnees == clickCoordinates }
    if (cell.adjacentBombs == 0) {
        expandSelection(cell.coordonnees, cells, mutableSetOf()) {
            onCellSelected(it)
        }
    } else {
        onCellSelected(cell.coordonnees)
    }
}

private suspend fun expandSelection(
    cell: Pair<Int, Int>,
    grid: List<Case>,
    checked: MutableSet<Pair<Int, Int>>,
    onCellSelected: (Pair<Int, Int>) -> Unit,
) {
    println("Expanding selection from $cell")
    onCellSelected(cell)
    checked.add(cell)
    delay(100)
    cell.adjacents(false).forEach { adjacent ->
        grid.find { it.coordonnees == adjacent }?.let {
            if (it.adjacentBombs == 0 && it.coordonnees !in checked) {
                expandSelection(adjacent, grid, checked, onCellSelected)
            }
        }
    }
}
