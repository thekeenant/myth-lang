package com.keenant.myth.lang.expr;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.stmt.Decl;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

@ToString
public class FuncDefn extends Expr {
    private final Params params;
    private final Procedure procedure;
    private final ClassName returnType;

    public FuncDefn(ParserRuleContext start, Params params, Procedure procedure, ClassName returnType) {
        super(start);
        this.params = params;
        this.procedure = procedure;
        this.returnType = returnType;
    }

    @Override
    public void typeCheck(Scope scope) throws TypeCheckException {
        Scope nested = new Scope(scope);
        params.typeCheck(nested);
        procedure.typeCheck(nested, returnType);
    }

    @Override
    public ClassName resolveType(Scope scope) throws TypeCheckException {
        return new ClassName("Function");
    }
}
