package org.example

import kotlinx.coroutines.*

/// If you want to cancel coroutine, that coroutine must be cooperative
/// Two ways to make coroutine cooperative
/// 1.  - only those suspending functions that belongs to kotlinx.coroutine
///     packages will make coroutine cooperative
///     - delay(), yield(), withContext(), withTimeout() etc. are the suspending functions that
///     belongs to kotlinx.coroutine package
/// 2.  - Explicitly check for the cancellation status within the coroutine
///     - CoroutineScope.isActive boolean flag



fun main31(){
    println("Main program start ${Thread.currentThread().name}")

    runBlocking {
        println("Runblock start ${Thread.currentThread().name}")

        // first way to be cooperative
//        val job: Job = launch {
//            for (i in 1..200){
//                print("$i.")
////                Thread.sleep(50)
//                delay(50)
//            }
//            println()
//        }
//        delay(2000)
//        // same with below
////        job.cancel()
////        job.join()
//        job.cancelAndJoin()


        // second way to be cooperative
        /// NEED TO ADD [ Dispatcher ]
        val job1: Job = launch(Dispatchers.Default) {
            for (i in 1..200){
                print("$i.")
                if(!isActive){
                    break
                }
                Thread.sleep(50)
            }
            println()
        }
        delay(2000)
        job1.cancelAndJoin()

        println("Runblock end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}


fun main32(){
    println("Main program start ${Thread.currentThread().name}")

    runBlocking {
        println("Runblock start ${Thread.currentThread().name}")

        val job: Job = launch(Dispatchers.Default) {
            try {
                for (i in 1..200){
                    print("$i.")
                    delay(5)
                }
            }catch (ex: CancellationException){
                println("\nException caught safely ${ex.message}")
            }finally {
                // suspend function is not working in finally cuz finally is executed after cancel
//                delay(1000)

                // if you want to execute suspend fun use withContext
                withContext(NonCancellable){
                    delay(1000)
                    println("Close launch in finally")
                }


            }

            println()
        }
        delay(500)
//        job.cancelAndJoin()
        job.cancel(CancellationException("Custom Message"))
        job.join()
        println("Runblock end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}



fun main(){
    println("Main program start ${Thread.currentThread().name}")

    runBlocking {
        println("Runblock start ${Thread.currentThread().name}")
        // even try catch exception is occured
//        withTimeout(2000){
//            try {
//                for (i in 1..200){
//                    print("$i.")
//                    delay(50)
//                }
//            }catch (ex:TimeoutCancellationException){
//                println("\n TimeoutCancellationException ${ex.message}")
//            }finally {
//                println("done")
//            }
//
//        }

        /// do not throw exception
        val result : String? = withTimeoutOrNull(2000){
                for (i in 1..100){
                    print("$i.")
                    delay(50)
                }
            "I am done"
        }
        println("\nResult $result")
        println("\nRunblock end ${Thread.currentThread().name}")
    }

    println("Main program end ${Thread.currentThread().name}")
}