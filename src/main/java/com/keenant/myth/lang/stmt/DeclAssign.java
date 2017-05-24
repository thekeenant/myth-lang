package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.expr.Expr;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Objects;

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
    public void typeCheck(Scope scope, ClassName returnType) throws TypeCheckException {
        super.typeCheck(scope, returnType);
        expr.typeCheck(scope);

        resolveType(scope);
    }

    @Override
    public ClassName resolveType(Scope scope) {
        ClassName resolved = expr.resolveType(scope);

        // explicit typed
        if (super.getDeclaredType().isPresent()) {
            ClassName declared = super.getDeclaredType().get();

            if (!declared.isSubTypeOf(resolved)) {
                throw new BadDeclarationException(this, "incompatible types, declared is " + declared + ", but resolved is " + resolved);
            }
        }

        return resolved;
    }
}
