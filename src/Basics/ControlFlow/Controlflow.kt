package Basics.ControlFlow

fun main() {

    ifExample()
    whenExample()
    forLoopsExamples()
    whileLoopExample()

}

fun ifExample() {

    println("if examples")
    var a = 5
    var b = 8

    // Expression
    var max = if(a > b) a else b
    println("max = $max")

    // Statement
    if(a > 3) {
        println("a is greater than 3")
    } else {
        println("a less than 3")
    }
}


fun whenExample() {
    println()
    println("When examples")

    var x: Int = 2
    // Statement
    when(x) {
        1 -> println("x = 1")
        2 -> println("x = 2")
        3, 4 -> println("x = 3 or 4") // check multiple values
        in 5..10 -> println("in between 5 and 10") // 'in' allows you to use a range, you can also use '!in'
        else ->{
            println("x is greater than 4")
        }
    }


    // Check type
    var myInt : Int = 3

    when (myInt) {
        is Int -> println("is a Int") // provides an error if types don't match.
        else -> {
            println("don't know what type myInt is")
        }
    }

    // Replace if else, no argument, supplied.


}

fun forLoopsExamples() {

    println()
    println("ForLoops")

    var myArray = arrayOf(1,2,3,4,5)

    // loop through a collection
    for(item in myArray) print(item)

    println()
    // Iterate through a range
    for(i in 1..5) {
        println(i)
    }

    // down to
    println("Down from six with 2 steps")
    for(i in 6 downTo 0 step 2) println(i)


    // Iterate through an array or list with an index
    print("Use Iterator to go through array")
    for(i in myArray.indices) {
        println(myArray[i])
    }

}


fun whileLoopExample() {
    println()
    println("While examples")
    var x = 0

    while (x <= 5) {
        println("X is less than 5")
        x++
    }
    println("the loop has ended x = $x")

}