package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

public class IsInstance extends Expr {
    private final Expr lhs;
    private final ClassName rhs;

    public IsInstance(ParserRuleContext context, Expr lhs, ClassName rhs) {
        super(context);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        lhs.typeCheck(scope);
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        return new ClassName("Bool");
    }
}
