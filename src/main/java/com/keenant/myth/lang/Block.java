package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.LocalScope;
import com.keenant.myth.lang.statement.BlockStatement;
import java.util.List;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;

@ToString
public class Block {
  private final List<BlockStatement> statements;

  public Block(List<BlockStatement> statements) {
    this.statements = statements;
  }

  public void analyze(LocalScope scope, CompileContext context) {
    statements.forEach(statement -> statement.analyze(scope, context));
  }

  public void codegen(MethodVisitor mv) {
    statements.forEach(statement -> statement.codegen(mv));
  }
}
