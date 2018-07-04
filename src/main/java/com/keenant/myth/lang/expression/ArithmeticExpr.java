package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import com.keenant.myth.util.Bytecode;
import com.keenant.myth.util.TypeOpcodes;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class ArithmeticExpr extends Expression {
  private final Expression expr1;
  private final Expression expr2;
  private final MathOperator mode;

  public enum MathOperator {
    ADD,
    SUB,
    MUL,
    DIV
  }

  public ArithmeticExpr(Expression expr1 , Expression expr2, MathOperator mode) {
    this.expr1 = expr1;
    this.expr2 = expr2;
    this.mode = mode;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    expr1.analyze(scope, context);
    expr2.analyze(scope, context);

    if (!expr1.getResolvedType().equals(expr2.getResolvedType())) {
      throw new UnsupportedOperationException("Arithmetic only supported for like-types currently");
    }
  }

  @Override
  public Type getResolvedType() {
    return expr1.getResolvedType();
  }

  @Override
  public void codegen(MethodVisitor mv) {
    expr1.codegen(mv);
    expr2.codegen(mv);

    TypeOpcodes opcodes = Bytecode.getTypeOpcodes(getResolvedType());
    int opcode = -1;

    switch (mode) {
      case ADD:
        opcode = opcodes.add;
        break;
      case SUB:
        opcode = opcodes.sub;
        break;
      case MUL:
        opcode = opcodes.mul;
        break;
      case DIV:
        opcode = opcodes.div;
        break;
    }

    mv.visitInsn(opcode);
  }
}
