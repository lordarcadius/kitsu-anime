package com.vipuljha.kitsuanime.features.anime.data.repositories

import com.vipuljha.kitsuanime.core.utils.NetworkResponse
import com.vipuljha.kitsuanime.features.anime.data.datasources.network.KitsuApi
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData
import com.vipuljha.kitsuanime.features.anime.domain.repositories.KitsuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class KitsuRepositoryImpl(
    private val api: KitsuApi
) : KitsuRepository {

    override fun getTrendingAnimeList(): Flow<NetworkResponse<List<AnimeData>>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val response = api.getTrendingAnimeList()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResponse.Success(it.toModel()))
                } ?: emit(NetworkResponse.Error("No data found"))
            } else {
                emit(NetworkResponse.Error(response.message()))
            }
        } catch (e: HttpException) {
            emit(NetworkResponse.Error("HTTP Error: ${e.message()}"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Network Error: Please check your connection"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error("Unexpected Error: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAnimeById(id: String): Flow<NetworkResponse<AnimeData>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val response = api.getAnimeDetails(id = id.toInt())
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(NetworkResponse.Success(it.toModel()))
                } ?: emit(NetworkResponse.Error("No data found"))
            } else {
                emit(NetworkResponse.Error(response.message()))
            }
        } catch (e: HttpException) {
            emit(NetworkResponse.Error("HTTP Error: ${e.message()}"))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Network Error: Please check your connection"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error("Unexpected Error: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

}