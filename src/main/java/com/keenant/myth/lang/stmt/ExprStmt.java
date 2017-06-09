package com.keenant.myth.lang.stmt;

import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.expr.Expr;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public class ExprStmt extends Stmt {
    private final Expr expr;

    public ExprStmt(ParserRuleContext context, Scope scope, Expr expr) {
        super(context, scope);
        this.expr = expr;
    }

    @Override
    public void typeCheck(ClassName returnType) throws TypeCheckException {
        expr.typeCheck();
    }

    @Override
    public void generate(MethodVisitor method) {
        expr.generate(method);
        // Todo: Gen
    }
}
