package com.keenant.myth.exception;

import com.keenant.myth.lang.Node;

public class BadReferenceException extends TypeCheckException {
    public BadReferenceException(Node node, String msg) {
        super(node, msg);
    }
}
