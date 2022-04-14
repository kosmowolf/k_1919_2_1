package com.example.k_1919_2_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.lesson3.Lesson3
import com.example.k_1919_2_1.lesson3.someViewGroup
import com.example.k_1919_2_1.lesson4.Lesson4
import com.example.k_1919_2_1.lesson4.Speakable
import com.example.k_1919_2_1.view.weatherList.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }

        val button = Button(this)
        val view1: View = LinearLayout(this)
        val view2: View = TextView(this)
        (view2 as TextView).text = ""
        someViewGroup((view1 as LinearLayout))

        val looperNotNullable:Looper = getMainLooper()
        val looperNullable:Looper? = getMainLooper()

        val lesson3=Lesson3()
        val lesson4 = Lesson4()
        lesson4.lesson3=lesson3
        lesson4.some()//Способ 1

        lesson4.f = lesson3.f //Способ 2
        lesson4.some2()

        lesson4.speakable = lesson3 //Способ 3.1
        lesson4.some3()

        lesson4.speakable = lesson3.callback //Способ 3.2
        lesson4.some4()

        lesson4.speakable = lesson3.callbackLambda1 //Способ 4.1
        lesson4.some5()

//        lesson4.speakable = lesson3.callbackLambda2 //Способ 4.2
//        lesson4.some6()
        lesson4.some6 { string:String,i:Int -> Log.d("@@@", "  Сообщение $string")
            1.0
        }


    }
}
