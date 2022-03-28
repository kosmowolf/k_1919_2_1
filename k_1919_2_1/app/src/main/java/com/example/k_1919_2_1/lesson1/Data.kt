package com.example.k_1919_2_1.lesson1

import android.util.Log
//a. Сформировать data class с двумя свойствами и вывести их на экран приложения.
//b. Создать Object. В Object вызвать copy и вывести значения скопированного класса на экран.
object Database{
    fun getTestCycle(){
        //c. Вывести значения из разных циклов в консоль, используя примеры из методических материалов.
        val list = listOf(1,2,3,4,5,6,7,8,9)
        for (one in list){
            Log.d("myLogs","$one gettestCycle")
        }
        list.forEach{
            Log.d("my","$it gettetst")
        }
        repeat(10){
            Log.d("my","$it gettetst")
        }
    }
}