**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# About #
The math expression parser module contains numeric calculators for double and complex valued mathematical expressions. The parser is usable as a [Google Web Toolkit (GWT)](http://code.google.com/webtoolkit/) module. See the SyntaxDescription examples for the input syntax.

The parser is driven by an operator table as described in the [Wikipedia entry for Operator-precedence parsers](http://en.wikipedia.org/wiki/Operator-precedence_parser#Example_algorithm_to_parse_infix_notation). Quote from Wikipedia: _an operator precedence parser is a bottom-up parser that interprets an operator-precedence grammar. For example, most calculators use operator precedence parsers to convert from the human-readable infix notation with order of operations format into an internally optimized computer-readable format._

The latest API is available as a binary download:
  * http://code.google.com/p/symja/downloads/list

Javadoc:
  * http://symja.googlecode.com/svn/trunk/matheclipse-parser/doc/index.html

SVN Sourcecode tree:
  * http://code.google.com/p/symja/source/browse/#svn/trunk/matheclipse-parser

For the Maven integration see:
  * MavenSupport

## License ##
The parser is published under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

## Examples ##
The following examples show the numeric evaluation engines shipped with the parser package. For using the symbolic math engine see the [JSR223Support](JSR223Support.md) and RunSymja documents.

### Evaluate an expression in double mode ###

This is a JUnit example for evaluating a math string expression into a **double** number value:

```
	public void testEval001() {
		try {
			DoubleEvaluator engine = new DoubleEvaluator();
			double d = engine.evaluate("Sin[Pi/2*Cos[Pi]]");
			assertEquals(Double.toString(d), "-1.0");
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("", e.getMessage());
		}
	}

	public void testEval002() {
		try {
			String in = "x^2*x^2-1";
			IDoubleValue vd = new DoubleVariable(3.0);
			DoubleEvaluator engine = new DoubleEvaluator();
			engine.defineVariable("x", vd);
			double d = engine.evaluate(in);
			assertEquals(Double.valueOf(d).toString(), "80.0");
			vd.setValue(4);
			d = engine.evaluate();
			assertEquals(Double.valueOf(d).toString(), "255.0");
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("", e.getMessage());
		}
	}
```

### Evaluate an expression in complex mode ###

This is a JUnit example for evaluating a math string expression into a **Complex** number value:

```
	public void testEval001() {
		try {
			ComplexEvaluator engine = new ComplexEvaluator();
			Complex c = engine.evaluate("Sin[Pi/2*Cos[Pi]]");
			assertEquals(ComplexEvaluator.toString(c), "-1.0");
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("", e.getMessage());
		}
	}
	
	public void testEval002() {
		try {
			Parser p = new Parser();
			ASTNode obj = p.parse("x^2+3*x*I");
			ComplexVariable vc = new ComplexVariable(3.0);
			ComplexEvaluator engine = new ComplexEvaluator();
			engine.defineVariable("x", vc);
			Complex c = engine.evaluateNode(obj);
			String result = ComplexEvaluator.toString(c);
			assertEquals(result, "9.000000000000002+I*9.0");
			vc.setValue(4);
			c = engine.evaluateNode(obj);
			result = ComplexEvaluator.toString(c);
			assertEquals(result, "15.999999999999998+I*12.0");
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("", e.getMessage());
		}
	}
```

### Using the parser as a GWT module ###

In your GWT project module you have to insert the line:


```
    <inherits name="org.matheclipse.parser.Parser"/>
```

in your appropriate `*.gwt.xml` file.
The `matheclipse-parser-x.x.x.jar` has to be available on the classpath.

Example:
```
<module>
        <!-- Inherit the core Web Toolkit stuff. -->
        <inherits name="com.google.gwt.core.Core"/>
...
        <!-- Inherit the MathEclipse parser. -->
        <inherits name="org.matheclipse.parser.Parser"/>
...
</module>
```

  * See http://code.google.com/webtoolkit/documentation/com.google.gwt.doc.DeveloperGuide.Fundamentals.html#Modules

### The Console application ###

You can test the evaluation engine in the **org.matheclipse.parser.server.util.Console** application. Switch between the **double** and **complex** evaluation modes by typing the keywords **double** or **complex**.

The initial console looks like the following test output:

```
org.matheclipse.parser.server.util.Console [options]

Options: 
  -d or -double                use Double evaluation mode
  -c or -complex               use Complex evaluation mode
  -h or -help                  print this message
  -f or -file <filename>       use given file as input
To stop the program type: 
exit<RETURN-KEY>
To switch between the evaluation modes type: 
complex<RETURN-KEY> or
double<RETURN-KEY>
To continue an input line type '\' at the end of the line.
****+****+****+****+****+****+****+****+****+****+****+****+
>>> Sin[Pi/2*Cos[Pi]]
-1.0
>>> 
```

#### Parsing an abstract syntax tree (AST) ####
The parser maps the source code in a tree form called Abstract Syntax Tree (AST)
This is a JUnit example for parsing a math string expression into an abstract syntax tree node (ASTNode):

```
	public void testParser1() {
		try {
			Parser p = new Parser();
			ASTNode obj = p.parse("Integrate[Sin[x]^2+3*x^4, x]");
			assertEquals(obj.toString(), "Integrate[Plus[Power[Sin[x], 2], Times[3, Power[x, 4]]], x]");
		} catch (Exception e) {
			e.printStackTrace();
			assertEquals("", e.getMessage());
		}
	}
```

After the parsing step the obtained ASTNode can be converted into internal math objects.
The next example traverses/converts the ASTNode and creates math objects from the text representation inside the ASTNode objects.


The example is copied from the symbolic Symja core. See SVN:
  * http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/convert/AST2Expr.java

```
  /**
   * Converts a parsed ASTNode expression into an IExpr expression
   */
  @Override
  public IExpr convert(ASTNode node) throws ConversionException {
    if (node == null) {
      return null;
    }
    if (node instanceof FunctionNode) {
      final FunctionNode functionNode = (FunctionNode) node;
      final IAST ast = F.ast(convert((ASTNode) functionNode.get(0)));
      for (int i = 1; i < functionNode.size(); i++) {
        checkCanceled();
        ast.add(convert((ASTNode) functionNode.get(i)));
      }
      IExpr head = ast.head();
      if (head.equals(F.PatternHead)) {
        final IExpr expr = Pattern.CONST.evaluate(ast);
        if (expr != null) {
          return expr;
        }
      } else if (head.equals(F.BlankHead)) {
        final IExpr expr = Blank.CONST.evaluate(ast);
        if (expr != null) {
          return expr;
        }
      } else if (head.equals(F.ComplexHead)) {
        final IExpr expr = Complex.CONST.evaluate(ast);
        if (expr != null) {
          return expr;
        }
      } else if (head.equals(F.RationalHead)) {
        final IExpr expr = Rational.CONST.evaluate(ast);
        if (expr != null) {
          return expr;
        }
      }
      return ast;
    }
    if (node instanceof SymbolNode) {
      if (node.getString().equals("I")) {
        // special - convert on input
        return F.CI;
      }
      return F.symbol(node.getString());
    }
    if (node instanceof PatternNode) {
      final PatternNode pn = (PatternNode) node;
      return F.pattern((ISymbol) convert(pn.getSymbol()), convert(pn
          .getConstraint()));
    }
    if (node instanceof IntegerNode) {
      final IntegerNode integerNode = (IntegerNode) node;
      final String iStr = integerNode.getString();
      if (iStr != null) {
        return F.integer(iStr, integerNode.getNumberFormat());
      }
      return F.integer(integerNode.getIntValue());
    }
    if (node instanceof FractionNode) {
      FractionNode fr = (FractionNode) node;
      if (fr.isSign()) {
        return F.fraction((IInteger) convert(fr.getNumerator()),
            (IInteger) convert(fr.getDenominator())).negate();
      }
      return F.fraction(
          (IInteger) convert(((FractionNode) node).getNumerator()),
          (IInteger) convert(((FractionNode) node).getDenominator()));
    }
    if (node instanceof StringNode) {
      return F.stringx(node.getString());
    }
    if (node instanceof FloatNode) {
      return F.num(node.getString());
    }

    return F.symbol(node.toString());
  }

```

### Operator Table ###

The parsers operator table is driven by three arrays in the `org.matheclipse.parser.client.operator.ASTNodeFactory` class:

  * http://code.google.com/p/symja/source/browse/trunk/matheclipse-parser/src/main/java/org/matheclipse/parser/client/operator/ASTNodeFactory.java
  * `ASTNodeFactory#HEADER_STRINGS` - contains all internal names for the operators
  * `ASTNodeFactory#OPERATOR_STRINGS` - contains all operators used by the parser
  * `ASTNodeFactory#OPERATORS` - contains all PrefixOperator, InfixOperator and PostfixOperator definitions

There is a simplified way to create the Java sources for a given operator table.
Simply define an operator table in a text file similar to the
```
  /matheclipse-parser/src/main/resources/operators.txt
```
file given in the source code distribution.

After that run the `org.matheclipse.parser.server.util.GenerateOperatorArrays#main()` method.
It creates the source code for the three arrays in the Eclipse console. Copy these arrays back to the ASTNodeFactory and recompile the parser package.