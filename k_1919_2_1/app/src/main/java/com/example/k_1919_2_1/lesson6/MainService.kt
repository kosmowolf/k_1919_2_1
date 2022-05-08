package com.example.k_1919_2_1.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MainService(val name:String=""):IntentService(name) {
    override fun onHandleIntent(p0: Intent?) {
        Log.d("@@@","work MainService")

    }

}