package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.expr.term.Ref;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Objects;

@ToString
public class AssignExpr extends Expr {
    private final Ref ref;
    private final Expr expr;

    public AssignExpr(ParserRuleContext start, Ref ref, Expr expr) {
        super(start);
        this.ref = ref;
        this.expr = expr;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        ref.typeCheck(scope);
        expr.typeCheck(scope);
        resolveType(scope);
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        ClassName refType = ref.resolveType(scope);
        ClassName exprType = expr.resolveType(scope);

        if (!exprType.isSubTypeOf(refType)) {
            throw new TypeCheckException(this, "incompatible types, ref is " + refType + " but expr is " + exprType);
        }

        return refType;
    }
}
