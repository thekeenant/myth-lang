package com.keenant.myth.lang.literal;

import com.keenant.myth.lang.scope.Scope;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

@ToString
public class IntLiteralExpr extends LiteralExpr {
  private final String valueStr;

  private int value;

  public IntLiteralExpr(String valueStr) {
    this.valueStr = valueStr;
  }

  @Override
  public void analyze(Scope scope) {
    value = Integer.parseInt(valueStr);
  }

  @Override
  public Type getResolvedType() {
    return Type.INT_TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitLdcInsn(value);
  }
}
