package com.keenant.myth.lang.expr.term;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

public class LiteralInt extends Term {
    private final int value;

    public LiteralInt(ParserRuleContext context, int value) {
        super(context);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        // Nothing to do
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        return new ClassName("java.lang.Integer");
    }
}
