package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
Code in a derived class can call its super class functions adn property accessors implementations using the super keyword.
*/
open class Rectangle {
    open fun draw() {println("Drawing a rectangle")}
    val borderColor: String get() = "black"
}

class FilledRectangle: Rectangle() {


    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fill : String get() = super.borderColor

    inner class Filler {
        fun fill2() {
            println("fill rec")
        }
        fun drawAndFill() {
            super@FilledRectangle.draw()
            fill2()
            println("Drawn a filled rectangle ${super@FilledRectangle.borderColor}")
        }
    }
}


/*
Inside an inner class, accessing the superclass of the outer class is done with the super keyword qualified with the outer
class name 'super@Outer'
 */




fun main() {
    println("superClassImplementation")
    val rec = FilledRectangle()
    rec.draw()
    println()
    println("Inner class")
    rec.Filler().drawAndFill()


}


