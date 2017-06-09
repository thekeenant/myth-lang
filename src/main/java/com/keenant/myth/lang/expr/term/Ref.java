package com.keenant.myth.lang.expr.term;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.BadReferenceException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.lang.stmt.DeclareStmt;
import org.objectweb.asm.MethodVisitor;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString
public class Ref extends Term {
    private final String ident;

    public Ref(ParserRuleContext context, Scope scope, String ident) {
        super(context, scope);
        this.ident = ident;
    }

    @Override
    public void typeCheck() throws TypeCheckException {

    }

    public DeclareStmt getDeclaration() {
        return getScope().findDeclGlobal(ident)
                .orElseThrow(() -> new BadReferenceException(this, "reference to unknown variable"));
    }

    @Override
    public ClassName resolveType() throws TypeCheckException {
        return getDeclaration().resolveType();
    }

    @Override
    public void generate(MethodVisitor method) {
        DeclareStmt decl = getDeclaration();
        // Todo: Gen
    }
}
