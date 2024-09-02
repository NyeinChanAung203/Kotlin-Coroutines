package org.example

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

/// Composing Suspending Functions
/// Sequential, Concurrent, Lazy execution

/// Sequential execution

suspend fun getMessageOne():String {
    delay(1000)
    println("After working in getMessageOne")
    return "Hello"
}
suspend fun getMessageTwo():String {
    delay(1000)
    println("After working in getMessageTwo")
    return "World"
}

fun main41(){
    println("Main program start ${Thread.currentThread().name}")
    runBlocking {
        println("Main-runblock program start ${Thread.currentThread().name}")

        val time = measureTime {
            val m1 = getMessageOne()
            val m2 = getMessageTwo()

            println("$m1 $m2")

        }
        println("Completed in $time")

        println("Main-runblock program end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}



/// Current == Parallel execution
fun main42(){
    println("Main program start ${Thread.currentThread().name}")
    runBlocking {
        println("Main-runblock program start ${Thread.currentThread().name}")


        // if you don't want return result, you can use launch
        val time = measureTime {
            val m1 = async { getMessageOne() }
            val m2 = async { getMessageTwo() }

            println("${m1.await()} ${m2.await()}")

        }
        println("Completed in $time")

        println("Main-runblock program end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}




/// Lazy Async execution
fun main(){
    println("Main program start ${Thread.currentThread().name}")
    runBlocking {
        println("Main-runblock program start ${Thread.currentThread().name}")


        // if you don't want return result, you can use launch
        val time = measureTime {
            val m1 = async (start = CoroutineStart.LAZY) { getMessageOne() }
            val m2 = async (start = CoroutineStart.LAZY) { getMessageTwo() }
// if you do not use return result, that coroutine will never be called
//            println("${m1.await()} ${m2.await()}")

        }
        println("Completed in $time")

        println("Main-runblock program end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}