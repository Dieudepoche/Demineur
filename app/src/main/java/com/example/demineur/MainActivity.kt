package com.example.demineur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.demineur.ui.composables.GrilleUI
import com.example.demineur.ui.theme.DemineurTheme

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




