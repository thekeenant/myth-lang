package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.DeclAssignContext;
import com.keenant.myth.MythParser.DeclContext;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.stmt.Decl;
import com.keenant.myth.lang.VarMode;
import com.keenant.myth.lang.stmt.DeclAssign;
import com.keenant.myth.lang.expr.Expr;

public class DeclVisitor extends MythBaseVisitor<Decl> {
    @Override
    public Decl visitDecl(DeclContext ctx) {
        VarMode mode = ctx.varMode().accept(new TypeVisitor());
        String name = ctx.IDENT().getSymbol().getText();
        ClassName type = null;
        if (ctx.className() != null) {
            type = ctx.className().accept(new ClassNameVisitor());
        }
        return new Decl(ctx, mode, name, type);
    }

    @Override
    public Decl visitDeclAssign(DeclAssignContext ctx) {
        Decl decl = ctx.decl().accept(this);
        Expr expr = ctx.expr().accept(new ExprVisitor());
        return new DeclAssign(ctx, decl, expr);
    }
}
