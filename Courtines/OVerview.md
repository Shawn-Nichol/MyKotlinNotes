Coroutines for asynchronous programming
Asynchronous or non-blocking programming is the new reality. Whether we're creating server-side, desktop or mobile applications, it's important that we provide an expericince that is not only fluid from the user's perspcetive, but scalable when needed. 

Kotlin, as a language, provides only minimal low-level APIs in its standard librarry to enable various other libraries to utilize coroutines. Unlike many other languages with simlar capabilities, async and wawait are not keywords in Kotlin and are not even part ot its standard library. Moreover, kotlin's concept of suspending function provides a safer and less error-prone abastraction for asynchronous operations that futures and promises. 

Kotlin.coroutines is a rich library for coroutines developed by jetBrains . It contains a number of high level cotroutine-enabled primitives that this guide covers, including launch, async and others

This is a guide on core features of kotlinx.coroutines with series of examples, divided up into different topics. 

In order to use coroutines as well as follow the examples in this guide, you need to add a dependency on the kotlin-courtines-core modules 
