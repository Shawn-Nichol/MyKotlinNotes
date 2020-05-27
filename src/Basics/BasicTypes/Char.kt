package Basics.BasicTypes


import java.lang.Character.*
import kotlin.Char.Companion.MAX_HIGH_SURROGATE
import kotlin.Char.Companion.MAX_LOW_SURROGATE
import kotlin.Char.Companion.MAX_SURROGATE
import kotlin.Char.Companion.MAX_VALUE
import kotlin.Char.Companion.SIZE_BITS
import kotlin.Char.Companion.SIZE_BYTES

/*
Char Represents a 16 - bit unicode character.
 */
fun main() {

    charFunction()
    charCompanionObject()
    charExtensionFunctions()
}

fun charFunction() {
    // Use single quotes, Double returns an error.
    val char1 = 'A'
    val char2 = 'A'

    println("Compare char1 to A:  ${char1.compareTo('A')}")
    println("Compare char1 to B: ${char1.compareTo('B')}")

    println("Dec char1: $char1")

    // Indicates whether some other object is equal to this one.
    println("char1 equals A: ${char1.equals('A')}")
    println("char1 equals B: ${char1.equals('B')}")

    println("Hashcode char1 = ${char1.hashCode()}")
    println("Hashcode char2 = ${char2.hashCode()}")

    println("increment char1 ${char1.inc()}")
    // Returns an int value
    println("minus char1 ${char1.minus('b')}")
    println("plus char1 ${char1.plus(33)}")

    println("Return char1 as a byte ${char1.toByte()}")
    println(char1)
    println("char1 to Int ${char1.toInt()}")




}

fun charCompanionObject() {
    var char1 = 'A'


    println()
    println("Companion Object")
    println("Max code point = $MAX_CODE_POINT")
    println("Max low Surrogate = $MAX_LOW_SURROGATE")
    println("Max high surrogate = $MAX_HIGH_SURROGATE")
    println("Max Radix $MAX_RADIX")
    println("Max surrogate = $MAX_SURROGATE")
    println("Max value = $MAX_VALUE")
    println("Min Code Point = $MIN_CODE_POINT")

    println("Min value = $MIN_VALUE")
    println("Size Bits = $SIZE_BITS")
    println("Size Bytes = $SIZE_BYTES")

}

fun charExtensionFunctions() {
    var char1 = 'D'
    var char2 = 'b'

    println()
    println("Extension function")
    println("coerceAtLeast ${char1.coerceAtLeast('b')}")
    println("coerceAtLeast ${char1.coerceAtLeast('E')}")

    println("char1 equal D ${char1.equals('D')}")
    println("char1 equals char2 ${char1.equals(char2)}")

    println("Title case ${char1.isTitleCase()}")
    println("Concatnate with plus: ${char1.plus(" testing")}")

    println("To title ${char2.toTitleCase()}")
    println("Until ${char1.until('s')}")



}