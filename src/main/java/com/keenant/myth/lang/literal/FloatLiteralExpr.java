package com.keenant.myth.lang.literal;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class FloatLiteralExpr extends LiteralExpr {
  private final String valueStr;

  private float value;

  public FloatLiteralExpr(String valueStr) {
    this.valueStr = valueStr;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    value = Float.valueOf(valueStr);
  }

  @Override
  public Type getResolvedType() {
    return Type.FLOAT_TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitLdcInsn(value);
  }
}
