package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.lang.Node;
import com.keenant.myth.codegen.Scope;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Expr extends Node {
    public Expr(ParserRuleContext context, Scope scope) {
        super(context, scope);
    }

    public abstract void typeCheck() throws TypeCheckException;

    public abstract ClassName resolveType() throws TypeCheckException;

    public abstract void generate(MethodVisitor method);
}
