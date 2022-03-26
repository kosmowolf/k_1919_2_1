package com.example.ble

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=A
        setContentView(R.layout.activity_main)



//
//        var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == RESULT_OK) {
//                //granted
//                showToast("granted")
//            }else{
//                //deny
//                showToast("deny")
//            }
//        }
//
//        val requestMultiplePermissions =
//            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//                permissions.entries.forEach {
//                    Log.d("test006", "${it.key} = ${it.value}")
//                }
//            }
//        showToast(requestBluetooth.toString())
    }

    // Receiver
    //функция всплывающего сообщения
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}