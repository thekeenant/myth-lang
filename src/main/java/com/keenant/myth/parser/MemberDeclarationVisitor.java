package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.MemberDeclarationContext;
import com.keenant.myth.lang.MemberDeclaration;

public class MemberDeclarationVisitor extends MythBaseVisitor<MemberDeclaration> {
  @Override
  public MemberDeclaration visitMemberDeclaration(MemberDeclarationContext ctx) {
    boolean isStatic = ctx.STATIC() != null;

    if (ctx.methodDeclaration() != null) {
      return ctx.methodDeclaration().accept(new MethodDeclarationVisitor(isStatic));
    }

    throw new UnsupportedOperationException("Unknown member declaration: " + ctx.getText());
  }
}
