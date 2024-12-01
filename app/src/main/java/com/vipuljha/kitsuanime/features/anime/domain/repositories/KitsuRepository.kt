package com.vipuljha.kitsuanime.features.anime.domain.repositories

import com.vipuljha.kitsuanime.core.utils.NetworkResponse
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData
import kotlinx.coroutines.flow.Flow

interface KitsuRepository {

    fun getTrendingAnimeList(): Flow<NetworkResponse<List<AnimeData>>>
    fun getAnimeById(id: Int): Flow<NetworkResponse<AnimeData?>>
}