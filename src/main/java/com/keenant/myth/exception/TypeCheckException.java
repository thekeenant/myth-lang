package com.keenant.myth.exception;

import com.keenant.myth.lang.Node;

public class TypeCheckException extends RuntimeException {
    public TypeCheckException(Node node, String msg, Throwable cause) {
        super("[" + node.getParserContext().getStart().getLine() + ":" + node.getParserContext().getStart().getCharPositionInLine() + "] " + msg, cause);
    }

    public TypeCheckException(Node node, String msg) {
        this(node, msg, null);
    }
}
