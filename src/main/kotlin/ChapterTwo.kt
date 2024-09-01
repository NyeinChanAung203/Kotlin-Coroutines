package org.example

import kotlinx.coroutines.*


/// Coroutine builders -> launch, runBlocking, async

/// GlobalScope.launch -> create coroutine at global scope (app level)
    // playing music, file download
/// launch -> create coroutine at local scope (screen level) if screen destroyed,it will be also destroyed
    // some data computation, login operation

/// launchs a new coroutine without blocking the current thread
    // inherit the thread & coroutine scope of the immediate parent coroutine
/// launch return job, we can control that coroutine using that job
/// job.join wait for coroutine for finish and cancel coroutine
fun main1() {
    println("Main program start: ${Thread.currentThread().name}")
    runBlocking {
        println("runBlock program start: ${Thread.currentThread().name}")

        val job : Job = launch {
            println("launch program start: ${Thread.currentThread().name}")
            delay(2000)
            println("launch program end: ${Thread.currentThread().name}")
        }

        val job2 : Job = launch {
            println("launch 2 program start: ${Thread.currentThread().name}")
            delay(1000)
            println("launch 2 program end: ${Thread.currentThread().name}")
        }

        // wait it to execute, after that below will run
        // if not wait, below will run whether coroutine run or not
        job.join()

        println("runBlock program end: ${Thread.currentThread().name}")
    }
    println("Main program end: ${Thread.currentThread().name}")
}

/// async launch a new coroutine without blocking the current thread
// inherit the thread & coroutine scope of the immediate parent coroutine
/// async return Deferred, we can control that coroutine using that
/// use await() to wait for coroutine for finish and cancel coroutine
/// async can return result like String or Unit etc...
fun main2() {
    println("Main program start: ${Thread.currentThread().name}")
    runBlocking {
        println("runBlock program start: ${Thread.currentThread().name}")

        val jobDeferred : Deferred<Unit> = async {
            println("launch program start: ${Thread.currentThread().name}")
            delay(2000)
            println("launch program end: ${Thread.currentThread().name}")
        }
        jobDeferred.await()
        val jobDeferred2 : Deferred<String> = async {
            println("launch 2 program start: ${Thread.currentThread().name}")
            delay(1000)
            println("launch 2 program end: ${Thread.currentThread().name}")
            "Shark"
        }
        println("My name is ${jobDeferred2.await()}")

        println("runBlock program end: ${Thread.currentThread().name}")
    }
    println("Main program end: ${Thread.currentThread().name}")
}