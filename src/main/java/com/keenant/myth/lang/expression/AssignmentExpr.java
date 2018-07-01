package com.keenant.myth.lang.expression;

import com.keenant.myth.lang.scope.Scope;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

@ToString
public class AssignmentExpr extends Expression {
  private final ReferenceExpr lhs;
  private final Expression rhs;

  private Type type;

  public AssignmentExpr(ReferenceExpr lhs, Expression rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public void analyze(Scope scope) {
    lhs.analyze(scope);
    rhs.analyze(scope);

    if (!lhs.getResolvedType().equals(rhs.getResolvedType())) {
      throw new IllegalStateException("Assignment type mismatch: " + lhs.getResolvedType() + " != " + rhs.getResolvedType());
    }

    type = lhs.getResolvedType();
  }

  @Override
  public Type getResolvedType() {
    return type;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    rhs.codegen(mv);
    lhs.codegenSet(mv);
  }
}
