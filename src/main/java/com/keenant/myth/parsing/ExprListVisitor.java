package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ExprListContext;
import com.keenant.myth.lang.ExprList;
import com.keenant.myth.lang.expr.Expr;

import java.util.ArrayList;
import java.util.List;

public class ExprListVisitor extends MythBaseVisitor<ExprList> {
    @Override
    public ExprList visitExprList(ExprListContext ctx) {
        List<Expr> list = new ArrayList<>();
        buildList(list, ctx);
        return new ExprList(ctx, list);
    }

    private void buildList(List<Expr> list, ExprListContext ctx) {
        list.add(ctx.expr().accept(new ExprVisitor()));
        if (ctx.exprList() != null)
            buildList(list, ctx.exprList());
    }
}
