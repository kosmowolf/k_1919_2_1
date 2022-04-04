package com.example.k_1919_2_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.lesson3.someViewGroup
import com.example.k_1919_2_1.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit()
        }

        val button = Button(this)
        someViewGroup(button)
    }
}
