# What is a coroutine
Coroutine are light-weight threads, they are lightweigt becuase creating coroutines doesn't allocate new threads, they use predefined thread pools, and smart scheduling. Corotuines can be suspended and resumed mid-execution, this allows long running tasks to be paused any number of times and resumed when ready. 

```
fun main() {
  GlobalScope.launch { // laucn a new coroutine in the background and continue
    delay(1000L) // non-blocking delay for 1 second
    println("World!")
  }
  
  println("Hello, ") // Main thread conitnues wile coroutine is delayed
  thread.sleep(2000L) // Block main thread for 2 seonds to keep JVM alive. 
}
```

## Coroutine Job
A job represents a piece of work that needs to be done. Additionally, every job cab be canelled. Because of that, it has lifecycle and can also have nested shildren. CoroutineBuilders return jobs as a result. If you create a job by nesting coroutines, it forms a parent child hierarchy. With the hierarcy, you can control all the children through this single instance of job. If you cancel the parent, all the children get cancelled. If a child fails in the execution, the rest of the hierarchy fails as well. 

## Bridging block and non-blocking
A blocking call to a funcinction means that a call to any other function, fomrthe same thread will halt the parent's execution. Following up, this means that if you make a blocking call on the main thread's execution, you effectively freeze the UI. Until that blocking calls finsishes, the user will see a static screen. 

ex. mixes non-blocking and delay(...) and blocking threadd.sleep(...). Its easy to lose track of which one is block and which one is not. 
```
fun main() {
  GlobalScope.launch { // Launch new coroutine in background and continue
    delay(1000) 
    println("World!")
  }

  println("Hello, ") // main thread continues immediatley
  runBlocking { //this expression blocks the main thread
    delay(2000) // while we delay for 2 seoncds to keep the JVM alive
  }
}
```
Use RunBlocking to wrap the execution of the main thread. 
```
fun main() runBlocking<Unit> { // Start main coroutine
  GlobalScope.launch { // launch new coroutine in a background thread. 
    dealy(1000)
    println("World!")
  }
  
  println("Hello, ") // main coroutine continues here immediatley
  delay(2000L) // delaying for 2 seconds to keep JVM alive
  
}
```
Here the runBlocking { ... } works  as an adaptor that is used to start th top-level main coroutine. We explicitly specify its unit return type, becuase a well-formed main function kotlin has to return unit.
```
class MyTest {
  @Test
  fun testMySuspendingFunction() = runBlocking<Unit> {
    // here we can use suspending functions using any assertion style that we like
  }
}
```

## Waiting for a Job
Delaying for a time while another coroutine is working is not a good approach. Lets explicitly wait until the backgroun job that we have launched is complete
```
val job = Global.launch { // launch a new coroutine and keep a reference to its job.
  delay(1000)
  println("World!")
}

println("Hello, ")
job.join() // wait until child coroutine completes
```

Now the result is still the same but the code of the amin coroutine is not tied to the duration of the background job in anyway. 

## Structured concurrency
There is stilll something to be desired for practical usage of coroutines. When we use GlobalScope.launch, we create a top-level coroutine. Even thought it is light-weight it still consumes memory resources. If we forget to keep a reference tot the newly launched coroutine. It still runs. What if the code in the coroutine hangs, What if we laucnhed too many coroutines and ran out of memory? having to manually keep referneces to all the launched coroutiens and join them is error prone. 

There is better solution. We can use structure concurrency in our code. instead of launching coroutines in the GlobalScope, we can launch coroutines in the specific scope of the operation we are perfroming. 

In our exmaple, we have a min function that is turned into a coroutine using the runBlocking coroutine builder. Every coroutine builder, including runBlocing, adds an instance of coroutineScope to the scope of the code block. We can launch coroutines in this scope without having to join ghtem explicitly, because an oter coroutine does not complete until all the coroutines launched in its scope complete. Thus we can make our example simpler. 

```
fun main() = runBlocking { // CoroutineScope
  launch { // launch a new coroutine in the scope of runBlocking
    dealy(1000)
    pringln("World!")
  }
  
  println("Hello, ")
}
```

## Scope Builder
In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using the coroutineScope builder. I creates a coroutine scope and does not complete until all launched children complete. 

runBlocking and coroutineScope look similar becuase they both wait for their body and all its children tom complete. The main difference is that the runBLocking method blocks the current thread for wating, while coroutinescope just suspends, releasing the underlying thread for other usages. Becuase of that differnece runBlocking is a regular function and coroutineScope is a suspending function. 

```
fun main() = runBlocking { // CoroutineScope
  launch {
    delay(1000)
    println("task from run blocking")
  }
  
  coroutineScope { // create a coroutinescope
    launch {
      delay(5000)
      println("Task from next launch")
    }
    
    delay(500)
    prinln("task from coroutinescope") // This line will be printed before the nested launch
  }
  
  println("coroutine scope is over") // this line is not printed until the nested launch completes. 
}
```

## Suspending
Suspending doesn't block your parent functions execution. If you call a suspending function  in some thread, you can easily push that function to a differen thread. In case it is a heavy operation, it won't block the main thread. Moreover, if you require a result from the function, you can bridge back to the main thread, without a lot of code. That way you can fetch data in a coroutine from the main thread. All you have todo is launch the coroutine in a worker thread. This way you'll effectivley call something from the main thread, switch to the background and switch back one the data is ready. 

Extract the block of code inside launch { ... } into a separte function. Suspending function can be used inside coroutines just like regualr function but their addition features is that they can in turn use other suspending functions to suspend execution of a coroutine. 

```
fun main() = runBlocking {
  launch { doWorld() }
  println("Hello, ")
}

suspend fun doWorld() {
  delay(1000)
  println("World!")
}
```

