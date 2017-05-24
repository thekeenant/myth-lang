package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.StmtDeclContext;
import com.keenant.myth.MythParser.StmtExprContext;
import com.keenant.myth.MythParser.StmtReturnContext;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.expr.Expr;
import com.keenant.myth.lang.stmt.Return;

public class StmtVisitor extends MythBaseVisitor<Stmt> {
    @Override
    public Stmt visitStmtDecl(StmtDeclContext ctx) {
        return ctx.accept(new DeclVisitor());
    }

    @Override
    public Stmt visitStmtExpr(StmtExprContext ctx) {
        return ctx.expr().accept(new ExprVisitor());
    }

    @Override
    public Stmt visitStmtReturn(StmtReturnContext ctx) {
        Expr expr = ctx.expr() == null ? null : ctx.expr().accept(new ExprVisitor());
        return new Return(ctx, expr);
    }
}
