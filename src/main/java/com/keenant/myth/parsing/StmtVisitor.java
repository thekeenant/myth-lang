package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.StmtDeclContext;
import com.keenant.myth.MythParser.StmtExprContext;
import com.keenant.myth.MythParser.StmtReturnContext;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.expr.Expr;
import com.keenant.myth.lang.stmt.ExprStmt;
import com.keenant.myth.lang.stmt.ReturnStmt;

public class StmtVisitor extends MythBaseVisitor<Stmt> {
    private final Scope scope;

    public StmtVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Stmt visitStmtDecl(StmtDeclContext ctx) {
        return ctx.accept(new DeclVisitor(scope));
    }

    @Override
    public Stmt visitStmtExpr(StmtExprContext ctx) {
        Expr expr = ctx.expr().accept(new ExprVisitor(scope));
        return new ExprStmt(ctx, scope, expr);
    }

    @Override
    public Stmt visitStmtReturn(StmtReturnContext ctx) {
        Expr expr = ctx.expr() == null ? null : ctx.expr().accept(new ExprVisitor(scope));
        return new ReturnStmt(ctx, scope, expr);
    }
}
