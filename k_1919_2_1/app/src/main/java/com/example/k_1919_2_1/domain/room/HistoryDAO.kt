package com.example.k_1919_2_1.domain.room

import androidx.room.*

@Dao
interface HistoryDAO {
    @Query("Insert into history_table (city,temperature,feelsLike,icon) values (:city,:temperature,:feelsLike,:icon)")
    fun nativeInsert(city:  String, temperature: Int, feelsLike:Int, icon:String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Query("SELECT * FROM history_table")
    fun getAll():List<HistoryEntity>

    @Query("SELECT * FROM history_table WHERE city=:city")
    fun getHistoryForCity(city:String):List<HistoryEntity>
}