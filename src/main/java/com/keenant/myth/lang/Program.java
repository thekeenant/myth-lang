package com.keenant.myth.lang;

import com.keenant.myth.exception.TypeCheckException;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString
public class Program extends Node {
    private final Procedure procedure;

    public Program(ParserRuleContext context, Procedure procedure) {
        super(context);
        this.procedure = procedure;
    }

    public void typeCheck() throws TypeCheckException {
        // Todo: this should be somewhere else
        Metadata data = new Metadata("Main", "java.lang.Object");
        Scope scope = new Scope(data);

        procedure.typeCheck(scope, null);
    }
}
