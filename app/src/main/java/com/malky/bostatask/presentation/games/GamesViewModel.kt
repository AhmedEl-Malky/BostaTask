package com.malky.bostatask.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.malky.bostatask.data.mappers.toGameUi
import com.malky.bostatask.domain.Game
import com.malky.bostatask.domain.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    pager: Pager<Int, Game>
) : ViewModel(), GamesInteractionListener {

    private val _state = MutableStateFlow(GamesState())
    val state = _state.asStateFlow()

    val gamesPaging = pager.flow
        .cachedIn(viewModelScope)
        .combine(state) { pagingData, currentState ->
            pagingData.map { it.toGameUi() }
                .filter { game ->
                    val genreFilter = currentState.selectedGenreId == 0 ||
                            game.genres.any { it.id == currentState.selectedGenreId }
                    val searchFilter = currentState.searchQuery.isEmpty() ||
                            game.name.contains(currentState.searchQuery, ignoreCase = true)
                    genreFilter && searchFilter
                }
        }


    override fun onFilterSelected(genre: Genre) {
        _state.update {
            it.copy(
                selectedGenreId = genre.id
            )
        }
    }

    override fun onSearchQueryChanged(query: String) {
        _state.update {
            it.copy(
                searchQuery = query
            )
        }
    }
}

