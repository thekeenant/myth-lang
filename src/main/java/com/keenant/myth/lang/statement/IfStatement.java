package com.keenant.myth.lang.statement;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.scope.LocalScope;
import javax.annotation.Nullable;
import lombok.ToString;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class IfStatement extends Statement {
  private final Expression expr;
  private final Statement statement;
  private final Statement elseStatement;

  public IfStatement(Expression expr, Statement statement, @Nullable Statement elseStatement) {
    this.expr = expr;
    this.statement = statement;
    this.elseStatement = elseStatement;
  }

  @Override
  public void analyze(LocalScope scope, CompileContext context) {
    expr.analyze(scope, context);
    statement.analyze(scope, context);
    if (elseStatement != null) {
      elseStatement.analyze(scope, context);
    }

    if (!Type.BOOLEAN_TYPE.equals(expr.getResolvedType())) {
      throw new IllegalArgumentException("If condition must be boolean, not " + expr.getResolvedType());
    }
  }

  @Override
  public void codegen(MethodVisitor mv) {
    Label trueLabel = new Label();
    Label endLabel = new Label();

    // evaluate expression
    expr.codegen(mv);

    // if boolean is true (aka != 0) jump to true label
    mv.visitJumpInsn(
        Opcodes.IFNE, trueLabel
    );
    if (elseStatement != null) {
      elseStatement.codegen(mv);
    }
    mv.visitJumpInsn(
        Opcodes.GOTO, endLabel
    );
    mv.visitLabel(trueLabel);
    statement.codegen(mv);
    mv.visitLabel(endLabel);
  }
}
