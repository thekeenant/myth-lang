package com.keenant.myth.lang;

import com.keenant.myth.exception.TypeCheckException;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Stmt extends Node {
    public Stmt(ParserRuleContext context) {
        super(context);
    }

    public abstract void typeCheck(Scope scope) throws TypeCheckException;
}
