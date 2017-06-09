package com.keenant.myth.lang.stmt;

import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.expr.Expr;
import org.objectweb.asm.MethodVisitor;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.ClassWriter;

@ToString(callSuper = true)
public class DeclareAssignStmt extends DeclareStmt {
    private final Expr expr;

    public DeclareAssignStmt(ParserRuleContext context, Scope scope, DeclareStmt decl, Expr expr) {
        super(context, scope, decl.getVarMode(), decl.getName(), decl.getDeclaredType().orElse(null));
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public void typeCheck(ClassName returnType) throws TypeCheckException {
        super.typeCheck(returnType);
        expr.typeCheck();

        resolveType();
    }

    @Override
    public ClassName resolveType() {
        ClassName resolved = expr.resolveType();

        // explicit typed
        if (super.getDeclaredType().isPresent()) {
            ClassName declared = super.getDeclaredType().get();

            if (!declared.isSubTypeOf(resolved)) {
                throw new BadDeclarationException(this, "incompatible types, declared is " + declared + ", but resolved is " + resolved);
            }
        }

        return resolved;
    }

    @Override
    public void generate(ClassWriter target) {
        throw new UnsupportedOperationException("no support for field assignment yet");
    }

    @Override
    public void generate(MethodVisitor method) {
        expr.generate(method);

//        method.visitVarInsn(
//
//        );

    }
}
