package com.malky.bostatask.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malky.bostatask.data.repositories.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(GamesState())
    val state = _state.onStart {
        fetchGames()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GamesState()
    )


    private suspend fun fetchGames() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
//            repository.fetchRemoteGames().onSuccess { gamesResult ->
//                _state.update {
//                    it.copy(
//                        games = gamesResult,
//                        isLoading = false
//                    )
//                }
//            }
        }
    }
}