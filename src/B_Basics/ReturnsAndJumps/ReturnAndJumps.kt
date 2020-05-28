package B_Basics.ReturnsAndJumps

fun main() {
    foo()
    println("return from foo")
    foo2()
    foo3()
    foo4()
    foo5()
}

fun returnAtLabels() {


}

/*
 the return expression from the nearest enclosing functino
 */
fun foo() {
    println()
    println("foo")

    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // non-local return directly to the caller of foo()
        println(it)
    }
    println("this point is not reachable. ")
}

/*

 */
fun foo2() {
    println()
    println("foo2")
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) return@lit // local return to the caller of the lambdaie
        print(it)
    }
}

/*
 No it returns only from the lambda expression. Often it is more convenient to use implicit labels: such a label
 has the same name as the function to which the lambda is passed.
 */
fun foo3() {
    println()
    println("foo3")

    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) {
            println("$it now starts at ")
            return@forEach
        } // local return to the caller of the lambda, for each loop
        print(it)
    }
    println("done with implicit label")

}

/*
lambda replaced with an anonymous function.
 */
fun foo4() {
    println()
    println("foo4")

    listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
        if (value == 3) return // local return to the caller of the anonymous fun.
        print(value)
    })
    print("done with anonymous functions")
}

/*
 There is no direct equivalent for break, but it can be simulated by adding another nesting lambda and non-locally
 returning from it.
 */
fun foo5() {
    println()
    println("foo5")

    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            println(it)
        }
        print("done with nested loop")
    }
}


