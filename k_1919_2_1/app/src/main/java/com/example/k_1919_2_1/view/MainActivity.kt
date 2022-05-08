package com.example.k_1919_2_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.lesson3.Lesson3
import com.example.k_1919_2_1.lesson3.someViewGroup
import com.example.k_1919_2_1.lesson4.BaseImpl
import com.example.k_1919_2_1.lesson4.BossDelegate
import com.example.k_1919_2_1.lesson4.Lesson4
import com.example.k_1919_2_1.lesson4.Speakable
import com.example.k_1919_2_1.lesson6.ThreadsFragment
import com.example.k_1919_2_1.view.weatherList.WeatherListFragment
import kotlinx.android.synthetic.main.fragment_threads.*

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
        with(lesson4){
            this.lesson3=lesson3
            some()//Способ 1
            f = lesson3.f //Способ 2
            some2()
            speakable = lesson3 //Способ 3.1
            some3()
            speakable = lesson3.callback //Способ 3.2
            some4()
            speakable = lesson3.callbackLambda1 //Способ 4.1
            some5()
            some6 { string:String,i:Int ->
                Log.d("@@@", "  Сообщение $string")
                1.0
            }
            was()
           // main(this@MainActivity)
        }

        val worker = BaseImpl()
        BossDelegate(worker,worker).apply {
            manipulate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_threads->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ThreadsFragment.newInstance()).commit()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun Lesson4.was(){
        Log.d("@@@","Был ${this.pr}")

    }
}
