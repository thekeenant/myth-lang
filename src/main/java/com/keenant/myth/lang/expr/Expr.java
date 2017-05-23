package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.Stmt;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Expr extends Stmt {
    public Expr(ParserRuleContext start) {
        super(start);
    }

    public abstract ClassName resolveType(Scope scope) throws TypeCheckException;
}
