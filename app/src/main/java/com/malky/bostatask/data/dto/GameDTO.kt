package com.malky.bostatask.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("rating")
    val rating: Double,
    @SerialName("rating_top")
    val ratingTop: Int?,
    @SerialName("background_image")
    val backgroundImage: String?,
    @SerialName("genres")
    val genres: List<GenreDTO>,
    @SerialName("released")
    val released: String?,
    @SerialName("short_screenshots")
    val shortScreenshots: List<ScreenshotDTO>
)