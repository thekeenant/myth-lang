package com.keenant.myth.lang.expression;

import lombok.ToString;
import org.objectweb.asm.MethodVisitor;

@ToString
public abstract class ReferenceExpr extends Expression {
  protected final String name;

  public ReferenceExpr(String name) {
    this.name = name;
  }

  public abstract void codegenGet(MethodVisitor mv);

  public abstract void codegenSet(MethodVisitor mv);
}
