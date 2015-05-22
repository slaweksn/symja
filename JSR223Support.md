**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**

**Note:** this feature is available since version 0.0.8.

MathEclipse is usable as a [JSR 223](http://en.wikipedia.org/wiki/JSR_223) scripting engine.

  * For using the scripting engine with the Java **jrunscript** tool see the document about [running Symja](RunSymja.md).
  * The [Seco](http://code.google.com/p/seco/) project also has an interesting JSR 223 interface. Watch the video tutorials at [Seco at Kobrix.com](http://www.kobrix.com/seco.jsp)

Example:
```
...
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import junit.framework.TestCase;

import org.matheclipse.core.eval.SystemNamespace;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.script.engine.MathScriptEngine;
public class ScriptEngineTestCase extends TestCase {
	public ScriptEngineTestCase() {
		super("ScriptEngineTestCase");
	}

```

Initialize the scripting engine:
```

	public void testScriptEngine() {
		String stringResult = null;
		ScriptEngineManager scriptManager = new ScriptEngineManager();
		ScriptEngine engine_1 = scriptManager.getEngineByExtension("m");

```

Evaluate a given expression `D[Sin[x]*Cos[x],x]`:
```
		try {
			stringResult = (String) engine_1.eval("D[Sin[x]*Cos[x],x]");
			assertEquals("Cos[x]^2-Sin[x]^2", stringResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
```

or `Expand[(x+5)^3]`:
```
		try {
			stringResult = (String) engine_1.eval("Expand[(x+5)^3]");
			assertEquals("125+75*x+15*x^2+x^3", stringResult);
			stringResult = (String) engine_1.eval("Factor[" + stringResult
					+ "]");
			assertEquals("(5+x)^3", stringResult);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
```

Define variables `$x` and `$y`:
```
		try {
			engine_1.put("$x", new Boolean(true));
			engine_1.put("$y", new Boolean(true));
			stringResult = (String) engine_1.eval("$x && $y");
			assertEquals("True", stringResult);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
```

Assuming that the `c:\temp\test.m` script file contains this code:
```
  $m={$x, $y, {13, 7, 8}}; $m.$m
```

we can now define values for the variables $x and $y (which contain the first and second row of the matrix $m) and multiply the matrix $m by itself:
```
    try {// a script file test
      ArrayList<Object> row = new ArrayList<Object>();
      row.add("List"); // head of the expression
      row.add(Integer.valueOf(1));
      row.add(Integer.valueOf(2));
      row.add(Integer.valueOf(3));

      int[] intArr = { 3, 4, 11 };

      engine_1.put("$x", row);
      engine_1.put("$y", intArr);
// the test.m file contains this script for matrix multiplication:
// $m={$x, $y, {13, 7, 8}};
// $m.$m

      ScriptContext context = engine_1.getContext();
      context.setAttribute(MathScriptEngine.RETURN_OBJECT, Boolean.TRUE,
          ScriptContext.ENGINE_SCOPE);
      Object objectResult = engine_1
          .eval(new FileReader(
              "C:\\temp\\test.m"));
// print result for matrix multiplication: {{1,2,3}, {3, 4, 11},
// {13, 7, 8}}.{{1,2,3}, {3, 4, 11}, {13, 7, 8}}
      assertEquals("{{46, 31, 49}, {158, 99, 141}, {138, 110, 180}}",
          objectResult.toString());
      if (objectResult instanceof IExpr) {
        // decompose the matrix into rows
        IExpr expr = (IExpr) objectResult;
        // gives the head "List", because matrices are list of row-lists
        assertEquals("List", expr.head().toString());

        if (expr instanceof List) {
          // use java.util.List to print the rows
          List<IExpr> list = (List<IExpr>) expr;
          for (IExpr subExpr : list) {
            System.out.println(subExpr);
          }
          IExpr subExpr;
          // there's a difference between foreach and for loop
          // because the head is stored at index 0:
          for (int i = 0; i < list.size(); i++) {
            subExpr = list.get(i);
            System.out.println(subExpr);
          }
        }

        if (expr instanceof IAST) {
          // use org.matheclipse.core.interfaces.IAST to print the
          // rows
          IAST list = (IAST) expr;
          for (IExpr subExpr : list) {
            System.out.println(subExpr);
          }
          IExpr subExpr;
          // there's a difference between foreach and for loop
          // because the head is stored at index 0:
          for (int i = 0; i < list.size(); i++) {
            subExpr = list.get(i);
            System.out.println(subExpr);
          }
        }

      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
```