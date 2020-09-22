```
fun main() {
  GlobalScoope.launch { // launch a new coroutine in background and continue. 
    delay(1000L) // non-blocking delay for 1 second
    println("World!") // Print after delay
  }
  
  println("Hello, ") // main thread continues while coroutine is delayed. 
  Thread.sleep(2000L) // Block main thread for 2 seconds to keep JVM alive. 
}
```

Essentially, coroutines are light-weight threads. They are launched with launch coroutine builder in  context of some CoroutineScope. Here we are launching a new coroutine in the GlobalScope, maning that the lifetime of the new coroutine is limited only by the lifetime of th whole application.

You can achieve the same result by replacing GlobalScope.launch{...} with thread {...} and delay {...} with Thread.sleep(...). 

Launch: Launches a new coroutine without blocking the current thread and returns a reference to the coroutine as a job. The coroutine is cancelled  when the resulting job is cancelled. The coroutine context is inherited from a CoroutineScope. Addtional context elemetns can be specified with context argument. 
CoroutineScope: calls the specified suspend block with this scope. The provided scope inherits its coroutineContext form the outer scope, but overrides the contest's job. 
GlobalScope: is used to launch the top level coroutines which are operating on thw whole application lifetime and are not cancelled prematurely.
delay: Delays the coroutine for a given time without blocking a thread and resumes it after a specified time. This suspending function is cancellable. If the jobe of the current coroutine is cancelled or completed while this suspending funcint is waiting,


## Bridging blocking and non-blocking 
The first example mixes non-blocking dealy(...) and blocking Thread.sleep(...) in the same code. It is easy to lose track of which one is block and which one is not. Let's be explicit about blockign using the runBlocking coroutine builder. 

```
fun main() {
  GlobalScope.launch { // Launch a new coroutine in background and continue
    delay(1000L)
    println("World!")
  }
  
  println("Hello, ") // main thread continues here immediately
  runBlocking { // but this expression blocks the main thread
    delay(2000L) // ... while we delay for 2 seconds to keep JVM alive
}
```

This example can also be written in more idiomatic way. using runBlocking to wrap the execution of the main function
```
fun main() runBlocking<Unit> { // start main coroutine
  GlobalScope.launch { // launch a new coroutine in a background thread. 
    delay(1000L)
    println("World!")
  }
  
  println("Hello, ") // main coroutine continues here immediately
  delay(2000L) // delaying for 2 secconds to keep JVM alive
```
Here the runBlockingn<Unit>{...} works as an adaptor that is used to start the top--level main coroutine. We explicitly specify its Unit return type, becuase a well-formed main functionin kotlin has to return Unit.

```
class MyTest {
  @Test
  fun testMySuspendingFunction() = runBlocking<Unit> {
    // here we can use suspending functions using any assertion style that we like
  }
}
```

## Waiting for a job
Delaying for a time while another coroutine is working is not a good approach. Let's explicilty wait (in a non-blockign way) until the backgroun job that we have launched is complete. 
```
val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its job
  dleay(1000L)
  println("World!")
}

println("Hello, ")
job.join() // wait until child coroutine completes. 
```

Now the result is still the same but the cod of the main coroutine is not tied to the duration of the backgroun job in any way

## Structured concurrecny
There is still something to be disred for practical usage of coroutines. When we use GlobalScope.launch, we create a top-level coroutine. Even though it is light-weight. it still consumes some memory resources  while it runs. If we forget to keep a reference to the newly launched coroutine. It still runs. What if the code in the coroutine hangs (for example, we erroneously dleay for too long), what if we launched too many coroutines and ran out of memory? having to manually keep references to all the alunched coroutines and join them is error prone. 

There is a better solution. WE can use structure concurrency in our codde. Instead of launching coroutines in the GlobalScope, just like we usually do with threads, we can launch coroutines in the specific scope of the operation we are performing. 

In our example, we have a main function that is turned inot a coroutine using the runBlocking coroutine builder. Every coroutine builder, including runBlocking, adds an instance of CoroutineScope to the scope of its code block. We can laucn coroutines in this scope without having to join them explicitly, becuase an outer coroutine does not complete until all the courtines launched in its scope complete. Thus we can make our example simpler
```
fun main() = runBlocking { // this: CoroutineScope
  launch { // launch a new coroutine in the scope of runBLocking
    delay(1000L)
    println("World!")
  }
  println("Hello, ")
```

## Scope Builder
In addtion to the coroutine sccope provided by differen builders, it is possible to declare your own scope using the coroutineScope builder. It creates a coroutine scope and does not complete until all launched children complete. 

runBlocking and coroutineScope may look sim9ilar becuase they both wait for thier body and all its children to complete. The main difference is that the runBlocking method blocks the current hread for waiting, while coroutinescope just suspends, releasing the underlying thread for other usages. Becuase of that difference runBlocking is a regular functionand coroutineScope is a suspending function.
```
fun main() = runBlocking { // this: CoroutineScope
  launch {
    delay(200L)
    println("task from runBlocking")
  }
  
  coroutineScope { // create a coroutine scope
    launch {
      delay(500L)
      println("Task from nested launch")
    }
    delay(100L)
    println("task from coroutine scope") / /This line will be printed before the nested launch
  }
  
  println("coroutine scope is over") // this line is not printed until the nested launch complet

```

## Extract function refactoring
Let's extract the block of code inside launch{...} into a separate function. When you perform "Extract function" refactoring on  this code, you get a new function with the suspend modifier. Thisis your first suspending function.Suspending function cna be used inside corotuines just like regular functions, but their additional feature is that they can, in turn, use other suspending functions to suspend execution of a coroutine. 

```
fun main() = runBlocking { 
  launch { doWorld() }
  println("Hello, ")
}

suspend fun doWorld() {
  delay(1000L)
  println("World!")
}
```
But what if the extracted function contains a coroutine builder which is invoked on the current scope? in this case, the suspend modifier on the extracted function i snot enough. Making doWorld an extension method on CoroutineScope is one of the solutions, but it may not always be applicalbe as it does not make the API clearer. The idiomatic solution is to have either an explicit CoroutineScope as a field in a class containing the target function or an implicit one when the outer class implements CoroutineScope. As a last resort, CoroutienScope can be used, but such an approach is structurally unsafe baecuase you non longer have control on the scope of execution of this methood. Only private APIs can use this builder. 
