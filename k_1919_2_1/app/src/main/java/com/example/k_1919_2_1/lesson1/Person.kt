package com.example.k_1919_2_1.lesson1

import android.view.View

open class Person constructor(val name:String="defaultName", val age:Int=20){
    fun test(testParam:String){
        val tetsable3 = testParam2
    }
    lateinit var testParam2:String
}


class Student(name:String, age:Int):Person(name,age), View.OnClickListener {
    override fun onClick(p0: View?){

    }

}