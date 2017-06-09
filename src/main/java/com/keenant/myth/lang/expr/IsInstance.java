package com.keenant.myth.lang.expr;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.antlr.v4.runtime.ParserRuleContext;

public class IsInstance extends Expr {
    private final Expr lhs;
    private final ClassName rhs;

    public IsInstance(ParserRuleContext context, Scope scope, Expr lhs, ClassName rhs) {
        super(context, scope);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void typeCheck() throws TypeCheckException {
        lhs.typeCheck();
    }

    @Override
    public ClassName resolveType() throws TypeCheckException {
        return new ClassName("Bool");
    }

    @Override
    public void generate(MethodVisitor method) {
        lhs.generate(method);

        method.visitTypeInsn(
                Opcodes.INSTANCEOF,
                rhs.getInternalName()
        );

        method.visitInsn(Opcodes.INSTANCEOF);
    }
}
