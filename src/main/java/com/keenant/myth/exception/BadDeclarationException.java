package com.keenant.myth.exception;

import com.keenant.myth.lang.Node;

public class BadDeclarationException extends TypeCheckException {
    public BadDeclarationException(Node node, String msg) {
        super(node, msg);
    }
}
