package com.vipuljha.kitsuanime.features.anime.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vipuljha.kitsuanime.core.utils.NetworkResponse
import com.vipuljha.kitsuanime.core.widgets.LoadingIndicator
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData
import com.vipuljha.kitsuanime.features.anime.presentation.viewmodels.TrendingAnimeListViewModel

@Composable
fun TrendingAnimeListScreen(
    viewModel: TrendingAnimeListViewModel = hiltViewModel()
) {

    val animeList by viewModel.animeList.collectAsStateWithLifecycle()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (animeList) {
                is NetworkResponse.Loading -> LoadingIndicator()
                is NetworkResponse.Success -> AnimeList((animeList as NetworkResponse.Success<List<AnimeData>>).data)
                is NetworkResponse.Error -> LoadingError((animeList as NetworkResponse.Error).message)
            }
        }
    }
}

@Composable
private fun AnimeList(data: List<AnimeData>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        item {
            Text(
                text = "Trending Anime",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }

        items(data) { anime ->
            AnimeCard(anime = anime, onClick = {})
        }
    }
}

@Composable
private fun LoadingError(message: String) {
    Text(text = message)
}