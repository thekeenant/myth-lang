package com.keenant.myth.lang.statement;

import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.scope.LocalScope;
import com.keenant.myth.lang.variable.LocalVariable;
import javax.annotation.Nullable;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@ToString
public class LocalVariableDeclaration extends BlockStatement {
  private final String name;
  private final MythType type;
  private final Expression expr;

  private LocalVariable symbol;

  public LocalVariableDeclaration(String name, MythType type, @Nullable Expression expr) {
    this.name = name;
    this.type = type;
    this.expr = expr;
  }

  @Override
  public void analyze(LocalScope scope) {
    symbol = scope.setLocal(name, type);

    if (expr != null) {
      expr.analyze(scope);
    }
  }

  @Override
  public void codegen(MethodVisitor mv) {
    if (expr != null) {
      int index = symbol.getIndex();

      expr.codegen(mv);

      // TODO: There are other types...
      mv.visitVarInsn(
          Opcodes.ASTORE,
          index
      );
    }
  }
}
