package com.keenant.myth.lang;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.stmt.DeclareStmt;
import com.keenant.myth.lang.stmt.DeclareAssignStmt;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Params extends Node {
    private final List<DeclareStmt> list;

    public Params(ParserRuleContext context, Scope scope, List<DeclareStmt> list) {
        super(context, scope);
        this.list = list;
    }

    public String getDescriptor() {
        // Todo
        return "([Ljava/lang/String;)";
    }

    public void typeCheck() throws TypeCheckException {
        boolean optional = false;
        for (DeclareStmt decl : list) {
            if (decl instanceof DeclareAssignStmt) {
                optional = true;
            }
            else if (optional) {
                throw new BadDeclarationException(this, "required params cannot follow optional params");
            }
            decl.typeCheck();
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
