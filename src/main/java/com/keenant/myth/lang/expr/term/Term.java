package com.keenant.myth.lang.expr.term;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.expr.Expr;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Term extends Expr {
    public Term(ParserRuleContext context, Scope scope) {
        super(context, scope);
    }
}
