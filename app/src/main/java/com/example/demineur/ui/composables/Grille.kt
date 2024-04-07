package com.example.demineur.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.demineur.modeles.Case

@Composable
fun Grille(modifier: Modifier) {
    val cases by remember(Case()) {mutableListOf(81)}


    Surface(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(9)) {
            items(cases){
                Box(
                    Modifier
                        .aspectRatio(1f)
                        .border(1.dp, Color.Black)
                        .clickable { it.compteur++ }
                ){
                    Text(text = it.compteur.toString())

                }

        }
            
        }
    }
}