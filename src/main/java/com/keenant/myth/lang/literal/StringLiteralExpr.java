package com.keenant.myth.lang.literal;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

@ToString
public class StringLiteralExpr extends LiteralExpr {
  private static final Type TYPE = Type.getType(String.class);

  private String value;

  public StringLiteralExpr(String value) {
    this.value = value;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    value = value.replace("\\\"", "\"");
    // nothing to do
  }

  @Override
  public Type getResolvedType() {
    return TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitLdcInsn(value);
  }
}
