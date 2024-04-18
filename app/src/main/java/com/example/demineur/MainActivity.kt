package com.example.demineur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.demineur.ui.theme.DemineurTheme
import com.geotec.geologger.presentation.ui.composables.GrilleUI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemineurTheme {
                GrilleUI()
            }
        }
    }
}




