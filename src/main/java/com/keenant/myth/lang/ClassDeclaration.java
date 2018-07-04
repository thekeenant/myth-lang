package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.ClassScope;
import java.util.List;
import lombok.ToString;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class ClassDeclaration {
  private final String name;
  private final List<MemberDeclaration> declarations;

  private ClassScope scope;

  public ClassDeclaration(String name, List<MemberDeclaration> declarations) {
    this.name = name;
    this.declarations = declarations;
  }

  public ClassScope getScope() {
    return scope;
  }

  public void analyze(CompileContext context) {
    scope = new ClassScope(null);

    for (MemberDeclaration decl : declarations) {
      decl.analyze(this, context);
    }
  }

  public byte[] codegen() {
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

    cw.visit(
        Opcodes.V1_8,
        Opcodes.ACC_SUPER | Opcodes.ACC_PUBLIC,
        name,
        null,
        Type.getInternalName(Object.class),
        null
    );

    {
      MethodVisitor constructor = cw.visitMethod(
          Opcodes.ACC_PUBLIC,
          "<init>",
          "()V",
          null,
          null
      );
      constructor.visitVarInsn(Opcodes.ALOAD, 0);
      constructor.visitMethodInsn(
          Opcodes.INVOKESPECIAL,
          Type.getInternalName(Object.class),
          "<init>",
          "()V",
          false
      );
      constructor.visitInsn(Opcodes.RETURN);
      constructor.visitMaxs(1, 1);
      constructor.visitEnd();
    }

    for (MemberDeclaration decl : declarations) {
      decl.codegen(cw);
    }

    cw.visitEnd();
    return cw.toByteArray();
  }
}
