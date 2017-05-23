package com.keenant.myth.lang;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.stmt.Decl;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Params extends Node {
    private final List<Decl> list;

    public Params(ParserRuleContext start, List<Decl> list) {
        super(start);
        this.list = list;
    }

    public void typeCheck(Scope scope) throws TypeCheckException {
        list.forEach(decl -> decl.typeCheck(scope));
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
