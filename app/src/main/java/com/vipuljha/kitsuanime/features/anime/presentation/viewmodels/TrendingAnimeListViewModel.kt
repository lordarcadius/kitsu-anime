package com.vipuljha.kitsuanime.features.anime.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vipuljha.kitsuanime.core.utils.NetworkResponse
import com.vipuljha.kitsuanime.features.anime.domain.models.AnimeData
import com.vipuljha.kitsuanime.features.anime.domain.repositories.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeListViewModel @Inject constructor(private val repository: KitsuRepository) :
    ViewModel() {

    private val _animeList =
        MutableStateFlow<NetworkResponse<List<AnimeData>>>(NetworkResponse.Loading)
    val animeList: StateFlow<NetworkResponse<List<AnimeData>>> = _animeList

    init {
        fetchAnimeList()
    }

    private fun fetchAnimeList() {
        viewModelScope.launch {
            repository.getTrendingAnimeList().collect {
                _animeList.value = it
            }
        }
    }
}