package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.expr.Expr;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString(callSuper = true)
public class DeclAssign extends Decl {
    private final Expr expr;

    public DeclAssign(ParserRuleContext context, Decl decl, Expr expr) {
        super(context, decl.getVarMode(), decl.getName(), decl.getDeclaredType().orElse(null));
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        super.typeCheck(scope);
        expr.typeCheck(scope);
    }

    @Override
    public ClassName resolveType(Scope scope) {
        // explicit typed
        if (super.getDeclaredType().isPresent()) {
            return super.getDeclaredType().get();
        }

        return expr.resolveType(scope);
    }
}
