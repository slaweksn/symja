package org.matheclipse.core.reflection.system;

import static org.matheclipse.core.expression.F.Plus;
import static org.matheclipse.core.expression.F.Times;

import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.eval.interfaces.AbstractArgMultiple;
import org.matheclipse.core.eval.interfaces.HashRule;
import org.matheclipse.core.eval.interfaces.INumeric;
import org.matheclipse.core.expression.AST;
import org.matheclipse.core.expression.F;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IComplex;
import org.matheclipse.core.interfaces.IComplexNum;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.IFraction;
import org.matheclipse.core.interfaces.IInteger;
import org.matheclipse.core.interfaces.INum;
import org.matheclipse.core.interfaces.INumber;
import org.matheclipse.core.interfaces.ISignedNumber;
import org.matheclipse.core.interfaces.ISymbol;

import com.google.common.collect.ArrayListMultimap;

public class Plus extends AbstractArgMultiple implements INumeric {

	private static ArrayListMultimap<Integer, HashRule> RULE_MAP = ArrayListMultimap.create();

	public ArrayListMultimap<Integer, HashRule> getHashRuleMap() {
		return RULE_MAP;
	}

	public Plus() {

	}

	@Override
	public IExpr e2ComArg(final IComplex c0, final IComplex c1) {
		return c0.add(c1);
	}

	@Override
	public IExpr e2DblArg(final INum d0, final INum d1) {
		return d0.add(d1);
	}

	@Override
	public IExpr e2DblComArg(final IComplexNum d0, final IComplexNum d1) {
		return d0.add(d1);
	}

	@Override
	public IExpr e2FraArg(final IFraction f0, final IFraction f1) {
		return f0.add(f1);
	}

	@Override
	public IExpr e2IntArg(final IInteger i0, final IInteger i1) {
		return i0.add(i1);
	}

	@Override
	public IExpr e2ObjArg(final IExpr o0, final IExpr o1) {
		IExpr temp = null;
		EvalEngine ee = EvalEngine.get();
		// Context ct = ((ContextThread) Thread.currentThread()).getContext();
		if (ee.isNumericMode()) {
			if (o0.equals(F.CD0)) {
				return o1;
			}

			if (o1.equals(F.CD0)) {
				return o0;
			}
		}

		if (o0.equals(F.C0)) {
			return o1;
		}

		if (o1.equals(F.C0)) {
			return o0;
		}

		if (o0.equals(F.Indeterminate) || o1.equals(F.Indeterminate)) {
			return F.Indeterminate;
		}

		if (o0.isAST(F.DirectedInfinity, 2)) {
			temp = eInfinity(o0, o1);
		} else if (o1.isAST(F.DirectedInfinity, 2)) {
			temp = eInfinity(o1, o0);
		}
		if (temp != null) {
			return temp;
		}

		if (o0.equals(o1)) {
			return Times(F.C2, o0);
		}

		if (o0.isAST(F.Times) && (((IAST) o0).size() > 2)) {
			final AST f0 = (AST) o0;

			if (f0.get(1) instanceof INumber) {
				if ((f0.size() == 3) && f0.get(2).equals(o1)) {
					return Times(Plus(F.C1, f0.get(1)), o1);
				}

				if (o1.isAST(F.Times) && (((IAST) o1).size() > 2)) {
					final AST f1 = (AST) o1;

					if (f1.get(1) instanceof INumber) {
						if (f0.equalsFromPosition(1, f1, 1)) {
							final IAST result = F.ast(f0, F.Times, true, 2, f0.size());

							return Times(Plus(f0.get(1), f1.get(1)), result);
						}
					} else {
						if (f0.equalsFromPosition(1, f1, 0)) {
							final IAST result = F.ast(f0, F.Times, true, 2, f0.size());

							return Times(Plus(F.C1, f0.get(1)), result);
						}
					}
				}
			} else {
				if (o1.isAST(F.Times) && (((IAST) o1).size() > 2)) {
					final AST f1 = (AST) o1;

					if (f1.get(1) instanceof INumber) {
						if (f0.equalsFromPosition(0, f1, 1)) {
							final IAST result = F.ast(f1, F.Times, true, 2, f1.size());

							return Times(Plus(F.C1, f1.get(1)), result);
						}
					}
				}
			}
		}

		if (o1.isAST(F.Times) && (((IAST) o1).size() > 2) && (((IAST) o1).get(1) instanceof INumber)) {
			final IAST f1 = (IAST) o1;

			if ((f1.size() == 3) && f1.get(2).equals(o0)) {
				return Times(Plus(F.C1, f1.get(1)), o0);
			}
		}

		return null;
	}

	private IExpr eInfinity(IExpr inf, IExpr o1) {
		if (inf.equals(F.CInfinity)) {
			if (o1.equals(F.CInfinity)) {
				return F.CInfinity;
			} else if (o1.equals(F.CNInfinity)) {
				EvalEngine.get().getOutPrintStream().println("Indeterminate expression Infinity-Infinity");
				return F.Indeterminate;
			} else if (o1 instanceof ISignedNumber) {
				return F.CInfinity;
			}
		} else if (inf.equals(F.CNInfinity)) {
			if (o1.equals(F.CInfinity)) {
				EvalEngine.get().getOutPrintStream().println("Indeterminate expression Infinity-Infinity");
				return F.Indeterminate;
			} else if (o1.equals(F.CNInfinity)) {
				return F.CNInfinity;
			} else if (o1 instanceof ISignedNumber) {
				return F.CNInfinity;
			}
		}
		return null;
	}

	@Override
	public IExpr eComIntArg(final IComplex c0, final IInteger i1) {
		return c0.add(F.complex(i1, F.C0));
	}

	@Override
	public void setUp(final ISymbol symbol) {
		symbol.setAttributes(ISymbol.ONEIDENTITY | ISymbol.ORDERLESS | ISymbol.FLAT | ISymbol.LISTABLE | ISymbol.NUMERICFUNCTION);
		setUpHashRule("Sin[x_]^2", "Cos[x_]^2", "1");
	}

	public double evalReal(final double[] stack, final int top, final int size) {
		double result = 0;
		for (int i = top - size + 1; i < top + 1; i++) {
			result += stack[i];
		}
		return result;
	}
}
