package org.example

import kotlinx.coroutines.*

/// Coroutine Scope, Coroutine context, Dispatchers

/// Coroutine Scope
/// Each coroutine has it own CoroutineScope instance attached to it
fun main51(){
    runBlocking {
        println("runblock $this")

        launch {
            println("launch $this")
        }

        async {
            println("async $this")
        }
    }
}


/// CoroutineContext & Dispatcher.
/// * Dispatcher determine the thread of a coroutine
fun main(){
    runBlocking {
        // Without Parameter: CONFINED [Confined Dispatcher]
        // - Inherits CoroutineContext from immediate parent coroutine
        // - Even after delay() or suspending function, it continues to run in the same thread
        launch {
            println("Ex1: ${Thread.currentThread().name}")
            delay(1000)
            println("Ex1 after delay: ${Thread.currentThread().name}")
        }

        // With Parameter: Dispatcher.Default [ similar to GlobalScope.launch ]
        // - Get its own context at Global level. Execute in a separate background thread
        // - After delay() or suspending function, it continues to run either in the same thread or some other thread
        launch(Dispatchers.Default) {
            println("Ex2: ${Thread.currentThread().name}")
            delay(1000)
            println("Ex2 after delay: ${Thread.currentThread().name}") // can be current Thread or some other thread
        }


        // With Parameter: Dispatcher.Unconfined [ unconfined dispatcher ]
        // - Inherit CoroutineContext from the immediate parent coroutine
        // - After delay() or suspending function, it continues to run in some other thread
        launch(Dispatchers.Unconfined) {
            println("Ex3: ${Thread.currentThread().name}") // main
            delay(1000)
            println("Ex3 after delay: ${Thread.currentThread().name}") // some other assume Thread1


            // Using coroutineContext property to flow context from parent to child
            launch(coroutineContext) {
                println("Ex5: ${Thread.currentThread().name}") // Thread1 - because parent is Thread1
                delay(1000)
                println("Ex5 after delay: ${Thread.currentThread().name}") // Thread1
            }
        }


        // Using coroutineContext property to flow context from parent to child
        launch(coroutineContext) {
            println("Ex4: ${Thread.currentThread().name}") // main
            delay(1000)
            println("Ex4 after delay: ${Thread.currentThread().name}") // main
        }
    }
}

/*
    Dispatcher

    - Default
    - Unconfined
    - Main -> use for UI in android
    - IO -> use IO such as db, internet etc...


 */