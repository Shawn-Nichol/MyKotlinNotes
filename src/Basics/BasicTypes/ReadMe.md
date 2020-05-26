Basic Types
- 
In Kotlin, everything is an object in the sense that we can call member functions and properties
on any variable. Some of the types can have a special internal representation

Basic Types: numbers, characters, booleans, arrays and strings

Numbers
-
Kotlin provides a set of built-in types that represent numbers

Byte: 8 bits, min: -128, max 127
Short 16 bits, min -32767 max 32767
Int 32 bits, min -2,147,483,648(-2 31) mas 2,147,483,647(2 31 -1) 
Long ...

All variables initialized with integer values not exceeding the maximum value of Int have 
inferred type Int.

For floating-point numbers, kotlin provides types Float and Double. According to the IEEE754 standard,
floating points types differ by their decimal place, that is, how man decimal digits they can store. 

Float: 32 bits 6 to 7 digits
Double: 64 bits, 15-16 digits

Variables are initialized with Double

Use Explicit conversion to convert numbers to different types.

Literal constants
- Decimal 123
  - Longs are tagged by a capital L
- Hexadecimals: 0x0F
- Binaries: 0b00001011
- Floats are tagged with an f

You can use underscores to make number constants more readable:

Representation
On the java platform, numbers are physically stored as JVM primitive types, unless we need
a nullable number reference or generics are involved. 

Every number type supports the following conversions:

- toByte(): Byte
- toShort(): Short
- toInt(): Int
- toLong(): Long
- toFloat(): Float
- toDouble(): Double
- toChar(): Char

Arithmetic operations + - * / %

Bitwise operations
- shl(bits) -signed shift left
- shr(bits) -signed shift right
- ushr(bits) - unsigned shift right
- and(bits) - bitwise and
- or(bits) -bitwise or
- xor(bits - bitwise xor
- inv() - bitwise inversion

Characters
-
Characters are represented by the type Char. They can not be treated directly as numbers
Character literals go inside single quotes. Special characters can be escaped using a 
backslash. The following escape sequence are support. 
\t, \b, \n, \r, \', \", \\ and \$

Booleans
-
The type boolean represents booleans, and has two values: true and false. Booleans are boxed
if a nullable reference is needed.
Built-in operations on booleans 
- || - lazy disjunction
- && - lazy conjunction
- ! -negation


Arrays
- 
Arrays in Kotlin are represented by the Array class, that has get and set functions(that turn
into [] by operator overloading convention), and size property.

Primitive type arrays:
Kotlin also has specialized classes to represent arrays of primitive types without boxing 
overhead: ByteArray, ShortArray, IntArray and so on. THese classes have no inheritance relation
to the Array class

Strings
- 
Strings are represented by the type String. Strings are immutable. Elements of a string
are characters that can be accessed by the indexing operation.

Escaping is done in the conventional way. 

A raw string use """, contains no escaping and can contain newlines and any other characters