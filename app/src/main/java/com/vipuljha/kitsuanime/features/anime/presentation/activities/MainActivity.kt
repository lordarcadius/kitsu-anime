@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.vipuljha.kitsuanime.features.anime.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vipuljha.kitsuanime.core.theme.KitsuAnimeTheme
import com.vipuljha.kitsuanime.features.anime.presentation.widgets.AnimeDetailsScreen
import com.vipuljha.kitsuanime.features.anime.presentation.widgets.TrendingAnimeListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KitsuAnimeTheme {
                val navController = rememberNavController()
                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeListScreen(
                                onClick = { cover, id ->
                                    navController.navigate(
                                        AnimeDetailsRoute(id = id, coverImage = cover)
                                    )
                                },
                                animatedVisibilityScope = this
                            )
                        }

                        composable<AnimeDetailsRoute> {
                            val args = it.toRoute<AnimeDetailsRoute>()
                            AnimeDetailsScreen(
                                id = args.id,
                                coverImage = args.coverImage,
                                animatedVisibilityScope = this
                            )
                        }
                    }
                }

            }
        }
    }
}

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeDetailsRoute(val id: String, val coverImage: String)