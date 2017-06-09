package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.expr.Expr;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class ReturnStmt extends Stmt {
    private final Expr expr;

    public ReturnStmt(ParserRuleContext context, Scope scope, Expr expr) {
        super(context, scope);
        this.expr = expr;
    }

    @Override
    public void typeCheck(ClassName returnType) throws TypeCheckException {
        if (expr == null) {
            if (returnType != null) {
                throw new TypeCheckException(this, "return value expected");
            }
        }
        else {
            expr.typeCheck();

            ClassName type = expr.resolveType();

            if (returnType == null)
                throw new TypeCheckException(this, "incompatible return types, expected nothing, resolved " + type);

            if (!type.isSubTypeOf(returnType))
                throw new TypeCheckException(this, "incompatible return types, expected " + returnType + ", resolved " + type);
        }
    }

    @Override
    public void generate(MethodVisitor method) {
        // Todo: Gen
    }
}
