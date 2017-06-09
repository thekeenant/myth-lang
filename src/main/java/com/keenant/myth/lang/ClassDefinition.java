package com.keenant.myth.lang;

import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.stmt.DeclareStmt;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.Type;

import java.util.List;

@ToString
public class ClassDefinition extends Node {
    private final List<DeclareStmt> list;

    public ClassDefinition(ParserRuleContext context, Scope scope, List<DeclareStmt> list) {
        super(context, scope);
        this.list = list;
    }

    public void typeCheck() throws TypeCheckException {
        for (DeclareStmt decl : list) {
            decl.typeCheck();
        }
    }

    public void generate() {
        String name = getScope().getClassName().getInternalName();
        String parent = getScope().getClassName().getParent().getInternalName();
        ClassWriter target = getScope().createTarget(name);

        // Start of every class
        target.visit(
                Opcodes.V1_8,
                Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
                name,
                null,
                parent,
                null
        );

        // Todo: all classes have default constructor atm, wtf?
        MethodVisitor constructor = target.visitMethod(
                Opcodes.ACC_PUBLIC,
                "<init>",
                "()V",
                null,
                null
        );

        constructor.visitCode();
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Object.class), "<init>", "()V", false);
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(1, 1);
        constructor.visitEnd();

        for (DeclareStmt stmt : list) {
            stmt.generate(target);
        }

        target.visitEnd();
    }

    public List<DeclareStmt> getDeclarations() {
        return list;
    }
}
