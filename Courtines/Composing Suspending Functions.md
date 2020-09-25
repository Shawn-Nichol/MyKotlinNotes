## Sequential by default
Assume that we  have two suspending functions defined elsewhere that do something useful like some kind of remote sersvice call or computation. We just pretened they are useful, but actually each one just delays for a second for the prpose of this example. 

```
suspend fun doSomethingUsefulOne(): Int {
  delay(1000L)
  return 13
} 

suspend fun doSeomthingUsefulTwo(): Int {
  delay(1000L) 
  return 29
}
```

What do we if we need them to be invoked sequentailly first doSomethingUsefulOne and then doSomethignUsefulTwo, and compute the sume of thier results? In practice we do this fif we use the result of the first function to make a descision on whether wee need to inovke the second one or to decide on how to invoke it. 

We use a normal suequential invocation, becuase the code in the coroutine, just like in the regular code, is sequental by default. The following example deomstrates it by measuring th total time it takes to execute both suspending functions

```
val time = measureTimeMillis {
  val one = doSomethingUsefulOne()
  val two = doSomethingUsefulTwo()
  println("The answer is ${one + two}
}
println("Completed in $time ms")
```

## Concurrent using async
What if there are no dependencies between invocations of doSomethingUsefulOne and doSomethingUsefulTwo and we want to get the answer faster by doing both concurrently? This is where async comes to help. 

Conceptually async is just like launch. It starts a separate coroutine which  is a light-weight thread that works concurrently with all the other coroutines. The difference is that launch returns a job and does not carry any resulting value, while async retruns later. You can use .await() on a deferred value to get its eventual result, but deferred is also a Job, so you can cancel it if needed. 

```
val time = measuredTimeMillis {
  val one = async {doSomethingUsefulOne()}
  val two = async {doSomethingUsefulTwo()}
  println("The answer is ${one.await() + two.await() }"
}

println("Completed in $time ms")
```

This is twice as fast becuase the two coroutines execute concurrently. Note that concurrency with coroutines is alwasy explicit. 

## Lazily started async
Optionally async can be made lazy by setting its start parameter to coroutineStart.LAZY. In this mode it only starts the coroutine whits wresultis required by await, or if its Job's start function is invoked. Run the following example. 
```
val time = measureTimeMillis {
  val one = async(start = CoroutineStart.LAZY)  { doSomethingUsefulOne() }
  val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
  
  one.start()
  two.start()
  println("The answer is ${one.await() + two.await()}")
}
println("Completed")
```

So here the two coroutines are defined but not executed as in teh previous example, but the control is given to the programmer on when exactly to start the execution by calling start. We first start one then start two and then await for the individual coroutines to finish. 

Note that if we just call await in println without first calling start on indvidiual coroutines, this will lead to sequential behavior, since await starts the coroutine execution and waits for its finish, which is not the intended use-case for lazy function in cases when computation of the value involves suspending functions. 

Async-Style functions
WE can define async-style functions that invoke doSomethingUsefulOne and doSeomthingUsefulTwo asynchronously using the async coroutine builder with an explicit GlobalScope refference. We name such functions with the "...Async" suffix to highlight the fact that the only start asynchrounous computation and one needds to use the resulting deferred value to get the result. 
```
// The resultl type is deffered<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
  doSomethingUsefulOne()
}

// The result type is deffered<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
  doSomethingUsefulTwo()
}
```
Note that these xxxAsync functions are not suspending functions. They can be used from aynwhere. However, their use alwasy implies asynchronous execution of their action with the invoking code. 
```
fun main() {
  val time = measureTimeMillis {
    // initiate async actions outside of a coroutine
    val one = somethingUsefulOneAsync()
    val two = somethingUsefulTwoAsync()
    // but waiting for a result must involve either suspending or blocking. 
    // here we use runBlocking {...} to block the main thread while waiting for the result
    runBlocking {
      pringln(("The answer is ${one.await() + two.await()}") 
    }
    println("Completed in $time ms")
  }
}
```
Consider what happens if between teh val one = somethignUsefulOneAsync() line and one.await() expression there is some logic error in the code and the program throws an exception and the operation that was being performed by the program aborts. Normally, a global error-handler could catch this exception, log and report the error for developers, but the program could other wise continue doing other operations. But haere we have somethingUsefulOneAsyn still running in the background even through the operation that initiated it was aborted. This probelm does not happen with tructured concurrency as shown in the section below. 

## Structured concurrency with asyn
Let us take the concurrent using async example and extract a function that concurrently performs doSeomthingUsefulOne  and doSomethingUsefulTwo and returns the sum of thier results. Becuase the async coroutine builder is defined as extension on CoroutineScope we need to have it in the scope and that is what the coroutineScope function provides
```
suspend fun concurrentSum(): Int = coroutineScope {
  val one = async {doSomethingUsefulOne() }
  val two = async { doSomethingUsefulTwo()( }
  one.await + two.await()
}
```
This way if something goes wrogn inside the code of the concurrentSum function and it throws an exception, all the coroutines that were launched in its scope willl be cancelled. 

```
val time = measureTimeMillis {
  println("The answer is ${concurrentSum()}")
}
printl("Copmletted in $time ms")
```
We still have concurrent execution of both operations, as evident from the output of the above main

cancellation is always prpagated through corotuines hierarchy
```
fun main()  = runBlocking<Unit> {
  try {
    failedConcurrentSum()
  } catch(e: ArithemeticException) {
    println("Computation failed with ArithmeticException")
  }
} 

suspend fun failedConcurrentSum(): Int = coroutineScope {
  val one = async<Int> {
    try {
      delay(Long.MAX_VALUE) // Emulates very long computation
      42
    }  finally {
      println("First child was cancelled")
    }
  }
  val two = asyn<Int> {
    println("Second child throws an exception")
    throw ArithmeticException()
  }
  one.await() + two.await()
}
```
