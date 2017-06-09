package com.keenant.myth.lang.expr.term;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import org.objectweb.asm.MethodVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.Opcodes;

public class LiteralInt extends Term {
    private final int value;

    public LiteralInt(ParserRuleContext context, Scope scope, int value) {
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
        return new ClassName("java.lang.Integer", new ClassName("java.lang.Number"));
    }

    @Override
    public void generate(MethodVisitor method) {
//        method.visitLdcInsn(value);
//        method.visitVarInsn(Opcodes.ISTORE, 0);
//
//        method.visitVarInsn(Opcodes.ALOAD, 0);
        method.visitTypeInsn(Opcodes.NEW, resolveType().getInternalName());
        method.visitInsn(Opcodes.DUP);
        method.visitLdcInsn(value);
        method.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "java/lang/Integer",
                "<init>",
                "(I)V",
                false
        );

        // Todo: This prints it out! :)
        method.visitVarInsn(Opcodes.ASTORE, 1);
        method.visitFieldInsn(
                Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
        );
        method.visitVarInsn(Opcodes.ALOAD, 1);

        method.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/Object;)V",
                false
        );

    }
}
