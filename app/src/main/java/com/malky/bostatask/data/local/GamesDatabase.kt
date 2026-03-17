package com.malky.bostatask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.malky.bostatask.data.local.entities.GameEntity
import com.malky.bostatask.data.local.entities.GenreEntity
import com.malky.bostatask.data.local.entities.ScreenshotEntity

@Database(
    entities = [
        GameEntity::class,
        GenreEntity::class,
        ScreenshotEntity::class
    ],
    version = 1
)
abstract class GamesDatabase : RoomDatabase() {
    abstract val dao: GamesDao
}
