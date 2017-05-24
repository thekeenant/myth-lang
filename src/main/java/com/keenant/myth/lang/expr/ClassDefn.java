package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.stmt.Decl;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

@ToString
public class ClassDefn extends Expr {
    private final String ident;
    private final List<Decl> list;

    public ClassDefn(ParserRuleContext context, String ident, List<Decl> list) {
        super(context);
        this.ident = ident;
        this.list = list;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        Scope nested = new Scope(scope);

        for (Decl decl : list) {
            decl.typeCheck(nested);
        }
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        return new ClassName(ident);
    }
}
