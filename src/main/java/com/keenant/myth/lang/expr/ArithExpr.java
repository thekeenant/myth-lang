package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.util.Arith;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Objects;

@ToString
public class ArithExpr extends Expr {
    private final Expr lhs;
    private final Expr rhs;
    private final Arith arith;

    public ArithExpr(ParserRuleContext start, Expr lhs, Expr rhs, Arith arith) {
        super(start);
        this.lhs = lhs;
        this.rhs = rhs;
        this.arith = arith;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        resolveType(scope);
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        ClassName lhsType = lhs.resolveType(scope);
        ClassName rhsType = rhs.resolveType(scope);
        // Todo: ensure the type supports arithmetic
        if (!rhsType.isSubTypeOf(lhsType))
            throw new TypeCheckException(this, "arithmetic expression with different types");
        return lhsType;
    }
}
