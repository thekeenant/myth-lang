package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.Stmt;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Expr extends Stmt {
    public Expr(ParserRuleContext context) {
        super(context);
    }

    public abstract void typeCheck(Scope scope) throws TypeCheckException;

    @Deprecated
    @Override
    public void typeCheck(Scope scope, ClassName returnType) throws TypeCheckException {
        // Todo: Make an ExprStmt, instead of Expr extending Stmt?
        typeCheck(scope);
    }

    public abstract ClassName resolveType(Scope scope) throws TypeCheckException;
}
