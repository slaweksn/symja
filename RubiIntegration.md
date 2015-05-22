**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**

# The Rubi rule-based integrator library #
Description of implementation of the rules from the [Rubi rule-based integrator](http://www.apmaths.uwo.ca/~arich/).

The Rubi **`IndefiniteIntegrationRules`** and **`UtilityFunctions`** rules are converted to Java files in the package:
[org.matheclipse.core.integrate/rubi](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/integrate/rubi)

The rules were converted to Java files with the following classes:
  * [ConvertRubiFiles.java](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/test/java/org/matheclipse/core/test/ConvertRubiFiles.java)
  * [ConvertRubiUtilityFunctions.java](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/test/java/org/matheclipse/core/test/ConvertRubiUtilityFunctions.java)


# Notes #
  * the pattern-matching evaluation is disabled for the Integrate function in the method `org.matheclipse.core.eval.EvalEngine#evalASTBuiltinFunction()`
  * the pattern-matching evaluation is called again after some basic AST manipulations in the [org.matheclipse.core.reflection.system.Integrate](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Integrate.java) class, which implements the `Integrate[ ]` function.
  * if you set `SHOW_CONSOLE = true;` in the `org.matheclipse.basic.Config` class, the evaluation trace of the Rubi functions is printed to the Java console.