 lazy() is a function that takes a lamabda and returns an instance of Lazy<T> which can serve as a delegate for implementing a lazy property: The first call to get() executes the lambda passed to lazy() and remebers the result, subsequent calls to get() simply returnthe remembered result
 
 ```
 val lazy: String by lazy {
  println("Computed!")
  "Hello"
 }
 
 fun main() {
  println(lazyValue)
  println(lazyValue)
}
 ```
 
By default, the evaluation of a lazy preporties is synchronized: the value is computed only in one thread, and all thread will see the same value. If the synchronization of initialization delegate is not required, so that multiple threads can execute it simultaneusly, pass LazyThreadSafetyMode.PUBLICATION as a parameter to the lazy() function. And if you're sure that the initialization will alwasy happend on the same thread as the one where you use teh property, you can use LazyThreadSafetyMode.NON: it doesn't incur any thread-safety guantess and the related overhead. 


