package com.keenant.myth.lang;

import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

/**
 * A procedure is a sequence of statements.
 */
@ToString
public class Procedure extends Node {
    private final List<Stmt> statements;

    public Procedure(ParserRuleContext context, List<Stmt> statements) {
        super(context);
        this.statements = statements;
    }

    public void typeCheck(Scope scope, ClassName returnType) {
        statements.forEach(stmt -> stmt.typeCheck(scope, returnType));
    }
}
