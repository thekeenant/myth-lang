package com.keenant.myth.lang;

import com.keenant.myth.lang.scope.MethodScope;
import com.keenant.myth.lang.variable.MethodSymbol;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lombok.ToString;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class MethodDeclaration extends MemberDeclaration {
  private final String name;
  private final Parameters params;
  private final MythType returnType;
  private final Block block;
  private final boolean isStatic;

  private Type resolvedReturnType;
  private MethodScope scope;

  public MethodDeclaration(String name, Parameters params, @Nullable MythType returnType, Block block, boolean isStatic) {
    this.name = name;
    this.params = params;
    this.returnType = returnType;
    this.block = block;
    this.isStatic = isStatic;
  }

  @Override
  public void analyze(ClassDeclaration context) {
    context.getScope().set(name, new MethodSymbol(this));
    scope = new MethodScope(context.getScope(), this);

    if (returnType == null) {
      resolvedReturnType = Type.VOID_TYPE;
    }
    else {
      resolvedReturnType = returnType.resolveType();
    }

    params.analyze(scope);
    block.analyze(scope);
  }

  private int access() {
    int result = Opcodes.ACC_PUBLIC;
    if (isStatic) {
      result |= Opcodes.ACC_STATIC;
    }
    return result;
  }

  private String methodDescriptor() {
    return "(" + params.getParameterTypes().stream()
        .map(Type::getDescriptor)
        .collect(Collectors.joining()) + ")" + resolvedReturnType.getDescriptor();
  }

  @Override
  public void codegen(ClassWriter cw) {
    MethodVisitor mv = cw.visitMethod(
        access(),
        name,
        methodDescriptor(),
        null,
        null
    );

    block.codegen(mv);

    // TODO
    mv.visitInsn(Opcodes.RETURN);
    mv.visitMaxs(20, 20);
    mv.visitEnd();
  }
}
