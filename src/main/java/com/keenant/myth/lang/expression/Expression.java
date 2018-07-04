package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.scope.Scope;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public abstract class Expression {
  public abstract void analyze(Scope scope, CompileContext context);

  public abstract Type getResolvedType();

  public abstract void codegen(MethodVisitor mv);
}
