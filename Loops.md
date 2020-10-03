# For loop
A for loop implicitly declares a new read-only variableif the outer scope already conteains a vairable with the same name, it will be shadowed by the  unrelated loop variable. For the same reason, the final valu eof the loop variable is not accessible after the loop. 
```
val names = listOf("Shawn", "Peter", "Jeff")
for name in names) {
  println(name)
}
```


## Range ..
Use .. to create a operator, last value is included. 
```
for(x in 0..10) println(x)
```

if you want to exclude the last value use until
```
for(x in 0 until 10) println(x)
```

The steps can be incremented with step then the value
```
for(x in 0..10 steps 2) println(x) // prints 0, 2, 4, 6, 8, 10
```

The step value must be positive, if you need to count downwards use downto
```
for(x in 10 downTo 0 step 2) println(x) // prints 10, 8, 6, 4, 2, 0
```

Any expressions to the right of 'in' in the loops can also be used outside of loops in order to generate ranges, which can be iterated over later or turned into lists. 
```
val numbers = (0..9).toList()
```

You can get the index of the current element by when your're iterating, you can use withINdex(), which corresponds to enumerate(). It producesa sequence of objects that have got two properties and two specially named accessor functions called component1() and component2()
```
for((index, value) in names.withIndex()) {
  println("$index: $value")
}
```

## Iterator over a map
Iterate over the entires as objectst that contina the key and the value as properties
```
for(entry in map) {
  println("${entry.key}: ${entry.map}")
}
```

Iterate over the entires as separate key and value objects
```
for((key, value) in map) {
  println("$key: $value")
}
```

Iterate over the keys
```
for(key in map.keys) {
  println(key)
}
```

Iteterate over the values
```
for(value in map.values) {
  println(values)
}
```
