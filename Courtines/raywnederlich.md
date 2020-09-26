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

Coroutine Terminolgy
