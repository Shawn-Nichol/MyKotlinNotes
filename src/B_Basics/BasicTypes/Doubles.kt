package B_Basics.BasicTypes

import java.util.concurrent.TimeUnit
import kotlin.Double.Companion.MAX_VALUE
import kotlin.Double.Companion.MIN_VALUE
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
fun main() {
    doubleFunction()
    doubleExtensionProperties()
}

fun doubleFunction() {
    var myDouble = 2.2

    println("Compare to 2.2 ${myDouble.compareTo(2.2)}")
    println("Compare to 4.2 ${myDouble.compareTo(4.2)}")

    println("decrement ${myDouble.dec()}")
    println("divide by 2  = ${myDouble.div(2)}")
    println("equals ${myDouble.equals(2.2)}")
    println("equals 4.2 ${myDouble.equals(4.2)}")

    println("hashcode ${myDouble.hashCode()}")
    println("increments this value ${myDouble.inc()}")
    println("minus ${myDouble.minus(1.0)}")
    println("plus ${myDouble.plus(5)}")
    println("times 4 ${myDouble.times(4)}")
    println("to bytes ${myDouble.toByte()}")
    println("to char ${myDouble.toChar()}")

    println("unaryMinus ${myDouble.unaryMinus()}")
    println("max value $MAX_VALUE")
    println("min value $MIN_VALUE")

    // NaN : is a constant holding the not a number value of double.


}

@ExperimentalTime
fun doubleExtensionProperties() {
    var myDouble = 4.4

    println()
    println("Extension Properties")
    //println("days ${myDouble.days}")

    println("to big decimal ${myDouble.toBigDecimal()}")
    println("duration seconds ${myDouble.toDuration(TimeUnit.SECONDS)}")
    println("duration minutes ${myDouble.toDuration(TimeUnit.MINUTES).inMinutes}")
    println("duration hours ${myDouble.toDuration(TimeUnit.HOURS).inHours}")
    println("duration days ${myDouble.toDuration(TimeUnit.DAYS)}")

    println("toULong ${myDouble.toULong()}")


}