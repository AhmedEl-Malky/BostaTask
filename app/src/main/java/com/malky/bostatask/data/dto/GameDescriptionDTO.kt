package com.malky.bostatask.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDescriptionDTO(
    @SerialName("description_raw")
    val descriptionRaw: String
)