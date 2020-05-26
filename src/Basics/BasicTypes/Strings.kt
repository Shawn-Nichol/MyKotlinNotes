package Basics.BasicTypes

fun main() {

    var myString = "This is my string"
    println(myString)


    stringProperties(myString)
    stringFunction(myString)
    stringExtension(myString)
    stringFunction(myString)
}

/*
String properties
 */
fun stringProperties(myString: String) {
    // length returns Int
    println("myString length = ${myString.length}")

}

/*
String Functions
 */
fun stringFunction(myString: String) {
    var  myString2: String = "My second String"

    println()
    println("String Function")

    println("Compare myStringOne to myStringTwo: ${myString.compareTo(myString2)}")

    // equals
    println("myString equals the second string ${myString.equals(myString2)}")
    println("myString equals the ${myString.equals("This is my string")}")

    // get, get can be replaced with indexing
    println("get Char at position ${myString.get(2)}" )

    // Returns the hashcode
    println("hashcode: ${myString.hashCode()}")

    // Plus, does not concatenate the string
    println("Plus ${myString.plus(" add plus")}")
    println("plus $myString")


    // Returns the Characters in that range from the string, includes white space
    println("myString subSequence: ${myString.subSequence(3, 12)}")
}

/*
Extension Properties
 */
fun stringExtension(myString: String) {
    println()
    println("Extension")

    // Return the Int Range of the string ex 0..16
    println("myString indices ${myString.indices}")

    // last Index
    println("my string lastIndex ${myString.lastIndex}")

}


fun stringExtensionFunction(myString: String) {
    var string2: String = "Test"
    // ALL
}
