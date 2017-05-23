package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.StmtDeclContext;
import com.keenant.myth.MythParser.StmtExprContext;
import com.keenant.myth.lang.Stmt;

public class StmtVisitor extends MythBaseVisitor<Stmt> {
    @Override
    public Stmt visitStmtDecl(StmtDeclContext ctx) {
        return ctx.accept(new DeclVisitor());
    }

    @Override
    public Stmt visitStmtExpr(StmtExprContext ctx) {
        return ctx.expr().accept(new ExprVisitor());
    }
}
