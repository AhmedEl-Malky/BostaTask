package com.malky.bostatask.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val rating: Double,
    val ratingTop: Int?,
    val backgroundImage: String?,
    val released: String?,
    val description: String?
)