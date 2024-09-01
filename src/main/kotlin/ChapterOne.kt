package org.example

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/// Both GlobalScope and runBlocking are launch coroutines
/// GlobalScope is non-blocking in nature
    // -> so we need to wait in main thread to finish it
    // if it takes 2s , wait 2s in main
    // if we don't wait, it will not get enough time to execute, so no execution occurred
/// runBlocking is blocking the thread in which it operates.


fun main(){
    println("Main program start: ${Thread.currentThread().name}")
    GlobalScope.launch {
        println("other program start: ${Thread.currentThread().name}")
//        Thread.sleep(2000)
        delay(1000)
        println("other program end: ${Thread.currentThread().name}")
    }
    // wait for GlobalScope coroutine to finish
    // Thread.sleep(2000)

    // Blocks the current main thread & wait for couroutine to finish
    runBlocking {
        println("run blocking program start: ${Thread.currentThread().name}")
        delay(1000)
        println("run blocking program end: ${Thread.currentThread().name}")
    }

    println("Main program end: ${Thread.currentThread().name}")
}


/// suspend fun can only be called from coroutine or another suspend fun
suspend fun wait(time:Long){
    delay(time) // delay is suspend fun
}