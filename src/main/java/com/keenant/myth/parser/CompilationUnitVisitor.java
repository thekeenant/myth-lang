package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.CompilationUnitContext;
import com.keenant.myth.lang.ClassDeclaration;
import com.keenant.myth.lang.CompilationUnit;
import com.keenant.myth.lang.ImportDeclaration;
import java.util.List;
import java.util.stream.Collectors;

public class CompilationUnitVisitor extends MythBaseVisitor<CompilationUnit> {
  @Override
  public CompilationUnit visitCompilationUnit(CompilationUnitContext ctx) {
    List<ImportDeclaration> imports = ctx.importDeclaration().stream()
        .map(childCtx -> childCtx.accept(new ImportDeclarationVisitor()))
        .collect(Collectors.toList());

    ClassDeclaration classDeclaration = ctx.classDeclaration().accept(new ClassDeclarationVisitor());

    return new CompilationUnit(imports, classDeclaration);
  }
}
