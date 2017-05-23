package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.TermContext;
import com.keenant.myth.lang.expr.term.LiteralBool;
import com.keenant.myth.lang.expr.term.LiteralInt;
import com.keenant.myth.lang.expr.term.Ref;
import com.keenant.myth.lang.expr.term.Term;

public class TermVisitor extends MythBaseVisitor<Term> {
    @Override
    public Term visitTerm(TermContext ctx) {
        if (ctx.LIT_INT() != null) {
            int value = Integer.parseInt(ctx.LIT_INT().getSymbol().getText());
            return new LiteralInt(ctx, value);
        }
        else if (ctx.TRUE() != null || ctx.FALSE() != null) {
            return new LiteralBool(ctx, ctx.TRUE() != null);
        }
        else if (ctx.ref() != null) {
            // Todo: references can be more
            return new Ref(ctx, ctx.ref().IDENT().getSymbol().getText());
        }
        else if (ctx.funcCall() != null) {

        }

        throw new UnsupportedOperationException("not done");
    }
}
