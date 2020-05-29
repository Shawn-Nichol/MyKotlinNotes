package C_ClassesAndObjects.CA_ClassesAndInheritance

open class Rectangle {
    open fun draw() {println("Drawing a rectangle")}
    val borderColor: String get() = "black"
}