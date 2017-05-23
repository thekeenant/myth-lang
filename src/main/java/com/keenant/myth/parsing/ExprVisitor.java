package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.*;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.expr.FuncDefn;
import com.keenant.myth.lang.expr.term.Ref;
import com.keenant.myth.lang.expr.ArithExpr;
import com.keenant.myth.lang.expr.AssignExpr;
import com.keenant.myth.lang.expr.Expr;
import com.keenant.myth.lang.stmt.Decl;
import com.keenant.myth.util.Arith;

import java.util.List;

public class ExprVisitor extends MythBaseVisitor<Expr> {
    @Override
    public Expr visitExprArith(ExprArithContext ctx) {
        Arith arith;
        if (ctx.PLUS() != null)
            arith = Arith.ADD;
        else if (ctx.MINUS() != null)
            arith = Arith.SUB;
        else if (ctx.TIMES() != null)
            arith = Arith.MULT;
        else if (ctx.DIVIDE() != null)
            arith = Arith.DIV;
        else
            throw new IllegalStateException("unknown arith state");

        Expr lhs = ctx.expr(0).accept(this);
        Expr rhs = ctx.expr(1).accept(this);

        return new ArithExpr(ctx, lhs, rhs, arith);
    }

    @Override
    public Expr visitExprParen(ExprParenContext ctx) {
        return ctx.expr().accept(this);
    }

    @Override
    public Expr visitExprTerm(ExprTermContext ctx) {
        return ctx.term().accept(new TermVisitor());
    }

    @Override
    public Expr visitExprAssign(ExprAssignContext ctx) {
        Ref ref = new Ref(ctx.ref(), ctx.ref().IDENT().getSymbol().getText());
        Expr expr = ctx.expr().accept(this);
        return new AssignExpr(ctx, ref, expr);
    }

    @Override
    public Expr visitExprFuncDefn(ExprFuncDefnContext ctx) {
        FuncDefnContext funcDefn = ctx.funcDefn();

        Procedure procedure = funcDefn.procedure().accept(new ProcedureVisitor());
        ClassName returnType = funcDefn.className().accept(new ClassNameVisitor());
        Params params = funcDefn.params().accept(new ParamVisitor());

        return new FuncDefn(ctx, params, procedure, returnType);
    }
}
