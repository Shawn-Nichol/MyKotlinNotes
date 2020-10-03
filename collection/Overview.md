# Collection
A collection usually contains a number of objects of the same type. Objects in a collection are called elements or itesm. For example, all the students in a department form a collection that can be used to calculate their average age. 

## List
List is an ordered collection with acceess to eleemtns by indices - integer numbers that reflect their position. Elements can occur more than once in a list. An exmaple of a list is a sentence: it's a group of words, their order is important, and they can repeat. 

## Set
Set is a collection of unique elemenets. It reflects the mathematical abstraction of set: a group of objects without repretitions. Generally, the order of set elements has no significance. For example, an alphabet is a set of letters

## Map
Map is a set of key-value pairsr. Keys are unique, and each of them maps to exactly one value. The values can be duplicates. Maps are useful for storing logical connection s between objects, for exmaple, an  employees ID and their position. 

You can manipulate collections independently with objects of the same type that is stored in them. You add a string to a String collection and and Int to Int collection, you can not add String to Int collection. 

# Collection Types
There are two types of interface for each collection types

- Read-only interface that providse operations for accessing collection elements. 
- A mutable interface that extends the corresponding read-only interface with write operations: adding, removing and updating its elements. 


Note that altering a mutable collection doesn't require it to be a var write operations modify the same mutable collection object, so the reference doesn't change. Although, if you try to reassign a val collection you'll get a compliation error. 
```
val number = mutableListOf("one", "two", "three")
numbers.add("five")
// numbers = mutableListOf("six", "seven") // compliation error. 
```

The read-only collection types are covariant. THis means that, if a rectangle class inherits from shape, you can use a List<Rectangle> anywhere the List<shape> is required. The collection types have the same subtyping relationship as the eleemtn types. Maps are covariant on the value type, but not on the key type. 

Mutable collections aren't covariant; otherwise, this would lead to runtime failures. If MutableList<Rectangle> was a subtype of MutableList<Shape>, you coudl insert other shape inhertors into it, thus viiolating its rectangle type argument. 

## Collection
Collection<T> is the root of the collection hierarchy. THis interface represents the common behavior of a read-only collection retrieving size, checking item memebership, and so on. Collection inherits from the Iterable<T> interface that defines the operations for iterating eleemmtns. YOu can use collection as a paramaeter of a function that applies to different  collection types. For more specific cases, use the Collecrion's inhertors List and Set.
```
fun printAll(strings: Collection<String>) {
        for(s in strings) print("$s ")
        println()
    }
    
fun main() {
    val stringList = listOf("one", "two", "one")
    printAll(stringList)
    
    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)
}
```

MutableCollection is collection with write operations, such as add and remove.
```

```

List stores elemetns in a specified order and provides indexed access to them. Indices start from zero -the index of the first elemetn and go to the lastIndex which is the list.size -1
```
val numbers = listOf("one", "two", "three", "four")
println("Number of elements: ${numbers.size}")
println("Third element: ${numbers.get(2)}")
println("Fourth element: ${numbers[3]}")
println("Index of element \"two\" ${numbers.indexOf("two")}")
```

List elements can include nulls and duplicat: a lst can contain any number of equal objects or occurences of a single object. Two lists are considere equal if they have the same size and structurally equal elements at the same positions. 
```
val bob = Person("Bob", 31)
val people = listOf(Person("Adam", 20), bob, bob)
val people2 = listOf(Person("Adam", 20), Person("Bob", 31), bob)
println(people == people2)
bob.age = 32
println(people == people2)
```

MutableList<T> is a list with list specific wite operations, add or remove an element at specfied position. 
```
val numbers = mutableListOf(1, 2, 3, 4)
numbers.add(5)
numbers.removeAt(1)
numbers[0] = 0
numbers.shuffle()
println(numbers)
```
