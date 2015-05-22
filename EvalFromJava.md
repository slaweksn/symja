**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**

Before writing a Java program you can interactively test your expressions in the [console application](http://code.google.com/p/symja/wiki/RunSymja#The_console_application).

**Note**: Symja requires Java 1.6 compliance level.



# Direct Java integration #
```
package org.matheclipse.examples;

import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.form.output.OutputFormFactory;
import org.matheclipse.core.form.output.StringBufferWriter;
import org.matheclipse.core.interfaces.IExpr;

import edu.jas.kern.ComputerThreads;

public class EvalExpand {
  public static void main(String[] args) {
    // Static initialization of the MathEclipse engine instead of null 
    // you can set a file name to overload the default initial
    // rules. This step should be called only once at program setup:
    F.initSymbols(null);
    EvalUtilities util = new EvalUtilities();

    IExpr result;

    try {
      StringBufferWriter buf = new StringBufferWriter();
      String input = "Expand[(AX^2+BX)^2]";
      result = util.evaluate(input);
      OutputFormFactory.get().convert(buf, result);
      String output = buf.toString();
      System.out.println("Expanded form for " + input + " is " + output);

      // set some variable values
      input = "A=2;B=4";
      result = util.evaluate(input);

      buf = new StringBufferWriter();
      input = "Expand[(A*X^2+B*X)^2]";
      result = util.evaluate(input);
      OutputFormFactory.get().convert(buf, result);
      output = buf.toString();
      System.out.println("Expanded form for " + input + " is " + output);
      
      buf = new StringBufferWriter();
      input = "Factor["+output+"]";
      result = util.evaluate(input);
      OutputFormFactory.get().convert(buf, result);
      output = buf.toString();
      System.out.println("Factored form for " + input + " is " + output);
    } catch (final Exception e) {
      e.printStackTrace();
    } finally {
      // Call terminate() only one time at the end of the program  
      ComputerThreads.terminate();
    }

  }
}
```

## User variable spaces ##
In the AJAX server interface the `SERVER_MODE` is set to `true`, to ensure that only variable identifiers starting with a '$' character could be assigned a new value or rule. The standard default functions like `Cos, Sin,...` and symbolic variable names like `x, y, z...` could not be redefined or enhanced. So every logged in user has his own _$ variable space_ which is separated from another users _$ variable space_.

```
...
    F.initSymbols(null);
    Config.SERVER_MODE = true;
...
```

# JSR223 based scripting engine support #
Here is a simple example for the JSR 223 support. More examples can be found here: [JSR223Support](JSR223Support.md).

```
package org.matheclipse.examples;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import edu.jas.kern.ComputerThreads;

public class EvalExpandJSR223 {
  public static void main(String[] args) {
    ScriptEngineManager scriptManager = new ScriptEngineManager();
    ScriptEngine engine_1 = scriptManager.getEngineByExtension("m");

    try {
      String input = "Expand[(AX^2+BX)^2]";
      String output = (String) engine_1.eval(input);
      System.out.println("Expanded form for " + input + " is " + output);

      // set some variable values 
      input = "A=2;B=4";
      engine_1.eval(input);

      input = "Expand[(A*X^2+B*X)^2]";
      output = (String) engine_1.eval(input);
      System.out.println("Expanded form for " + input + " is " + output);

      input = "Factor[" + output + "]";
      output = (String) engine_1.eval(input);
      System.out.println("Expanded form for " + input + " is " + output);

    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      // Call terminate() only one time at the end of the program
      ComputerThreads.terminate();
    }

  }
}
```