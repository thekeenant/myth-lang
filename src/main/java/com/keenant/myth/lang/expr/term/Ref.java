package com.keenant.myth.lang.expr.term;

import com.keenant.myth.exception.BadReferenceException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.stmt.Decl;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString
public class Ref extends Term {
    private final String ident;

    public Ref(ParserRuleContext context, String ident) {
        super(context);
        this.ident = ident;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {

    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        Decl decl = scope.findDeclGlobal(ident).orElse(null);
        if (decl == null) {
            throw new BadReferenceException(this, "reference to unknown variable");
        }

        return decl.resolveType(scope);
    }
}
