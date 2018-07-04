package com.keenant.myth.lang.literal;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class DoubleLiteralExpr extends LiteralExpr {
  private final String valueStr;

  private double value;

  public DoubleLiteralExpr(String valueStr) {
    this.valueStr = valueStr;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    value = Double.valueOf(valueStr);
  }

  @Override
  public Type getResolvedType() {
    return Type.DOUBLE_TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitLdcInsn(value);
  }
}
