package com.example.k_1919_2_1.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.k_1919_2_1.utils.KEY_BUNDLE_SERVICE_MESSAGE

class MyBroadcastReceiver: BroadcastReceiver() {//настраиваемся на волну)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("@@@", "MyBroadcastReceiver onReceive ${intent!!.action}")
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_SERVICE_MESSAGE)
            Log.d("@@@", "MyBroadcastReceiver onReceive $extra")
        }
    }
}