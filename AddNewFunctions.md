**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# Object Hierarchy #

All atomic math objects like integer numbers (`IntegerSym`), fractional numbers (`FractionSym`), complex numbers (`ComplexSym`), numerical numbers (`Num, ComplexNum`), patterns (`Pattern`), strings (`StringX`) or symbols (`Symbol`) are derived from `org.matheclipse.core.expression.ExprImpl`

The parser maps the source code of math functions (like `Sin[x], a+b+c, PrimeQ[17],...`) in a **tree form** called **Abstract Syntax Tree (AST)**. These functions are represented as AST objects (derived from `IAST`, `IExpr` and `java.util.List` interfaces). The **head** (i.e. `Sin, Plus, PrimeQ,...`) of the function is stored at index `0` in the list. The `n` arguments of the function are stored in the indexes `1..n`.

For example the function `f[x,y,z]` is internally represented by an AST derived from the `java.util.ArrayList`: `[ f, x, y, z ]`. Of course these lists can be nested and form a tree of `java.util.List`s and other atomic math objects (i.e. `f[x,y,g[u,v,h[w,3]]]` is represented by the nested AST structure: `[ f, x, y, [ g, u, v [ h, w, 3 ] ] ]`).

Here is a hierarchy overview of the classes, which implement the internal math expression representation:

![http://symja.googlecode.com/svn/wiki/MathEclipseObjectHierarchy.png](http://symja.googlecode.com/svn/wiki/MathEclipseObjectHierarchy.png)

You can use the **F factory** ([org.matheclipse.core.expression.F](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/expression/F.java)) to create the objects of the type hierarchy.

# Function Interfaces #
For your own Java math functions you have to add the Java package name to the system namespace:
```
SystemNamespace.DEFAULT.add("<your.own.package.name>");
```

In this package you have to define a class which is derived from the [org.matheclipse.core.eval.interfaces.IFunctionEvaluator](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/eval/interfaces/IFunctionEvaluator.java) or [org.matheclipse.core.eval.interfaces.AbstractFunctionEvaluator](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/eval/interfaces/AbstractFunctionEvaluator.java) interfaces.

If you would like to implement a new `MyFunction[<argument>]` function, you can for example derive a class with the same name (`MyFunction`) from `AbstractFunctionEvaluator`. In this class you have to define a public default constructor and an `evaluate()` method. The `evaluate()` method can return `null` if no new result could be evaluated.

```
public class MyFunction extends AbstractFunctionEvaluator {

  public MyFunction() {
  }

  @Override
  public IExpr evaluate(final IAST functionList) {
...

...
  }
}
```

# Example #

You can find a lot of examples in the [org.matheclipse.core.reflection.system package](http://code.google.com/p/symja/source/browse/#svn/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system).

Add your own package to the system namespace:
```
SystemNamespace.DEFAULT.add("org.matheclipse.test.function");
```

After defining a new `MyFuntion[]` function in your own package you can evaluate an expression like `MyFunction[Sin[x]*Cos[x]]`
```
package org.matheclipse.test.function;

import org.matheclipse.core.eval.interfaces.AbstractFunctionEvaluator;
import org.matheclipse.core.expression.AST;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;

/**
 * Calculate the <b>depth</b> of an expression 
 */
public class MyFunction extends AbstractFunctionEvaluator {

  public MyFunction() {
  }

  @Override
  public IExpr evaluate(final IAST functionList) {
    if (functionList.size() != 2) {
      // The size has to be 2 because the head is stored at index 0 and 
      // the argument is stored at index 1
      return null;
    }
    if (!(functionList.get(1) instanceof IAST)) {
      //constant integer 1:
      return F.C1;
    }
    return F.integer(AST.COPY.depth((IAST) functionList.get(1), 1));
  }

}
```

# Building expressions #
With the **`JavaForm[]`** function you can convert a Symja expression into internal Java form.
This example:
```
JavaForm[I/2*E^((-I)*x)-I/2*E^(I*x)]
```
gives the Java expression:
```
Plus(Times(complex(0L,1L,1L,2L),Power(E,Times(CNI,x))),Times(complex(0L,1L,-1L,2L),Power(E,Times(CI,x))))
```

If you do a static import of the **`F`** factory like this in your classes:
```
import static org.matheclipse.core.expression.F.*;
```
you can use the above Java expression to create the **`IExpr`** instances.

With the **`eval()`** method you can evaluate the above expressions:
```
F.eval(<your expression here>);
```
to new **`IExpr`** instances.