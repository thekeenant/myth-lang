package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.ScopeException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.ClassName;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.VarMode;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

@ToString
public class Decl extends Stmt {
    private final VarMode varMode;
    private final String name;
    private final ClassName declaredType;

    public Decl(ParserRuleContext context, VarMode varMode, String name, ClassName declaredType) {
        super(context);
        this.varMode = varMode;
        this.name = name;
        this.declaredType = declaredType;
    }

    public VarMode getVarMode() {
        return varMode;
    }

    public String getName() {
        return name;
    }

    public Optional<ClassName> getDeclaredType() {
        return Optional.ofNullable(declaredType);
    }

    public ClassName resolveType(Scope scope) {
        return declaredType;
    }

    public void typeCheck(Scope scope) {
        if (resolveType(scope) == null) {
            throw new BadDeclarationException(this, "type cannot be inferred");
        }

        try {
            scope.addDecl(this);
        } catch (ScopeException e) {
            throw new BadDeclarationException(this, "name already in scope");
        }
    }

    @Override
    public void typeCheck(Scope scope, ClassName returnType) throws TypeCheckException {
        typeCheck(scope);
    }
}
