package com.keenant.myth.lang.literal;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class BooleanLiteralExpr extends LiteralExpr {
  private final String valueStr;

  private boolean value;

  public BooleanLiteralExpr(String valueStr) {
    this.valueStr = valueStr;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    value = Boolean.valueOf(valueStr);
  }

  @Override
  public Type getResolvedType() {
    return Type.BOOLEAN_TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitInsn(value ? Opcodes.ICONST_1 : Opcodes.ICONST_0);
  }
}
