package org.matheclipse.core.reflection.system;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.eval.exception.ReturnException;
import org.matheclipse.core.eval.exception.RuleCreationError;
import org.matheclipse.core.eval.exception.Validate;
import org.matheclipse.core.eval.interfaces.ICreatePatternMatcher;
import org.matheclipse.core.eval.interfaces.IFunctionEvaluator;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.ISymbol;

public class Set implements IFunctionEvaluator, ICreatePatternMatcher {
	public static IExpr evalLeftHandSide(IExpr leftHandSide) {
		return evalLeftHandSide(leftHandSide, EvalEngine.get());
	}

	public static IExpr evalLeftHandSide(IExpr leftHandSide, final EvalEngine engine) {
		if (leftHandSide instanceof IAST) {
			final IExpr temp = engine.evalSetAttributes((IAST) leftHandSide);
			if (temp != null) {
				leftHandSide = temp;
			}
		}
		return leftHandSide;
	}

	public Set() {
	}

	public IExpr evaluate(final IAST ast) {
		Validate.checkSize(ast, 3);
		final IExpr leftHandSide = ast.get(1);
		final IExpr rightHandSide = ast.get(2);
		Object[] result;
		if (rightHandSide.isAST(F.Condition, 3)) {
			result = createPatternMatcher(leftHandSide, ((IAST) rightHandSide).get(1), ((IAST) rightHandSide).get(2));
		} else {
			result = createPatternMatcher(leftHandSide, rightHandSide, null);
		}
		return (IExpr) result[1];
	}

	public Object[] createPatternMatcher(IExpr leftHandSide, IExpr rightHandSide, IExpr condition) throws RuleCreationError {
		final Object[] result = new Object[2];
		final EvalEngine engine = EvalEngine.get();

		leftHandSide = evalLeftHandSide(leftHandSide, engine);

		try {
			rightHandSide = engine.evaluate(rightHandSide);
		} catch (final ReturnException e) {
			rightHandSide = e.getValue();
		}

		result[0] = null; // IPatternMatcher
		result[1] = rightHandSide;
		if (leftHandSide instanceof ISymbol) {
			final ISymbol lhsSymbol = (ISymbol) leftHandSide;

			if (lhsSymbol.hasLocalVariableStack()) {
				lhsSymbol.set(rightHandSide);
				return result;
			} else {
				result[0] = lhsSymbol.putDownRule(F.Set, true, leftHandSide, rightHandSide, condition);
				return result;
			}
		}

		if (leftHandSide instanceof IAST) {
			final ISymbol lhsSymbol = ((IAST) leftHandSide).topHead();
			result[0] = lhsSymbol.putDownRule(F.Set, false, leftHandSide, rightHandSide, condition);
			return result;
		}

		throw new RuleCreationError(leftHandSide);
	}

	public IExpr numericEval(final IAST functionList) {
		return evaluate(functionList);
	}

	public void setUp(final ISymbol symbol) {
		symbol.setAttributes(ISymbol.HOLDALL);
	}

}