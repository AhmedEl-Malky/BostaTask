package com.malky.bostatask.navigations

import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object AppGraph : Destinations

    @Serializable
    data object Games : Destinations

    @Serializable
    data class GameDetails(val id: Int) : Destinations

}