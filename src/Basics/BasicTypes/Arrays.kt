package Basics.BasicTypes

/*
Array instances cn be created using the arrayOf, ArrayOfNulls and emptyArray
 */
fun main() {
    arrayFunction()
}

fun arrayFunction() {

    var array1 = arrayOf(0, 1, 2, 3, 4, 5, 5, 25, 9, 8)
    var arrayNull= arrayOfNulls<Int>(5)
    var arrayEmpty = emptyArray<Int>()

    println("array1 ${array1.size}")
    println("arrayNull ${arrayNull.size}")
    println("arrayEmpty ${arrayEmpty.size}")

    println("array1 get 3 ${array1.get(3)}")
    println("arrayNull get 3 ${arrayNull.get(3)}")
    // Can't use get because Array is empty
    //println("arrayEmpty get 3 ${arrayEmpty.get(3)}")

    println("array1 iterator ${array1.iterator()}")
    println("arrayNull iterator ${arrayNull.iterator()}")
    println("arrayEmpty iterator ${arrayEmpty.iterator()}")

    println("array set pos 3 ${array1.set(3, 10)}")
    println("array set pos 3 ${array1.set(3, 3)}")
    println("arrayNull set pos 3 ${arrayNull.set(3, 11)}")


    println("array indices ${array1.indices}")
    println("arrayNull ${arrayNull.indices}")
    println("arrayEmpty ${arrayEmpty.indices}")

    println("array last index ${array1.last()}")
    println("arrayNull last index ${arrayNull.last()}")

    println("missing all")

    println("array1 any ${array1.any()}")
    println("arrayNull any ${arrayNull.any()}")
    println("arrayEmpty any ${arrayEmpty.any()}")

    println("array asIterable ${array1.asIterable()}")

    println("array assequenced ${array1.asSequence()}")

    println("array binary search:  ${array1.binarySearch(2, 0 , 5)}")

    println("array component1 ${array1.component1()}")

    println("array contains 2  ${array1.contains(2)}")

    println("array count ${array1.count()}")
    println("arrayNull count ${arrayNull.count()}")
    println("arrayEmpty count ${arrayEmpty.count()}")

    println("array distinct ${array1.distinct()}")

    println("array drop ${array1.drop(2)}")
    println("array dropLast ${array1.dropLast(5)}")

    println("array droplastWhile ${array1.dropLastWhile { it > 2 }}")

    println("array drop while ${array1.dropWhile { it == 5 }}")

    println("element at or else ${array1.elementAtOrElse(33){22}}")
    println("elemen at or null ${array1.elementAtOrNull(22)}")

    println("Filter % 2 ${array1.filter { it % 2 == 0 }}")

    println("filterInstance ${array1.filterIsInstance<Int>()}")

    println("find ${array1.find { it < 4 }}")

    println("findLast ${array1.findLast { it == 4 }}")

    println("first ${array1.first()}")
    println("first or null ${array1.firstOrNull()}")

    array1.iterator().forEach { println("Fore each $it")}

    println("get or else ${array1.getOrElse(2){22}}")

    println("get Index ${array1.indexOf(3)}")
    println("get first Index ${array1.indexOfFirst { 5 == 5  }}")
    println("get index of last ${array1.indexOfLast { 5== 5 }}")

    println("isArrayOf ${array1.isArrayOf<Boolean>()}")
    println("isArrayOf ${array1.isArrayOf<Int>()}")
    println("is empty ${array1.isEmpty()}")
    println("is not empty ${array1.isNotEmpty()}")
    println("is null or empty ${array1.isNullOrEmpty()}")
    println("ArrayNull is null or empty ${arrayNull.isNullOrEmpty()}")
    println("ArrayEmpty is null or empty ${arrayEmpty.isNullOrEmpty()}")

    println("last element ${array1.last()}")
    println("last index of ${array1.lastIndexOf(5)}")

    println("max ${array1.max()}, min ${array1.min()}")
    println("array none ${array1.none()}")
    println("arrayEmpty none ${array1.none()}")

    println("Random ${array1.random()}")

//    println("Reverse ${array1.reverse()}")
    println("Sort ${array1.sorted()}")
    println("Sort decending ${array1.sortedDescending()}")
    println("array 1, $array1")

    println("Sum array1 ${array1.sum()}")

    println("Take ${array1.take(4)}")
    println("take last ${array1.takeLast(4)}")
    println("take last while ${array1.takeLastWhile { it > 5 }}")
    println("take while ${array1.takeWhile { it <5 }}")

    array1.toHashSet().forEach { println("$array1")  }












}