package com.vipuljha.kitsuanime.features.anime.data.datasources.network

import com.vipuljha.kitsuanime.features.anime.data.dto.AnimeResponseDto
import com.vipuljha.kitsuanime.features.anime.data.dto.TrendingAnimeListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {

    @GET("trending/anime")
    suspend fun getTrendingAnimeList(): Response<TrendingAnimeListDto>

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): Response<AnimeResponseDto>

}