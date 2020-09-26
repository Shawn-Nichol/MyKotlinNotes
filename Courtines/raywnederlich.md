














## Coroutine Jobs
As the name suggest, a job represents a piece of work that needs to be done. Aditionally, every job can be cancelled, finishing its execution. Becuase of that, it has a lifecycle and can also have nestted children. Coroutines builders like launch() and async() return jobs as a resut. 

If you createa job, by nesting coroutines, it forms a parent-child hierarchy. With the hierarchy, you can control all the children through this single instance of job. If you cancel the paren, all the children get cancelled too. If a child fails in teh execution, the rest othe hierarchy fails as well. 

Describing a parent-child hierarchy helps you to control the lifecycle of coroutines form a single instance when using it inside of an activity or fragment. 

This why you're declaring a parentJob. You can use it to ccancel adn clear up all coroutines hwich you launched. 

## Dispatcher
YOu can execute a coroutine using different coroutineDispatchers, as mention before. Some of the available CoroutineDispatchers in the API are Dispatcher.main Dispatcher.IO and Dispatchers.Default.

- Dispatchers.Defualt: CPU-intensive work, such as soritn glarge lists, doing comples calculations and similar. A shared pool of threadd on the JVM back it.
- Dispatcher.IO: Networking or reading and writing from files. In short  any input and ouput, as the names  states.
- Dispatchers.Main: Recommended dispatcher for performing UI-related events. For example, shoing lists in a reyclerView, updatin gView and so on.

Scoping Kotlin coroutines
Now, to define the scope when the corotines runs, you'll use a custom coroutineScope to handlle the lifecycle of the corotuines
```
private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
```
The plus() operator helps you create a set of CoroutineContext elements, which you associate with the coroutines in a particular scope. The context and their eelements are a set of fulres each kiotlin coroutine has to adhere to 

This set of elements can have information about
- Dispatchers which dispatch coroutines in a particular thread pool and executor
- Coroutine exception handler, which let you handle thrown exceptions
- Parent Job, which you can use to cancel all kotlin coroutines within the scope. 

Both the coroutineDispatcher and a job impelemtn Corotuine Context. This allows you to sum them using plust(), to combine their functinallity 



## Internal working of coroutines
Internally, coroutines ues the concept of Continuation-passing style progarmming, aslo known as CPS. THis style of programmin involves passing the control flow of the program as an arguemtn to functionns. This argument, in kotlin corotuines world is known as continuation. 

A continuation is nothing more than a callback. Although, it's much more system -level than you standard callbacks. The system uses them to know when a suspending function should continue or return a value. 

For example, when you call await(), the system suspends the outer coroutine until there is a value present. Once the value is there, it uses the continuation, to retturn it back to the outer coroutine. This way, it doens't have to block threads, it cna just notify itself that a coroutine needs a thread to conitnue its work. 

## Handling exceptions
Exception handling in Koltlin coroutines behaves differently depending on the Coroutine Builder you are using. THe exception may get propagated automatically or it may get deferred till the consumer consumes the reuslt. 

Here's how exceptions hehave for the builders you used in your code how to handle them
- Launch: The exception propagates to the parent and will fail your coroutine parent child hierarchy. This will throw an exception in the coroutine thread immediately. You canavoid these exceptions with try/catch blocksor a custom exception handler
- Async You defer exceptions until you consume the result the result for the asyn block. That means if you forgot or did not consume the result fot eh asyn block, through await(), you may not get an exception at all! The corotuuine willl bury it, and your app will be fine. If you want to avoid exceptions from await(), use a try/catch block either on the await9) call or within async()
