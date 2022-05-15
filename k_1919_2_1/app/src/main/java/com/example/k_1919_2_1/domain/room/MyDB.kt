package com.example.k_1919_2_1.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1)
abstract class MyDB:RoomDatabase() {
    abstract fun historyDAO():HistoryDAO
}