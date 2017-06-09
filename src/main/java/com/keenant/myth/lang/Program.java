package com.keenant.myth.lang;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString
public class Program extends Node {
    private final ClassDefinition def;

    public Program(ParserRuleContext context, ClassDefinition def) {
        super(context, null);
        this.def = def;
    }

    @Override
    public Scope getScope() {
        return def.getScope();
    }

    public void typeCheck() throws TypeCheckException {
        def.typeCheck();
    }

    public void generate() {
        def.generate();
    }
}
