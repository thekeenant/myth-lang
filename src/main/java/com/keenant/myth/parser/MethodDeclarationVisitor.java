package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.MethodDeclarationContext;
import com.keenant.myth.lang.Block;
import com.keenant.myth.lang.MethodDeclaration;
import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.Parameters;

public class MethodDeclarationVisitor extends MythBaseVisitor<MethodDeclaration> {
  private final boolean isStatic;

  public MethodDeclarationVisitor(boolean isStatic) {
    this.isStatic = isStatic;
  }

  @Override
  public MethodDeclaration visitMethodDeclaration(MethodDeclarationContext ctx) {
    String methodName = ctx.IDENT().getText();
    Parameters params = ctx.parameters().accept(new ParametersVisitor());
    Block block = ctx.block().accept(new BlockVisitor());
    MythType returnType = null;

    if (ctx.type() != null) {
      returnType = ctx.type().accept(new TypeVisitor());
    }

    return new MethodDeclaration(methodName, params, returnType, block, isStatic);
  }
}
