# Data Class
A classes main purpose is to hold data, 
```
data class User(val name: String, val age: Int)
```

The compiler automatically derive teh following members from all properties declared in the primariy constructor.
- equals / hashcode()
- toString()
- componentN() functions corresponding to the properties in their order of declaration
- copy function

TO ensure consistency and meanifgul behavior of the generated code, data classes have to fulfill the following requirements. 
- The primary constructor needs to have at least one parameter
- All primary constructor parameters need to be marked as val or var
- Data classes cannot be abstract, open sealed or inner
- Data classe may only implmenet interface (before 1.1)

Additionally, the memebers generation follows these rules with regard to the memebers inheritance
- If there are explicit implementations of equals(), hashCode() or toString() in the data class body or final implementation in a superclass, then these functions are not generated, and the existing implementations are used
- If a supertype has the componentN() functions that are open and return compatible types, the corresponding functions are generated for the data class and override those of the supertype. If the functions of the supertype cannot be overriden due to incompatible sigatures or being final an error is reported
- Deriving a data class from a type that alread has a copy(...) function with a matching signature is decpreacted in kotlin 1.2. and is probhited in kotlin 1.3.

On the JVM, if the generated class needs to have parameterless constructor, default values for all properties have to be specifeied
```
data class User(val name: String = "", val age: Int = 0)
```

Properties declared in the class body
Note that the compiler only uses the properties defined insdie the primary constructor for the automatically generated functions. To exclude a property from the generated implementations, declared inside the class body
```
data class Person(val name: String) {
  vaar age: Int = 0
}
```
Only the property name will be used inside the toString(), equals(), hashCode() and copy() implementations, and there will only be one component function component1(). While two person objects can have differen ages, they will be treated as equal 
```
val person1 = Person("John")
val person2 = Person("John")
person1.age = 10
person2.age = 20
```

Copying 
Its often the case that we need to copy an object altering some of its properties, but keeping the rest unchanged. This what copy() function is generated for. For the user class above its implementation would be as follows
```
fun copy(name: String = this.name, age: Int = this.age) = User(name, age)
```

This allows us to write
```
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)
```

Data Classes and destructuring declarations
```
val jane = User ("Jane", 35)
val (name, age) = jane
println("$name, $age yeaers of age")
```
