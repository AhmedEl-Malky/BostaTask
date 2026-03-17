package com.malky.bostatask.presentation.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.malky.bostatask.data.repositories.GamesRepository
import com.malky.bostatask.navigations.Destinations
import com.malky.bostatask.utils.onError
import com.malky.bostatask.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: GamesRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    val gameId = saveStateHandle.toRoute<Destinations.GameDetails>().id

    private val _state = MutableStateFlow(GameDetailsState())
    val state = _state.onStart {
        getGameById(id = gameId)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = GameDetailsState()
    )

    suspend fun getGameById(id: Int) {
        _state.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }
        repository.getGameById(id = id)
            .onSuccess { game ->
                Log.d("GameDetails", "Success : $game with id = $id")
                _state.update {
                    it.copy(
                        isLoading = false,
                        gameDetails = game,
                        error = null
                    )
                }
            }.onError { error ->
                Log.d("GameDetails", "Error : ${error.message} with id = $id")
                _state.update {
                    it.copy(
                        error = error.message,
                        isLoading = false,
                        gameDetails = null
                    )
                }
            }
    }
}
