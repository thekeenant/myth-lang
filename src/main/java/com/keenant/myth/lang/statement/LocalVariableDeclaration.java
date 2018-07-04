package com.keenant.myth.lang.statement;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.scope.LocalScope;
import com.keenant.myth.lang.variable.LocalVariable;
import com.keenant.myth.util.Bytecode;
import com.keenant.myth.util.TypeOpcodes;
import javax.annotation.Nullable;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class LocalVariableDeclaration extends BlockStatement {
  private final String name;
  private final MythType type;
  private final Expression expr;

  private Type resolvedType;
  private LocalVariable symbol;

  public LocalVariableDeclaration(String name, @Nullable MythType type, @Nullable Expression expr) {
    this.name = name;
    this.type = type;
    this.expr = expr;
  }

  @Override
  public void analyze(LocalScope scope, CompileContext context) {
    if (type == null && expr == null) {
      throw new UnsupportedOperationException("Type or expression must be provided");
    }

    if (expr != null) {
      expr.analyze(scope, context);
    }

    resolvedType = type == null ? expr.getResolvedType() : type.resolveType(context);

    symbol = scope.setLocal(name, new MythType(resolvedType));

  }

  @Override
  public void codegen(MethodVisitor mv) {
    if (expr != null) {
      int index = symbol.getIndex();

      expr.codegen(mv);

      TypeOpcodes opcodes = Bytecode.getTypeOpcodes(resolvedType);

      mv.visitVarInsn(
          opcodes.store,
          index
      );
    }
  }
}
