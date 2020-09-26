# FLow
interface Flow<out T>
A cold asynchronous data stream that sequentially emits values and completes normallly or with an exception. 

Intermediate operators on teh flow such as map,  filter, take, zip are functions that area pplied to the upstream flow or flows and return a downstream flow where further operators can bea pplied to. Intermediate operations do not execute any code in the flow and are nost suspending functions themselves. They only set up a chain of operations for furutre executioin and quickly return. This known as a cold flow property. 

Terminal operators on the flow are either suspendign functions such as collect, single, feduce, toList etc or launchin operator that starts collection of the flow in the given scope. They are applied to the upstream flow and trigger execution of all operations. Execution of the flow is also called collectin ghte flow and is alwasy perfromed in a suspending manner wiout actual blocking. Terminal operators complete normally or exceptionally dpeending on successful or failed execution of all the flo operations in the upstream. The most basic ternminal operator is collect for example 

```
try {
  flow.collect { value -> 
    println("Received $value")
  }
} catch (e: Exception) {
  println("The flow has thown an exception: $e")
}

````

Be ddfault, flows are sequentail and all flow operations are executed sequentially in the same coroutine, with an exception for a few operations  specifically designed to introduce concurrency into flow execution such as buffer and flatMapMerge.

The flow interface does not carry information whether af low truly is a cold stream that can be colled repeatedly and triggers execution of the same code everytime it is collected, or if it is a hot stream that emits differenct values from the same running source on each collection. However, conventionally flows represent cold streams. Transitions betweeen hot and cold streams are supported via channels and the corresponding API: channelFlow, produceIn, broadcastIn.

## Flow builders
There are the following basic ways to create a flow. 
- flowOf(...) functions to create a flow from a fixed set of values. 
- asFlow() extension functions on various types to convert them into flows. 
- flow{ ... } builder function to construct arbitrarry flows form potenially concurrent calls to the send function

## Flow constraints
All implementations of the Flow interface must adhere to two key properties desciebed in detail below
- context preservation
- Exception transparency

These properties ensure the ability to perform local reasoning about the code with flows and modularize the code in such a a way that upstream flow emitters can be developed separately from downstream flow collectors. A user of a flow does not need to be awarfe of implementation details of the upstream flows it uses. 

## Context preservation
The flow has a context preservation property; it encapsulates its own execution context and enver propagates or tleaks it downstream, thus making reasoning about the execution context of particular transformations or terminal operations trivial

There is only one way to change the context of a flow: Teh flowOn operator that changes teh upstream context "everything above the flowOn operator". for additional information refer to its documentation 

This reasoning can be demonstratted in practice. 
```
val flowA = flowOf(1,2, 3)
  .map{ it + 1 } // Will be executed in ctxA
  .flowOn(ctxA) // Changese the upstreamcontext: FlowOf and map
  
// now we have a context-reserving flow: it is executed somewhere but this information is encapsulated in the flow itself

val filtered = flowA // ctxA is encapsulated in flowA
  .filter { it == 3} // Pure operator without a context yet
  
withContext(Dispatchers.Main) {
  // All non-encapsulated opertors will be executed in Main: filter and single 
  val result = filtered.single()
  myUItext = result
}
```
From the implementation point of view, means that all flow implementations should only emit from the same coroutine. This constraint is efficiently enforced by the default flow builder. The flow builder. THe flow builder should be used if flow implementaitiono does not start any coroutines. Its implementation prevents most of the development mistakes. 
```
val myFlow = flow {
  // GlobalScope.launch { // is prohibited
  // launch(Dispatcher>IO) { // is prohibited
  // withContext(CoroutineName("myFlow")) // is prohibited
  emit(1) // ok
  coroutineScope {
    emit(2) // ok -- still the same coroutine.
  }
}
```

Use channel Flow if the collection and emission fo a flow are to be separated into multipel coroutines. It encapsulates all teh context preservation work and allows you to focus on oyur domain-specific problem, raher than invariant implementations details. It is possible to use any combination fo coroutine builders from within channelFlow. 

If you are looking for perfromance and are sure that no concurrent emits and context jumps will bhappen, the flow builder can be used alongside a coroutineScope or superviosScope instead. 
- Scoped primitive should be use to provide a CoroutineScope.
- Changing the context of emission is prohibited, no matter whether it is withContext(ctx) or a builder argument
- Collecting another flow from a sepaarate context is allowed, but it has the same effect as applying the flowOn operator to that flow, whic more efficient. 

