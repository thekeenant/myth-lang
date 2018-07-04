package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import lombok.ToString;
import org.objectweb.asm.ClassWriter;

@ToString
public abstract class MemberDeclaration {
  public abstract void analyze(ClassDeclaration classDeclaration, CompileContext context);

  public abstract void codegen(ClassWriter cw);
}
