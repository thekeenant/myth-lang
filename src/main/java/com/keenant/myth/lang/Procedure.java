package com.keenant.myth.lang;

import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import org.objectweb.asm.MethodVisitor;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Collections;
import java.util.List;

/**
 * A procedure is a sequence of statements.
 */
@ToString
public class Procedure extends Node {
    private final List<Stmt> statements;

    public Procedure(ParserRuleContext context, Scope scope, List<Stmt> statements) {
        super(context, scope);
        this.statements = statements;
    }

    public Procedure(ParserRuleContext context, Scope scope, Stmt stmt) {
        this(context, scope, Collections.singletonList(stmt));
    }

    public void generate(MethodVisitor method) {
        for (Stmt stmt : statements) {
            stmt.generate(method);
        }
    }

    public void typeCheck(ClassName returnType) {
        statements.forEach(stmt -> stmt.typeCheck(returnType));
    }
}
