package com.malky.bostatask.presentation.games

import com.malky.bostatask.domain.Genre

interface GamesInteractionListener {
    fun onFilterSelected(genre: Genre)
    fun onSearchQueryChanged(query: String)
}
