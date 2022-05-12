package com.example.k_1919_2_1.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.lesson3.Lesson3
import com.example.k_1919_2_1.lesson3.someViewGroup
import com.example.k_1919_2_1.lesson4.BaseImpl
import com.example.k_1919_2_1.lesson4.BossDelegate
import com.example.k_1919_2_1.lesson4.Lesson4
import com.example.k_1919_2_1.lesson4.Speakable
import com.example.k_1919_2_1.lesson6.MainService
import com.example.k_1919_2_1.lesson6.MyBroadcastReceiver
import com.example.k_1919_2_1.lesson6.ThreadsFragment
import com.example.k_1919_2_1.utils.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.example.k_1919_2_1.utils.KEY_SP_FILE_NAME_1
import com.example.k_1919_2_1.utils.KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN
import com.example.k_1919_2_1.utils.KEY_WAVE
import com.example.k_1919_2_1.view.weatherList.WeatherListFragment
import kotlinx.android.synthetic.main.fragment_threads.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
        startService(Intent(this,MainService::class.java).apply {
            putExtra(KEY_BUNDLE_ACTIVITY_MESSAGE,"Привет сервис")//TODO HW key1 - должен быть в константах
        })

        val receiver = MyBroadcastReceiver()
        //registerReceiver(receiver, IntentFilter(KEY_WAVE))
        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
        //LocalBroadcastManager.getInstance(this).registerReceiver("myAction")

        val sp = getSharedPreferences(KEY_SP_FILE_NAME_1, Context.MODE_PRIVATE)
        //val sp = getSharedPreferences("fileName1", Context.MODE_MULTI_PROCESS)

        val editor = sp.edit()
        editor.putBoolean(KEY_SP_FILE_NAME_1_KEY_IS_RUSSIAN,true)
        editor.apply()

        //PeriodicWorkRequestBuilder<>()
        //WorkManager.getInstance(this).enqueue()
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
