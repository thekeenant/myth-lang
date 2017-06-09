package com.keenant.myth.lang;

import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.Scope;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Stmt extends Node {
    public Stmt(ParserRuleContext context, Scope scope) {
        super(context, scope);
    }

    public abstract void typeCheck(ClassName returnType) throws TypeCheckException;

    public abstract void generate(MethodVisitor method);
}
