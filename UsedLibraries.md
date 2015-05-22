**The information on this page is outdated; the Symja project has moved to [BitBucket.org](https://bitbucket.org/axelclk/symja_android_library).**



# Links to external open source libraries #
Used core libraries:
  * [Commons-Math - The Apache Commons Mathematics Library](http://commons.apache.org/math/) (Apache software licence)
  * [JAS - Java Algebra System](http://krum.rz.uni-mannheim.de/jas/) (LGPL software licence, [JAS Google Code project](http://code.google.com/p/java-algebra-system/))

Used libraries for the Symja-GUI
  * [Surfaceplotter](http://code.google.com/p/surfaceplotter/) (LGPL software licence)
  * [JLaTeXMath - A Java API to render LaTeX](http://forge.scilab.org/index.php/p/jlatexmath/) (GPL software licence)
  * [jsyntaxpane - Java EditorPane with support for Syntax Highlighting](http://code.google.com/p/jsyntaxpane) (Apache software licence)

Used in older versions of the GUI (up to version 0.0.10)
  * [JEuclid - MathML renderer](http://jeuclid.sourceforge.net/) (Apache software licence)

# Commons-Math functions #
The following functions are based on commons-math implementations:
  * `Det` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Det.java)) - [LUP-decomposition of a square matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/FieldLUDecomposition.html)
    * `Det[{{1,2},{3,4}}] gives -2`
  * `Eigenvalues` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Eigenvalues.java)) - [Calculates the eigen decomposition of a symmetric matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/EigenDecomposition.html)
    * `Eigenvalues[{{1,0,0},{0,1,0},{0,0,1}}] gives {1.0,1.0,1.0`}
  * `Eigenvectors` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Eigenvectors.java)) - [Calculates the eigen decomposition of a symmetric matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/EigenDecomposition.html)
  * `Fit` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Fit.java)) - [Levenberg-Marquardt optimizer](http://commons.apache.org/math/apidocs/org/apache/commons/math3/optimization/general/LevenbergMarquardtOptimizer.html)
    * `Fit[{{1,1},{2,4},{3,9},{4,16}},2,x] gives x^2.0`
  * `FindRoot` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/FindRoot.java)) - [BisectionSolver, BrentSolver, MullerSolver, NewtonSolver, RiddersSolver, SecantSolver](http://commons.apache.org/math/apidocs/org/apache/commons/math3/analysis/solvers/UnivariateSolver.html)
    * `FindRoot[Exp[x]==Pi^3,{x,-1,10}, Bisection] gives {x->3.434189647436142`}
  * `Inverse` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Inverse.java)) - [LUP-decomposition of a square matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/FieldLUDecomposition.html)
    * `Inverse[{{1,2},{3,4}}] gives {{-2,1},{3/2,-1/2`}}
  * `LinearProgramming` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/LinearProgramming.java)) - [simplex solver](http://commons.apache.org/math/apidocs/org/apache/commons/math3/optimization/linear/SimplexSolver.html)
    * `LinearProgramming[{-2, 1, -5}, {{1, 2, 0},{3, 2, 0},{0,1,0},{0,0,1}}, {{6,-1},{12,-1},{0,1},{1,0}}]` see [Introducing Apache Commons Math SimplexSolver](http://google-opensource.blogspot.com/2009/06/introducing-apache-commons-math.html)
  * `LinearSolve` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/LinearSolve.java)) - [LUP-decomposition of a square matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/FieldLUDecomposition.html)
    * `LinearSolve[{ { 1/10, 6/5, 1/9 },{ 1, 59/45, 1/10 },{6/5, 1/10, 1/9 } },{ 1/10, 6/5, 1/9 }] gives {99109/101673,10898/11297,-9034/869`}
  * `LUDecomposition` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/LUDecomposition.java)) - [LUP-decomposition of a square matrix](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/FieldLUDecomposition.html)
    * `LUDecomposition[{{1, 2, 3}, {3, 4, 11}, {13, 7, 8}}] gives {{{1,0,0},{3,1,0},{13,19/2,1}},{{1,2,3},{0,-2,2},{0,0,-50}},{1,2,3`}}
  * `NIntegrate` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/NIntegrate.java)) - [LegendreGaussIntegrator, RombergIntegrator, SimpsonIntegrator, TrapezoidIntegrator](http://commons.apache.org/math/apidocs/org/apache/commons/math3/analysis/integration/UnivariateIntegrator.html)
    * `NIntegrate[(x-1)*(x-0.5)*x*(x+0.5)*(x+1),{x,0,1},Simpson] gives -0.0208333320915699`
  * `SingularValueDecomposition` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/SingularValueDecomposition.java)) - [Singular Value Decomposition](http://commons.apache.org/math/apidocs/org/apache/commons/math3/linear/SingularValueDecomposition.html)


# Java Algebra System (JAS) functions #

  * `Apart` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Apart.java))
  * `Factor` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Factor.java))
    * `Factor[x^2-y^2] gives (y+x)*(-y+x)`
  * `GroebnerBasis` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/GroebnerBasis.java))
  * `FactorTerms` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/FactorTerms.java))
  * `Integrate` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Integrate.java))
  * `NRoots` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/NRoots.java))
  * `PolynomialGCD` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/PolynomialGCD.java))
    * `PolynomialGCD[-1+x^16,(x^2-1)*((1+x^4))] gives x^6-x^4+x^2-1`
  * `PolynomialQ` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/PolynomialQ.java))
  * `PolynomialQuotientRemainder` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/PolynomialQuotientRemainder.java))
  * `Roots` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Roots.java))
    * `Roots[x^3-4*x^2+x+6]` gives ` {3,2,-1} `
  * `RootIntervals` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/RootIntervals.java))
  * `Solve` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Solve.java)) uses `Roots` and `LinearSolve`.
    * `Solve[{x+2*y==10,3*x+y==20},{x,y}]` gives ` {{x->6,y->2}} `
  * `Together` - ([SVN](http://code.google.com/p/symja/source/browse/trunk/matheclipse-core/src/main/java/org/matheclipse/core/reflection/system/Together.java))

In the `edu.jas.kern.ComputerThreads` class the following switch lets you choose between single thread and multiple thread execution inside the JAS library:
```
...
        public static boolean NO_THREADS = true;
...
```...
        public static boolean NO_THREADS = true;
...
}}}```