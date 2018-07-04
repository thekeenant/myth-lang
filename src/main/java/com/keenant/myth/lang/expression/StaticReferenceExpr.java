package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import java.lang.reflect.Field;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString(callSuper = true)
public class StaticReferenceExpr extends ReferenceExpr {
  private final Expression owner;

  private Field javaField;
  private Type fieldType;

  public StaticReferenceExpr(String name, Expression owner) {
    super(name);
    this.owner = owner;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    owner.analyze(scope, context);

    Type ownerType = owner.getResolvedType();

    try {
      Class<?> javaClass = Class.forName(ownerType.getClassName());
      for (Field field : javaClass.getDeclaredFields()) {
        if (field.getName().equals(name)) {
          javaField = field;
          fieldType = Type.getType(javaField.getType());
          return;
        }
      }
    }
    catch (ClassNotFoundException e) {
      throw new UnsupportedOperationException("Unknown static reference", e);
    }

    throw new UnsupportedOperationException("Field not found: " + name + " in " + ownerType.getClassName());
  }

  @Override
  public Type getResolvedType() {
    return fieldType;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    codegenGet(mv);
  }

  @Override
  public void codegenSet(MethodVisitor mv) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void codegenGet(MethodVisitor mv) {
    mv.visitFieldInsn(
        Opcodes.GETSTATIC,
        owner.getResolvedType().getInternalName(),
        name,
        fieldType.getDescriptor()
    );
  }
}
