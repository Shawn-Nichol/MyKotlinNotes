package B_Basics.BasicTypes

import kotlin.Float.Companion.MAX_VALUE
import kotlin.Float.Companion.MIN_VALUE
import kotlin.Float.Companion.NEGATIVE_INFINITY
import kotlin.Float.Companion.NaN
import kotlin.Float.Companion.POSITIVE_INFINITY

fun main() {
    floatFunction()
}

fun floatFunction() {
    var  myFloat = 13.3
    var  myFloat2 = 12

    println("compare myFloat to ${myFloat.compareTo(22)}")
    println("compare myFloat to ${myFloat2.compareTo(12)}")

    println("Decrement = ${myFloat.dec()}")

    println("Divide by 2 = ${myFloat.div(2)}")

    // Indicates whether some other object is equal to this one, Implementations must fulfil the following requirements:
    println("myFloat Equals myFloat2 ${myFloat.equals(myFloat2)}")
    println("myFloat Equals 13.0 ${myFloat.equals(13.0)}")

    println("hashcode ${myFloat.hashCode()}")
    println("hascode myFloat2 ${myFloat2.hashCode()}")

    println("increments myFloat ${myFloat.inc()}")

    println("UnaryMinus ${myFloat.unaryMinus()}")
    println("Unaryminus myFloat2 ${myFloat2.unaryMinus()}")

    println("UnaryPlus myFloat ${myFloat.unaryPlus()}")
    println("UnaryPlus myFloat2 ${myFloat.unaryPlus()}")

    println("Max value $MAX_VALUE")
    println("min value $MIN_VALUE")

    println("Nan holding not a number value to float $NaN")
    println("Negative infinity $NEGATIVE_INFINITY")
    println("Positive infinity $POSITIVE_INFINITY")

    println("coerceAtLeast 18 ${myFloat.coerceAtLeast(18.0)}")
    println("coerceAtLeast 1 ${myFloat.coerceAtLeast(1.0)}")

    println("coerceAtMost 100 ${myFloat.coerceAtMost(100.0)}")
    println("coerceAtMost 5 ${myFloat.coerceAtMost(5.0)}")

    println("coerceIn 4 to 100 ${myFloat.coerceIn(4.0, 100.0)}")
    println("coerceIn 80 to 100 ${myFloat.coerceIn(80.0, 100.0)}")
    println("float range from myFloat to 100 ${myFloat.rangeTo(100.0)}}")

    println("to big decimal ${myFloat.toBigDecimal()}")
    println("toUInt ${myFloat.toUInt()}")
    println("toUlong ${myFloat.toULong()}")




}