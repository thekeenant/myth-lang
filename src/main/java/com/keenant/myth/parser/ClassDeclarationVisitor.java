package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ClassDeclarationContext;
import com.keenant.myth.lang.MemberDeclaration;
import com.keenant.myth.lang.ClassDeclaration;
import java.util.List;
import java.util.stream.Collectors;

public class ClassDeclarationVisitor extends MythBaseVisitor<ClassDeclaration> {
  @Override
  public ClassDeclaration visitClassDeclaration(ClassDeclarationContext ctx) {
    String name = ctx.IDENT().getText();
    List<MemberDeclaration> declarations = ctx.memberDeclaration().stream()
        .map(childCtx -> childCtx.accept(new MemberDeclarationVisitor()))
        .collect(Collectors.toList());

    return new ClassDeclaration(name, declarations);
  }
}
