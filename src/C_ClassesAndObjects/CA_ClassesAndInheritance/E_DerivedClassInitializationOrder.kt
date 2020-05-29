package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
During construction of a new instance of derived class, the base class initialization is done as the first step(preceded
only by evaluation of the arguments for the base class constructor) and thus happens before the initialization logic.

```
    Open class base(val name: String) {
        init {println("Initializing Base")}

        open val size Int =
            name.length.also {println("Initializing size in Base: $it")}
    }

    class Derived(
        name: String,
        val lastName: String
    ) : Base(name.capitalize().also {println("argument for Base: $it") }) {

        init {println("Initializing Derived") }

        Override val size: Int =
            (super.size + lastName.length).also{println(""Initializing size in Derived: $it")}
   }

```

By the time the base class constructor executes, the properties declared or overridden in the derived class are not yet
initialized. If any of those properties are used in the base class initialization logic either directly or indirectly,
through another overridden open member implementation. When designing a base class you should avoid using open members
in the constructor.

 */