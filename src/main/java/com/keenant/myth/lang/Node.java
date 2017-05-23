package com.keenant.myth.lang;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Node {
    private final ParserRuleContext start;

    public Node(ParserRuleContext start) {
        this.start = start;
    }

    public ParserRuleContext getParserContext() {
        return start;
    }
}
