package com.keenant.myth.lang;

import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.stmt.Decl;
import com.keenant.myth.lang.stmt.DeclAssign;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Params extends Node {
    private final List<Decl> list;

    public Params(ParserRuleContext start, List<Decl> list) {
        super(start);
        this.list = list;
    }

    public void typeCheck(Scope scope) throws TypeCheckException {
        boolean optional = false;
        for (Decl decl : list) {
            if (decl instanceof DeclAssign) {
                optional = true;
            }
            else if (optional) {
                throw new BadDeclarationException(this, "required params cannot follow optional params");
            }
            decl.typeCheck(scope);
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
