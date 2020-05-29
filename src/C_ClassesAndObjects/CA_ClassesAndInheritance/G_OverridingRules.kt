package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
In Kotlin, implementation inheritance is regulated by the following rule: if a class inherits multiple implementations of
the same  member from its immediate superclasses, it must override this member and provide its own implementation. To
denote the supertype from which the inherited implementation is take, we use super qualified by the super tpe name in
angle Bracket
 */


/*
Interface members are 'open by default
 */
interface Polygon {
    fun draw() {
        println("draw a polygon")
    }
}

/*
It's fine to inherit from both Rectangle and Polygon, but both of them have their implementations of draw(), so we have to
override draw() in Square and provide its own implementation that eliminates the ambiguity.
 */
class Square() : Rectangle(), Polygon {
    // The compiler requires draw to be overridden:
    override fun draw() {
        super<Rectangle>.draw()
        super<Polygon>.draw()
    }
}


fun main() {
    val square = Square()
    square.draw()
}