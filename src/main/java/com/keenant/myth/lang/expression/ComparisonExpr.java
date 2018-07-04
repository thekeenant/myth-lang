package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import com.keenant.myth.util.Bytecode;
import com.keenant.myth.util.TypeOpcodes;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ComparisonExpr extends Expression {

  private final Expression expr1;
  private final Expression expr2;
  private final ComparisonOperator operator;

  public enum ComparisonOperator {
    LEQ,
    GEQ,
    LESS,
    GREATER,
    EQUAL,
    NEQ,
  }

  public ComparisonExpr(Expression expr1, Expression expr2, ComparisonOperator operator) {
    this.expr1 = expr1;
    this.expr2 = expr2;
    this.operator = operator;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    expr1.analyze(scope, context);
    expr2.analyze(scope, context);

    if (!expr1.getResolvedType().equals(expr2.getResolvedType())) {
      throw new UnsupportedOperationException("Comparisons must be of like-types currently");
    }
  }

  @Override
  public Type getResolvedType() {
    return Type.BOOLEAN_TYPE;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    expr1.codegen(mv);
    expr2.codegen(mv);

    Label trueLabel = new Label();
    Label endLabel = new Label();

    // TODO: Support other than ints
    switch (operator) {
      case LEQ:
        mv.visitJumpInsn(Opcodes.IF_ICMPLE, trueLabel);
        break;
      case GEQ:
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, trueLabel);
        break;
      case LESS:
        mv.visitJumpInsn(Opcodes.IF_ICMPLT, trueLabel);
        break;
      case GREATER:
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, trueLabel);
        break;
      case EQUAL:
        mv.visitJumpInsn(Opcodes.IF_ICMPEQ, trueLabel);
        break;
      case NEQ:
        mv.visitJumpInsn(Opcodes.IF_ICMPNE, trueLabel);
        break;
    }
    mv.visitInsn(Opcodes.ICONST_0);
    mv.visitJumpInsn(Opcodes.GOTO, endLabel);
    mv.visitLabel(trueLabel);
    mv.visitInsn(Opcodes.ICONST_1);
    mv.visitLabel(endLabel);
  }
}
