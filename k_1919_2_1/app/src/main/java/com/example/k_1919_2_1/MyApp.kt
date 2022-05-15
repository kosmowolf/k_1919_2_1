package com.example.k_1919_2_1

import android.app.Application
import androidx.room.Room
import com.example.k_1919_2_1.domain.room.HistoryDAO
import com.example.k_1919_2_1.domain.room.MyDB

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object{
        private var db:MyDB?=null
        private var appContext:MyDB?=null
        fun getHistoryDAO(): HistoryDAO {
            if (db==null){
                if(appContext!=null){
                    db = Room.databaseBuilder(appContext!!,MyDB::class.java,"test")
                        .allowMainThreadQueries()
                        .build()
                }else{
                    throw IllegalStateException("Shit Happens")
                }
            }
            return db!!.historyDAO()
        }
    }
}