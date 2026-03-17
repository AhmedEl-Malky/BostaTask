package com.malky.bostatask.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.malky.bostatask.data.mappers.toGameUi
import com.malky.bostatask.domain.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    pager: Pager<Int, Game>
) : ViewModel() {

    val gamesPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { game -> game.toGameUi() }
        }
        .cachedIn(viewModelScope)
}
