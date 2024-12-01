@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.vipuljha.kitsuanime.features.anime.presentation.widgets

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData

@Composable
fun SharedTransitionScope.AnimeCard(
    anime: AnimeData,
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Card(onClick = onClick, modifier = Modifier.height(150.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.padding(6.dp)
        ) {
            AsyncImage(
                model = anime.attributes.posterImage.original,
                contentDescription = "${anime.attributes.canonicalTitle}'s image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .sharedElement(
                        rememberSharedContentState(key = anime.id),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                contentScale = ContentScale.Crop
            )
            Column {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(text = anime.attributes.averageRating.toString())
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = "Star icon",
                            tint = MaterialTheme.colorScheme.inversePrimary
                        )
                    }

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = anime.attributes.canonicalTitle.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = anime.attributes.synopsis.toString(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }

}