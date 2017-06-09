package com.keenant.myth.lang.expr.term;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.Opcodes;

public class LiteralBool extends Term {
    private final boolean value;

    public LiteralBool(ParserRuleContext context, Scope scope, boolean value) {
        super(context, scope);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void typeCheck() throws TypeCheckException {
        // Nothing to do
    }

    @Override
    public ClassName resolveType() throws TypeCheckException {
        return new ClassName("java.lang.Boolean");
    }

    @Override
    public void generate(MethodVisitor method) {
        method.visitInsn(Opcodes.NEW);
        method.visitInsn(Opcodes.DUP);

        method.visitLdcInsn(value);
        method.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "java/lang/Boolean",
                "<init>",
                "(Z)V",
                false
        );
    }
}
