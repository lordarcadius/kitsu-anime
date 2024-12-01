@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.vipuljha.kitsuanime.features.anime.presentation.widgets

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.vipuljha.kitsuanime.core.utils.NetworkResponse
import com.vipuljha.kitsuanime.core.widgets.LoadingIndicator
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData
import com.vipuljha.kitsuanime.features.anime.presentation.viewmodels.AnimeDetailsViewModel

@Composable
fun SharedTransitionScope.AnimeDetailsScreen(
    id: String,
    coverImage: String,
    viewModel: AnimeDetailsViewModel = hiltViewModel(),
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LaunchedEffect(key1 = true) {
        viewModel.fetchAnimeDetails(id)
    }
    val anime by viewModel.animeDetails.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding() + 10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                AsyncImage(
                    model = coverImage,
                    contentDescription = "Anime cover image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .sharedElement(
                            rememberSharedContentState(key = id),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                )
            }

            item {
                when (anime) {
                    is NetworkResponse.Loading -> {}
                    is NetworkResponse.Success -> AnimeDetails(
                        data = (anime as NetworkResponse.Success<AnimeData>).data,
                    )

                    is NetworkResponse.Error -> Text(text = (anime as NetworkResponse.Error).message)
                }
            }

        }

        if (anime is NetworkResponse.Loading) {
            LoadingIndicator()
        }
    }
}

@Composable
private fun AnimeDetails(
    data: AnimeData,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = data.attributes.canonicalTitle ?: "Unknown",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.attributes.startDate?.split("-")?.first() ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = " - ",
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )

                Text(
                    text = data.attributes.averageRating ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = data.attributes.synopsis ?: "")
        }

    }
}