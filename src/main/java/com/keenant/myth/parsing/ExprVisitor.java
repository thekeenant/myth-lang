package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ExprIsInstanceContext;
import com.keenant.myth.MythParser.ExprParenContext;
import com.keenant.myth.MythParser.ExprTermContext;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.expr.Expr;
import com.keenant.myth.lang.expr.IsInstance;

public class ExprVisitor extends MythBaseVisitor<Expr> {
    private final Scope scope;

    public ExprVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Expr visitExprParen(ExprParenContext ctx) {
        return ctx.expr().accept(this);
    }

    @Override
    public Expr visitExprTerm(ExprTermContext ctx) {
        return ctx.term().accept(new TermVisitor(scope));
    }

    @Override
    public Expr visitExprIsInstance(ExprIsInstanceContext ctx) {
        Expr expr = ctx.expr().accept(new ExprVisitor(scope));
        ClassName name = new ClassName(ctx.IDENT().getSymbol().getText());
        return new IsInstance(ctx, scope, expr, name);
    }
}
