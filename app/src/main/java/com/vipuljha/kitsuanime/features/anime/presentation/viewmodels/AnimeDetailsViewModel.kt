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
class AnimeDetailsViewModel @Inject constructor(private val repository: KitsuRepository) :
    ViewModel() {

    private val _animeDetails =
        MutableStateFlow<NetworkResponse<AnimeData>>(NetworkResponse.Loading)
    val animeDetails: StateFlow<NetworkResponse<AnimeData>> = _animeDetails

    fun fetchAnimeDetails(id: String) {
        viewModelScope.launch {
            repository.getAnimeById(id = id).collect {
                _animeDetails.value = it
            }
        }
    }
}