package com.keenant.myth.lang;

import com.keenant.myth.codegen.Scope;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Node {
    private final ParserRuleContext context;
    private final Scope scope;

    public Node(ParserRuleContext context, Scope scope) {
        this.context = context;
        this.scope = scope;
    }

    public ParserRuleContext getParserContext() {
        return context;
    }

    public Scope getScope() {
        return scope;
    }
}
