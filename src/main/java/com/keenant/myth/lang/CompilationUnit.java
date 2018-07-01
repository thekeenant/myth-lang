package com.keenant.myth.lang;

import java.util.List;
import lombok.ToString;

@ToString
public class CompilationUnit {
  private final List<ImportDeclaration> imports;
  private final ClassDeclaration classDeclaration;

  public CompilationUnit(List<ImportDeclaration> imports, ClassDeclaration classDeclaration) {
    this.imports = imports;
    this.classDeclaration = classDeclaration;
  }

  public void analyze() {
    classDeclaration.analyze();
  }

  public byte[] codegen() {
    return classDeclaration.codegen();
  }

}
