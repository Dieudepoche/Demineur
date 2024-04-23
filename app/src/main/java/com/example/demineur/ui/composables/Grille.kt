package com.example.demineur.ui.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demineur.R
import com.example.demineur.modeles.Case


@Composable
fun GrilleUI(
    modifier: Modifier = Modifier,
    cells: List<Case>,
    onCellClick: (Pair<Int, Int>) -> Unit
) {
    val size by remember(cells) {
        mutableIntStateOf(cells.maxOf { it.coordonnees.first })
    }

    Surface(modifier) {
        LazyVerticalGrid(columns = GridCells.Fixed(size)) {
            items(cells) {
                Cell(Modifier.aspectRatio(1f), case = it) {
                    onCellClick(it)
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
    val color by animateColorAsState(
        targetValue = if (case.selected) {
            if (case.bomb) {
                Color.Red
            } else {
                Color.LightGray
            }
        } else {
            Color.Transparent
        },
        animationSpec = tween(durationMillis = 1000)
    )

    val alpha by animateFloatAsState(
        targetValue = if (case.selected) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    val radius by animateFloatAsState(
        targetValue = if (case.selected) 1.5f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        )
    )

    val textRadius by animateFloatAsState(
        targetValue = if (case.selected) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    val textRotation by animateFloatAsState(
        targetValue = if (case.selected) 1080f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )
    IndexedValue(value = 21)

    val flagIndex = listOf()
    val flagAnim by animateIntAsState(targetValue = )
    Box(
        modifier
            .border(1.dp, Color.Black)

            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        this.awaitRelease() {

                        }

                    },
                    onTap = {
                        onClick(case.coordonnees)
                    }
                )
            }
            .clipToBounds(),
        contentAlignment = Alignment.Center
    ) {
        if (case.selected) {
            if (case.bomb) {
                Icon(
                    modifier = Modifier.alpha(alpha),
                    painter = painterResource(id = R.drawable.bomb),
                    contentDescription = null
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(radius)
                        .background(color, CircleShape)
//                        .rotate(textRotation)
                ) {
                    if (case.adjacentBombs > 0) {
                        Text(
                            modifier = Modifier
                                .scale(textRadius)
                                .alpha(alpha),
                            text = case.adjacentBombs.toString()
                        )
                    }
                }
            }
        }

    }
}