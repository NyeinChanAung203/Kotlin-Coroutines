There are 5 chapters in this course.  

In chapter 1, get a solid understanding of what is #coroutine in #kotlin. 
    - Explore the 'suspend' keyword, comparison with threads, delay function, Thread.sleep function and a lot more. 
    - Basics of coroutine builders: launch, async, and runBlocking.  

In chapter 2, dive deeper into Coroutine Builders and explore them in detail. 
    - Use GlobalScope companion object to launch coroutines. 
    - Why GlobalScope is discouraged to use? 
    - Test suspending functions by writing a JUnit test case. 

In chapter 3, learn to cancel coroutines and handle exceptions. 
    - What is cooperative coroutine? How to write a cooperative code? 
    - Explore job.cancel(), and job.cancelAndJoin() functions.
    - Use of yield() and delay() suspending functions (suspend modifier). 
    - Explore what is CoroutineScope.isActive boolean flag. 
    - Handle CancellationException and TimeoutCancellationException.  
    - Use withContext(NonCancellable), withTimeout, and withTimeoutOrNull coroutine builders

In chapter 4, compose suspending functions in different ways within a coroutine. 
    - Explore how code execution in coroutine is sequential by default.
    - How to execute suspending functions in a coroutine concurrently?
    - Lazily execute async coroutine by using CoroutineStart.LAZY 

In chapter 5, learn a few advanced coroutine concepts. 
    - Understand what is CoroutineScope?
    - What is CoroutineContext?
    - What is a Dispatcher? 
        - Role of a dispatcher: to assign a thread to a coroutine. 
        - Types of Dispatcher: Confined, Unconfined, Default, Main, and IO 
    - Components of CoroutineContext: Job, Dispatcher, and Coroutine Name.

YouTube Link -> https://www.youtube.com/watch?v=lmRzRKIsn1g&t=3761s
