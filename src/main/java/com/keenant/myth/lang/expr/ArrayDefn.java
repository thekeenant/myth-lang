package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.ExprList;
import com.keenant.myth.lang.Scope;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Objects;

@ToString
public class ArrayDefn extends Expr {
    private final ClassName type;
    private final ExprList exprs;

    public ArrayDefn(ParserRuleContext context, ClassName type, ExprList exprs) {
        super(context);
        this.type = type;
        this.exprs = exprs;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        if (exprs != null) {
            exprs.typeCheck(scope);

            for (Expr expr : exprs) {
                ClassName exprType = expr.resolveType(scope);

                if (!exprType.isSubTypeOf(type)) {
                    throw new TypeCheckException(expr, "incompatible array value, expected " + type + ", resolved " + exprType);
                }
            }
        }
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        return new ClassName("ArrayList");
    }
}
