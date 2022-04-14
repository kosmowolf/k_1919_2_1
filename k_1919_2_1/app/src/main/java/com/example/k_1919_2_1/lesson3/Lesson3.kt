package com.example.k_1919_2_1.lesson3

import android.util.Log
import android.view.ViewGroup
import com.example.k_1919_2_1.lesson1.Person
import com.example.k_1919_2_1.lesson4.Speakable

class Lesson3: Speakable {

   fun usual1(string: String){//1 способ
            Log.d("@@@", "  Сообщение $string" )
    }

    val f=fun(string:String){//2 способ
        Log.d("@@@", "  Сообщение $string" )

    }

    override fun f(string: String,i: Int):Double {//3.1 способ
        Log.d("@@@", "  Сообщение $string" )
        return 1.0
    }

    val callback =  object : Speakable{ //3.2 способ
        override fun f(string: String,i: Int):Double {
        Log.d("@@@", "  Сообщение $string" )
        return 1.0
        }
    }


    val callbackLambda1 = Speakable hak@{ string,i -> //4.1 способ
        Log.d("@@@", "  Сообщение $string" )
        1.0
    }

    val callbackLambda2 =  r@{ string:String,i: Int -> //4.2 способ
        Log.d("@@@", "  Сообщение $string" )
        return@r 1
    }
















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