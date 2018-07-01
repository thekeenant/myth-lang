package com.keenant.myth.lang;

import lombok.ToString;
import org.objectweb.asm.ClassWriter;

@ToString
public abstract class MemberDeclaration {
  public abstract void analyze(ClassDeclaration context);

  public abstract void codegen(ClassWriter cw);
}
