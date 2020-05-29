package C_ClassesAndObjects.CA_ClassesAndInheritance

fun main() {

}
/*
// The class can also declare secondary constructors, which are prefixed with constructor

class Person{
    var childern: MutableList<Person> = mutableListOf<>()
    constructor(parent:  Person) {
        parent.childern.add(this)
    }
}

// If the class has a primary constructor, each secondary constructor needs to delegate to the praimary constructor, either
// directly or indirectly through another secondary constructor. Delegation to another constructor of the same class is
// done using the 'this'

class PersonOne(val name: String) {
    var children: MutableList<Person> = mutableListOf<>()
    constructor(name: String, parent: PersonOne) : this(name) {
        parent.children.add(this)
    }
}

// Note the code in the initializer block effectivley becomes part of the primary constructor.
class Constructors{
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}

// If a non-abstract class does not declare any constructors (primary or secondary), it will have a generated primary constructor with no
// arguments and will be public. If you don't want the class to have a public constructor you must declare and empty constructor.
class DontCreateMe private constructor() {

}

// On the JVM, if all of the parameters of the primary constructor have default values, the compiler will generate an
// additional parameterless constructor which will use the default values. This makes it easier to use kotlin with libraries such
// as Jackson or JPA

*/