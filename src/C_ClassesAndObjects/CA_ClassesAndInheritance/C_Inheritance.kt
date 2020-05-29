package C_ClassesAndObjects.CA_ClassesAndInheritance

/*
all class in Kotlin have a common super class 'Any', it is the default super type with no supertypes declared.

'Any' has three methods: equals(), hashCode(), toString().

By default Kotlin class are final, To make a class inheritable mark it as open.
```
    open class Base
```

 To declare a supertype place a colon after the class header
 ```
    open class base(p: Int) : Base(p)
 ```

 If there is no primary constructor each secondary constructor has initialize the base type using the super keyword.
 ```
    class MyView: View {
        constructor(ctx: Context) : super(ctx)
        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
    }
 ```




 */