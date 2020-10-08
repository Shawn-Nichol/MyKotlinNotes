# Object Expression and declaration
Someitimes we need to create an object of a slight modification of some class, without explicityly declaring a new subclass for it. Kotlin handles this case with object expression and object declarations. 

To create an object of an anonymous class that inherits from some type 
```
window.addMouseListener(object : MouseAdapter() {
  override fun mouseClicked(e: MouseEvent) { ...}
  
  override fun mouseEntered(e: MouseEvent) { ... }  
}
```
if a supertype has a constructor, approperate constructor parameters must be passed to it. Many supertypes may be specified as a comma-separated list after the colon: 
```
open class A(x: Int) {
    public open val y: Int = x
}

open class A(x: Int) {
  public open val y: Int = x
}

interface B { /*...*/ }

val ab: A object : A(1), B {
  override val y = 15
}
```

If by any chance, we need just an object with no nontrivial supertypes, we can simply say
```
fun foo() {
  val adHoc = object {
    var x: Int = 0
    var y: Int = 0
  }
  print(adHoc.x + adHoc.y)
}
```

Note that anonymous objects can be used as types only in local and private declarations. If you use an anonymous object as a return type of a public function or the type of public property. the actual type of that function of property will be the declared supertype of the anonyomous object as a return type of a pulbic functio or th type of a public property., the actual type of that function or property will be the declared supertype of the anonymous object, or Any if you didn't declare any supertype, Members added in the anonymous object will not be accessible. 

```
class C {
  // Private function, so the return type is the anonymous object type
  private fun foo() = object {
    val x: STring = "x"
    
    // Pbulic functio, so the return type is any
    fun publicFoo() = object {
      val x: String = "X"
    }
    
    fun bar() {
      val x1 = foo().x // works
      val x2 = publicFoo().x // Error: unresolved reference 'x'
    }
  }
}
```


The code in the object expressions can access variables from the enclosing scope. 
```
fun countClicks(window: JComponent) {
    var clickCount = 0
    var enterCount = 0

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++
        }

        override fun mouseEntered(e: MouseEvent) {
            enterCount++
        }
    })
    // ...
}
```
