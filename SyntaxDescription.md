**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# Syntax Description #
Symja has an evaluation engine for mathematical expressions. The common arithmetic operations are:
  * `+` addition
  * `-` subtraction
  * `*` scalar multiplication
  * `/` division
  * `^` exponentiation
  * `.` matrix multiplication

A semicolon operator ";" is used to separate multiple expressions. The following example shows two expressions separated by the ";" operator, only the result for the second expression will be returned back after evaluation:
```
    100!;32+8+2
```
gives `42`.

## Identifiers ##
An identifier is a user-choosen or built-in name for a variable, function or constant. Symja distinguishes lower and upper case. All built-in functions are starting with an upper case charcter (`Sin[], Cos[], Expand[], Inverse[]`, etc). Built-in constants are also starting with an upper case letter (`E, Pi, Degree`, etc). If you type `SIN[x]` or `sin[x]`, Symja assumes you mean something other than the built-in `Sin[]` function. User-defined functions and variables typically start with a "$" character and can have names which are lower or upper case or both. `$foo[x*y], $Foo[x*y], $FOO[x*y]` are all different.

## Strings ##
A string is a representation of text as a sequence of characters. Strings must be surrounded by double quotes. An example string:
```
  "...Hello world..." 
```

# Expression types #

| **Type**	| **Description**	 | **Input Example** |
|:---------|:-----------------|:------------------|
|Integer	  |integer numbers 	 |` 42 `             |
|Rational	 |rational numbers 	|` 13/17 `          |
|Complex	  |complex numbers 	 |` 2+I*(1/3) `      |
|Real	     |double values 	   |` 0.5 `            |
|Complex Real	|complex double values 	|` 0.5-I*0.25 `     |
|Evaluation Precedence	|control precedence with ` (...) `|` (a+b)*c `        |
|Lists	    |comma separated list of elements which are sourrounded by ` { ... } `|` {a, b, c, d} `   |
|Vectors	  |vectors are like list, but cannot contain sublists ` { ... } `|` {1, 2, 3, 4} `   |
|Matrices	 |a matrix contains the rows as sublists 	|` {{1, 2}, {3, 4}} `|
|Predefined Functions	|predefined function names start with an upper case character and the arguments are sourrounded by ` [ ... ] `|` Sin[0], PrimeQ[13] `|
|Predefined Constants	|predefined constant names start with an upper case character 	|`Degree, E, Pi, False, True, ...`|
|Userdefined variables	|identifiers which you would like to assign a value start with a '`$`' character	|` $a=42 `          |
|Userdefined rules	|identifiers which you would like to assign a rule start with a '`$`' character |` $f{x_,y_]:={x,y} `|
|Pattern Symbols	|patterns end with a preceding '`_`' and could have a constraint 	|` $f[x_IntegerQ]:={x} `|
|Strings	  |character strings are enclosed by double quote characters 	|`"Hello World"`    |
|Slots	    |a '`#`' character followed by an optional number 	|`#` or `#2`        |
|Pure Functions	|pure functions can be expressed with the & operator 	|`(#^3)&[x]` gives `x^3`|
|Parts of an expression	|` expression[[index]] `|`{a, b, c, d}[[2]]` gives `b`|


# Examples #

Simplify an expression:
```
a+a+4*b^2+3*b^2
```

Factor an integer number:
```
FactorInteger[2^15-5]
```

Derivative of a function:
```
D[Sin[x^3],x]
```

Factor a polynomial:
```
Factor[-1+x^16]
```

Factor a polynomial modulo an integer:
```
Factor[5+x^12,Modulus->7]
```

Expand a polynomial:
```
Expand[(-1+x)*(1+x)*(1+x^2)*(1+x^4)*(1+x^8)]
```

Inverse of a matrix:
```
Inverse[{{1,2},{3,4}}]
```

Determinant of a matrix:
```
Det[{{1,2},{3,4}}]
```

See the [JUnit test cases](http://symja.googlecode.com/svn/trunk/matheclipse-core/src/test/java/org/matheclipse/core/system/SystemTestCase.java) for more syntax examples.