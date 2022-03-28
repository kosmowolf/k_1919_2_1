package com.example.k_1919_2_1

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 4. Добавить кнопку в разметку и повесить на неё clickListener в Activity.
        val btn_click_me = findViewById<Button>(R.id.btn_click_me)
        btn_click_me.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

        com.example.k_1919_2_1.lesson1.Person("andrei",25).test("we")
    }
}
