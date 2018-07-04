package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
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
    CompileContext context = new CompileContext();

    imports.forEach(i -> i.analyze(context));
    classDeclaration.analyze(context);
  }

  public byte[] codegen() {
    return classDeclaration.codegen();
  }

}
