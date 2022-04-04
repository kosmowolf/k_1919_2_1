package com.example.k_1919_2_1.lesson3

import android.util.Log
import android.view.ViewGroup
import com.example.k_1919_2_1.lesson1.Person

class Lesson3 {
    fun test(){
        val people:List<Person> = mutableListOf(Person("name1",20),Person("name2",22))
        people.get(0).age
        people[0].age
        people.size
        val peopleHack = people.toMutableList()
        val peopleAge:List<Person> =people.filter { it.age>20 }

        val arr1 = arrayOf(1,2,3,4,5,6,7,8,9,10)
        val arr2 = arr1.map { it*2 }
        peopleHack.add(Person("name1",20))
        write(1)
        write(10.0f)
        write(10.0)
        write(10.0)
        write("")

        writeAll(1)
        writeAll(10.0f)
        writeAll(10.0)
        writeAll(10.0)
        writeAll("")

//        val peopleAny:MutableList<Any> = mutableListOf(Person("name1", 20), Person("name2",22))
//        val peoplePerson:MutableList<Person> = mutableListOf(Person("name1", 20),Person("name2",22))
//        val peopleMix:List<Any> = peoplePerson

        val producerAny: Producer<Any> = Producer<Person>()

    }

    class Producer<out T>{
        private val content = mutableListOf<T>()
        fun test():T{
            return  content.last()
        }
    }

    class Consumer<in T>{

        fun test(params:T){

        }
    }
    fun <T> writeAll(i:T){
        Log.d("","это $i")
    }

    fun write(i:Int){
        Log.d("","это $i")
    }
    fun write(i:Double){
        Log.d("","это $i")
    }
    fun write(i:Byte){
        Log.d("","это $i")
    }
    fun write(i:Boolean){
        Log.d("","это $i")
    }
    fun write(i:Person){
        Log.d("","это $i")
    }
    fun write(i:String){
        Log.d("","это $i")
    }
    fun write(i:Float){
        Log.d("","это $i")
    }

}

fun <T: ViewGroup> someViewGroup(i:T){
    Log.d("","это $i")
}

interface Flyable {
    val i: Int
    fun test()
    fun test2()
    fun test3() {
        Log.d("", "$i")
    }
}