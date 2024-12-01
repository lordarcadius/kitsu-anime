package com.vipuljha.kitsuanime.features.anime.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vipuljha.kitsuanime.core.theme.KitsuAnimeTheme
import com.vipuljha.kitsuanime.features.anime.presentation.widgets.TrendingAnimeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KitsuAnimeTheme {
                TrendingAnimeListScreen()
            }
        }
    }
}