package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
A class and some of its members may be declared abstract. An abstract member does not have an implementation in its class.
Note that we don not need to annotate an abstract class or function with open -it goes with saying.

We can override a non-abstract open member with an abstract one.
 */

open class abPolygon {
    open fun draw() {

    }
}

abstract class abRectangle : abPolygon() {
    abstract override fun draw()
}

fun main() {
    val rec = abRectangle()}