package org.matheclipse.core.patternmatching;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.matheclipse.basic.Config;
import org.matheclipse.core.convert.AST2Expr;
import org.matheclipse.core.eval.interfaces.IFunctionEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.parser.client.Parser;
import org.matheclipse.parser.client.ast.ASTNode;

public class PatternMatcherAndInvoker extends PatternMatcher {
  /**
   * 
   */
  private static final long serialVersionUID = -2448717771259975643L;
  
  private transient Method fMethod;
  private transient Type[] fTypes;
  private final transient IFunctionEvaluator fInstance;

  /**
   * Create a pattern-matching rule which invokes the method name in the given
   * instance, if leftHandSide is matching.
   * 
   * @param leftHandSide
   *          could contain pattern expressions for "pattern-matching"
   * @param instance
   *          instance of an IFunctionEvaluator interface
   * @param method
   *          method to call
   */
  public PatternMatcherAndInvoker(final IExpr leftHandSide,
      IFunctionEvaluator instance, final String methodName) {
    super(leftHandSide);
    this.fInstance = instance;
    initInvoker(instance, methodName);
  }

  /**
   * Create a pattern-matching rule which invokes the method name in the given
   * instance, if leftHandSide is matching.
   * 
   * @param leftHandSide
   * @param instance
   * @param methodName
   */
  public PatternMatcherAndInvoker(final String leftHandSide,
      IFunctionEvaluator instance, final String methodName) {
    this.fInstance = instance;
    Parser parser = new Parser();
    ASTNode node = parser.parse(leftHandSide);
    IExpr lhs = AST2Expr.CONST.convert(node);
    fLeftHandSide = lhs;
    init(fLeftHandSide);
    initInvoker(instance, methodName);
  }

  private void initInvoker(IFunctionEvaluator instance, final String methodName) {
    Class<? extends IFunctionEvaluator> c = instance.getClass();
    Method[] declaredMethods = c.getDeclaredMethods();
    List<Method> namedMethods = new ArrayList<Method>();
    for (Method method : declaredMethods) {
      if (method.getName().equals(methodName))
        namedMethods.add(method);
    }
    if (namedMethods.size() == 1) {
      this.fMethod = namedMethods.get(0);
      this.fTypes = fMethod.getGenericParameterTypes();
    } else {
      // throw an exception ?
    }
  }

  /**
   * Match the (left-hand-side) pattern with the given expression. If true
   * evaluate the right-hand-side for the determined values of the patterns
   * 
   * @param ee
   * @param evalExpr
   * @return
   */
  // public IExpr eval(final IExpr leftHandSide) {
  // // if(fRightHandSide.isAST("Condition")) {
  // // System.out.println("2:"+fRightHandSide);
  // // }
  // if (fPatternCounter == 0) {
  // // no patterns found match equally:
  // if (fLeftHandSide.equals(leftHandSide)) {
  // return fRightHandSide;
  // }
  // return null;
  // }
  // initPattern();
  // if (matchExpr(fLeftHandSide, leftHandSide) && checkCondition()) {
  // return EvaluationSupport.substituteLocalVariables(fRightHandSide,
  // fPatternSymbolsArray, fPatternValuesArray);
  // }
  // return null;
  // }
  public IExpr eval(final IExpr leftHandSide) {
    IExpr result = null;
    if (fPatternCounter == 0 && fLeftHandSide.equals(leftHandSide)) {
      if (fTypes.length != 0) {
        return null;
      }
      try {
        result = (IExpr) fMethod.invoke(fInstance);
      } catch (IllegalArgumentException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      } catch (IllegalAccessException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      } catch (InvocationTargetException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      }
      return result;
    }
    if (fTypes.length != fPatternSymbolsArray.size()) {
      return null;
    }
    initPattern();
    if (matchExpr(fLeftHandSide, leftHandSide) && checkCondition()) {

      IExpr arg;
      List<IExpr> args = new ArrayList<IExpr>(fPatternSymbolsArray.size());
      for (int i = 0; i < fPatternSymbolsArray.size(); i++) {
        arg = fPatternValuesArray[i];
        if (arg == null)
          return null;
        args.add(arg);
      }
      result = null;
      try {
        result = (IExpr) fMethod.invoke(fInstance, args.toArray());
      } catch (IllegalArgumentException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      } catch (IllegalAccessException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      } catch (InvocationTargetException e) {
        if (Config.SHOW_STACKTRACE) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof PatternMatcherAndInvoker)) {
      return false;
    }
    // don't compare fInstance, fMethod, fTypes here
    return super.equals(obj);
  }
}