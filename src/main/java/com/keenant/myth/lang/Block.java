package com.keenant.myth.lang;

import com.keenant.myth.lang.scope.LocalScope;
import com.keenant.myth.lang.scope.MethodScope;
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

  public void analyze(LocalScope scope) {
    statements.forEach(statement -> statement.analyze(scope));
  }

  public void codegen(MethodVisitor mv) {
    statements.forEach(statement -> statement.codegen(mv));
  }
}
