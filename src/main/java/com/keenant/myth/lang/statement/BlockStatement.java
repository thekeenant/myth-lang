package com.keenant.myth.lang.statement;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.LocalScope;
import org.objectweb.asm.MethodVisitor;

public abstract class BlockStatement {
  public abstract void analyze(LocalScope scope, CompileContext context);

  public abstract void codegen(MethodVisitor mv);
}
