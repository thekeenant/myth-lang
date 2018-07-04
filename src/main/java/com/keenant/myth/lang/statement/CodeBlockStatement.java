package com.keenant.myth.lang.statement;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.Block;
import com.keenant.myth.lang.scope.LocalScope;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;

@ToString
public class CodeBlockStatement extends Statement {
  private final Block block;

  public CodeBlockStatement(Block block) {
    this.block = block;
  }

  @Override
  public void analyze(LocalScope scope, CompileContext context) {
    LocalScope nested = new LocalScope(scope);
    block.analyze(nested, context);
  }

  @Override
  public void codegen(MethodVisitor mv) {
    block.codegen(mv);
  }
}
