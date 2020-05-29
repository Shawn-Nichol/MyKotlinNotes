package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
    Kotlin requires explicit modifiers for overridable members
    ```
        open class Shape {
            open fun draw() { }
            fun fill() {}
        }

        class Circle() : Shape() {
            override run draw() {}
        }


    ```

    Override it self is open, if you want prohibit re-overriding make the override class final.


    Overriding properties works in a similar way to overriding methods; properties declared on a superclass that are
    then redeclared on a derived class must be prefaced with override, and they must have a compatible type. Each declared
    property can be overridden by a property with an initializer or by a property with a get method.

    ```
        open class Shape {
            open val myCount: = 0
       }

       class Rectangle : shape {
            override val myCount = 4
        }
    ```

    You can override a val property with a var property but not vice versa. This allowed because val property essentially
    declares a get method, and overriding it as a var additionally declares a set method in the derived class.
 */