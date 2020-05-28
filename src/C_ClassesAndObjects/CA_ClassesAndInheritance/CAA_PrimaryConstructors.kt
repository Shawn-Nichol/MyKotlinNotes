package C_ClassesAndObjects.CA_ClassesAndInheritance


fun main() {
    Invoice()
    EmptyClass()
    Person("Shawn")
    PersonOne("Ted")
    InitOrderDemo("Charlie")
}

/*
 Class declaration
 class Name, class header(specifying its type parameters, the primary constructor) body
 */
class Invoice() {

}


/* class has no header and body */
class EmptyClass

/*
The Primary constructor is part of the class header it goes after the class name and before the parameters.
 */
class Person constructor(firstName: String) {

}

/*
If the primary constructor does not have any annotations or visibility modifiers, the constructor keyword can be omitted:
 */
class PersonOne (firstName: String) {

}


/*
During an instance initialization, the initializer blocks are executed in the same order as they appear in the class body.
 */
class InitOrderDemo(name: String) {
    val first = "First: ${name}".also(::println)
    init {
        println("First initializer block that prints {$name}")
    }

    val second = "Second $name".also(::println)
    init {
        println("Second initializer block that prints ${name.length}")
    }
}

/*
Parameters of the primary constructor can be used in teh initializer blocks. They can also be used in property initializers
declared in the class
 */
class Customer(name: String) {
    val customerKey = name.toUpperCase()
}

/*
Declaring properties of the primary constructor can be used in the initializer blocks.
 */
class PersonTwo(val firstName: String, val lastName: String, var age: Int) {

}

/*
if the constructor has annotations or visibility modifiers the constructor keyword is required and the modifiers go before
the constructor.
 */
//class CustomerTwo public @Inject constructor(name: String) {

//}