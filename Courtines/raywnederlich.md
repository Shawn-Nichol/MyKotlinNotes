Asynchronous programming is very important for modern application. Using it increase amount of work your app  can perfrom in parallel. This in turn allows you to run heavy-duty tasks away from the UI thread, in the background. By doing so, you avoid UI freezes, and provide a fluid experience for your users. 

Android provides several asynchronous programming mechanisms, but its difficult to find the most appropriate one to use. Some mechanisms have a huge learning curve. Others require a ton of boilerplate code to implement, and aren't that concise. This all which affects the scalablitily of your app, and increases the cognitive load for new develoeprs. It's best if the API s you rely on are easy to use and scale when needed. 

Becuase all platforms on the JVM had the same problem, the team from JetBrains has come up with a new API. THe idea behind it is to help you solve all these problems, without a steep learning curve. In this tutorial, you'll learn about that API kolin Coroutines. 

Why Use Coroutines? 
Asy ou may know, Android developers today have many async tools as hand. Theses include RxJaava/Kotlin/Android, AsyncTasks, Jobs, Threads and more. So why would you need another tool in the toolbox, something else to learn?

If you've worked with Rx, then you know it takes a lot of effort ot oget to know it enought to be able to use if safely. On the other hand, asyncTasks and threads can easily introduce leaks adn memory overhead. Finally, relying on  all these APIs, which use callbacks can introduce leaks an d memory overheaed. Finally, relying on all these APIs, which use callbacks, can introduce a ton of code. Not only that but the code can become unreadable, as you introduce more callbacks. 

So trust me, after tyring out kotlin Coroutines, you'll realize they aren't just another tool. They're a whole new way of thingking about asynchronicity!

Koltin Coroutines help you to write asynchronous code in a more natural way. That is, in a sequential style of programming, which is more humanly-understandbnle and readable. They offer several benefits, which you'll learn about in this tutorial 

Throughout this tutorial, you'll develop a photo ediing app. Snowy, which will aloow you to download an image and then apply a snow filter to that image. To downlaod the images and process them you'll need to perform asynchronous tasks

Along the way you'll  also learn
- how Kotlin coroutines work internally
- how to create you own coroutines
- Exception handling with coroutines. 
- How kotlin corotuines compare to other tools like threads, RxKotlin and son. 

## Introductin corotuines
The documetnation says kotlin coroutines are like lightweight threads. They are lightweight becuase creating coroutines doesn't allocate new threads. Instead, they use predefined thread pools, and smart scheduling. Schedduling i sthe process of determining which piece of work you will execute next. Just liek a regular schedule. 

Additionally, coroutines can be suspended and resumed mid-execution. This means you can have a long-running task, which you can execute little-by litttle. You can pause it any number of times, and resume  it when you're ready again. Knowing this creating a large number of kotlin coroutines won't bring unnececessary memory overhead to your program. You'll just supend som eo f them until the thread pool frees up. 

### Suspending vs blocking
Well, suspension and blocking sound similar, but they're actually very differnt. A  blocking call to a function means that a call to any other functino, from the same thread, will halt the parent's execution. Following up, this means that if you amke a blocking call on the main thread's execution, you effectively freeze the UI. Until that blocking calls finsihes, the user will see a static screnn whichi is not a good thing. 

ON the other hand, suspendin godesn't nnecessarily block your parent function's execution . If you call a suspendinf function in some thread, you can easily push that function to a different thread. In case it is heavy opeartion, it won't block the main thread. Moreover, if ou require a result from the function, you can bridge back to the mainT thread, without a lot of code. That way you can fetch data in a coroutine fom the main thread. All you have to do is launch the coroutine in a worker thread. THis way you'll effectively call somethign from the main thread, switch to the background and swithc back once the data is ready. 

If the suspending function has to suspend, it will simply pause it execution. This way you free up its thread for other work. Once it's done suspending, it will get the next free thread from the pool, to finsish its work. 

## Coroutine Terminolgy
- Suspending functions: This kind of function can be suspended without blocking the current thread. Instead of returning a simple vlaue, it aslo knows in which context the caller suspended it. Using this it can resume appropriately, when ready. 

- CoroutineBuilders: These take a suspending lambda as argument to create a couritne. There are a bunch of coroutine builders provided by kotlin coroutines, inclucing async90, launch(), runBlocking()

- CoroutineScope: Helps to define the lifecycle of the kotlin coroutine. It can be application wide or bound to a component like the android activity. You have to use a scope to starrt a coroutine

- CoroutineDispatcher: Defines thread pools to launch your kotlin coroutines in . Thihs could be the background thread pool, main thread or even your custom thread pool. You'll usethis to switch between, and return results from, threads. 

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
