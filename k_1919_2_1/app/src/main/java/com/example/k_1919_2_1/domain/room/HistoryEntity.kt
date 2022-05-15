package com.example.k_1919_2_1.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.k_1919_2_1.repository.City
import com.example.k_1919_2_1.repository.getDefaultCity
import java.sql.Timestamp
import java.util.concurrent.locks.Condition

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val city:  String,
    //val timestamp: Long,
    val temperature: Int,
    val feelsLike:Int,
    val icon:String,
    val condition:String="cloudy"
)
