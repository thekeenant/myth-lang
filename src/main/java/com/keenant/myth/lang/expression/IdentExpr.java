package com.keenant.myth.lang.expression;

import com.keenant.myth.lang.scope.Scope;
import com.keenant.myth.lang.variable.LocalVariable;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString(callSuper = true)
public class IdentExpr extends ReferenceExpr {
  private final Expression owner;

  private Type type;

  private LocalVariable localVariable;

  public IdentExpr(String name, Expression owner) {
    super(name);
    this.owner = owner;
  }

  public String qualifiedName() {
    String result = name;
    if (owner instanceof IdentExpr) {
      result = ((IdentExpr) owner).qualifiedName() + "." + result;
    }
    return result;
  }

  @Override
  public void analyze(Scope scope) {
    if (owner == null) {
      boolean identExists = scope.exists(name);
      if (identExists) {
        localVariable = scope.lookup(name, LocalVariable.class);
        type = localVariable.getType().resolveType();
      }
    }
    else {
      owner.analyze(scope);

      try {
        Class<?> clazz = Class.forName(qualifiedName());
        type = Type.getType(clazz);
      }
      catch (ClassNotFoundException e) {
        // ignore
      }
    }
  }

  @Override
  public Type getResolvedType() {
    if (type == null) {
      throw new IllegalStateException("Bad type: " + this);
    }
    return type;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    codegenGet(mv);
  }

  @Override
  public void codegenGet(MethodVisitor mv) {
    mv.visitVarInsn(
        Opcodes.ALOAD,
        localVariable.getIndex()
    );
  }

  @Override
  public void codegenSet(MethodVisitor mv) {
    mv.visitVarInsn(
        Opcodes.ASTORE,
        localVariable.getIndex()
    );
  }
}
