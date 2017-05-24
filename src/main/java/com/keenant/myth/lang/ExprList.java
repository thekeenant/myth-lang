package com.keenant.myth.lang;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.expr.Expr;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Iterator;
import java.util.List;

public class ExprList extends Node implements Iterable<Expr> {
    private final List<Expr> exprs;

    public ExprList(ParserRuleContext start, List<Expr> exprs) {
        super(start);
        this.exprs = exprs;
    }

    public void typeCheck(Scope scope) throws TypeCheckException {
        for (Expr expr : exprs) {
            expr.typeCheck(scope);
        }
    }

    @Override
    public Iterator<Expr> iterator() {
        return exprs.iterator();
    }
}
