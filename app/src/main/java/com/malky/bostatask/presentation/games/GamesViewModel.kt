package com.malky.bostatask.presentation.games

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class GamesViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(GamesState())
    val state = _state.asStateFlow()


}