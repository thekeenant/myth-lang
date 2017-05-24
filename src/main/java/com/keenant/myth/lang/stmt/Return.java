package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.expr.Expr;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Objects;

public class Return extends Stmt {
    private final Expr expr;

    public Return(ParserRuleContext context, Expr expr) {
        super(context);
        this.expr = expr;
    }

    @Override
    public void typeCheck(Scope scope, ClassName returnType) throws TypeCheckException {
        if (expr == null) {
            if (returnType != null) {
                throw new TypeCheckException(this, "return value expected");
            }
        }
        else {
            expr.typeCheck(scope);

            ClassName type = expr.resolveType(scope);

            if (returnType == null)
                throw new TypeCheckException(this, "incompatible return types, expected nothing, resolved " + type);

            if (!type.isSubTypeOf(returnType))
                throw new TypeCheckException(this, "incompatible return types, expected " + returnType + ", resolved " + type);
        }
    }
}
