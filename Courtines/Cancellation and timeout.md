## cancelling coroutine execution
In a long-running application you migh need fine-grained control on your background coroutines. For example, a user might have closed the page that launched a oroutine and now it s result is no longer needed and its operation can be cancelled. The launch function returns a job that can be used cancel and running coroutine

```
val job = launch {
  repeat(1000) {i -> 
    println("Job: I'm sleeping $i ...")
    delay(500L)
  }
}

delay (1300L) // delay a bit
println("main: I'm tired of waiting")
job.cancel() // Cancels the job
job.join() // waits for job's competion
println("main: now I can quit.")
```

As soon as main invokes job.cancel we don't see any output from the other coroutine becuase it was cancelled. There is also a job extension function cancelAndJoin that combines cancel and join invocation

## Cancelation is cooperative
Coroutine cancellation is cooerative. A coroutine code has to cooperate to be cancellable. All the suspending functions in kotlinx.coroutines are cancellable. They check for cancellation fo coroutine and throw CancellationException when cancelled. However, if a coroutine is working in computation and does not check for cancellation, then it cannot be cancelled. 
```
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
  var nextPrintTime = startTime
  var i = 0
  while (i < 5) { // computation loop, just wastes CPU
    // print a message twice a second
    if(System.currentTimeMillis() >= nextPrintTime) {
      println("job: I'm sleepign ${i++} ...")
      nextPrintTime += 500L
    }
  }
}
delay(1300L) // delay
println("main: I'm tired of waiting")
job.cancelAndJoino() // cancels the job and waits for its completion
println("main: now I can quite.")

```

## Making computation code cancellable
There are two approaches to making computation code cancellable. The first one is to periodically invoke a suspending function that checks for cancellation. There is a yild function that is good choice for that prpose. The other one is to explicitly check the cancellation status. Let us try the latter approach. 

Replace while (i < 5) in the previos example with while(isActive) and rerun it
```
val startTime = System.currentTimeMillis()
val job = launch(Dispatcher.Default) {
  var nextPrintTime = startTime
  var i = 0
  while(isActive) { // cancellable computation loop
    // print message twic a second
    if(System.currentTimeMillis() >= nextPrintTime) {
      println("job: I'm sleeping ${i++} ...")
      nextPrintTIme += 500L
    }
  }
}
delay(1300L)
println("main: I'm tired of waiting")
job.cancelAndJoin() // cancels the job and waits for its completion
pringln("main: now I can quite")
```
isActive is an extension property available inside the coroutine via the CorotuineScope object. 

## Closing resource with finally
Cancellable suspending function throw CancellationException on cancellation which can be handled in the usual way. For example. Try {...} finally {...} expression and kotlin use function execute thier finalization action normally when a coroutine is cancelled
```
val job = launch {
  try {
    repeat(1000) { i-> 
      println("job: I'm sleeping $i ...")
      delay(500L)
    }
  } finally {
    println("job: I'm running finally")
  }
}
delay(1300)
println("main: I'm tired of waiting")
job.cancelAndJoin()
println("main: Now I can quite")
```

## Run non-cancellable block
Any attempt to use a suspending function in the finally block of the previous example causes CancellationExceptioin, becuase the coroutinen running this code is canclled. Usually, this is not a problem since all well-behaving closign operations(cloosing a file, cancelling a job, or closing any kind of a communciation channel) are usually non-blocking and do not involve any suspending functions. However, in the rare case when you need to suspend in a cancelled coroutine you can wrap the corresponding code in withContext(NonCancellable) {...} using with COntext function and NonCancellable context.
```
val job = launch {
  try {
    repeat(1000) { i -> 
      println("job: I'm sleeping $i ...")
      delay(500)
    }
  } finally {
    withContext(NonCancellable) {
      println("job: I'm running finally")
      delay(1000L)
      println("job: And I've just delayed for 1 sec becuase I'm non-cancellable")
    }
  }
}

delay(1300L)
println("main: I'm tired of waiting")
job.cancelAndJoin()
println("main: now I can quite.")
```

## Timeout
The most obvious practical reson to cancel execution of a coroutine is becuase its execution time has exceeded some timeout. While you can manuually track the reference to the corresponding job and launch a separate coroutine to cancel the tracked one after dealy, there is a ready to use withTimeout function that does it.
```
withTimeout(3000L) {
  repeat(1000) { i -> 
    println("I'm sleeping $i ...")
    delay(500L)
  }
}
```
The TimeoutCancellationException that is thrown by withTimeout is a subclass of CancellationException. We have not seen its stack trace printe don the console before.That is becuase inside a canelled coroutine CancellationException is considered to be a normal reason for coroutine completion. However, in this example we have used withTimeout right inside the main function. 

Since cancellation is just an exception, all resources are closed in the usual way. You ccan wrap the code with timeout in a try {...} catch (e: TimeoutCancellationException) {...} block if you need to do some additional action specifically on any kind of timeout or use the withTimeoutOrNull function is similar to withTimeout but returns null on timeout instead of throwing an exception

```
val result = withTimeoutOrNull(1300L) {
  repeat(1000) {i -> 
    println("I'm sleeping $i ...")
    delay(500L)
  }
  "Done // willl get cancelled before it produces this result
}
println("Resultis $result")
```
