package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.LocalVariableDeclarationContext;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.statement.LocalVariableDeclaration;
import com.keenant.myth.lang.MythType;

public class LocalVariableDeclarationVisitor extends MythBaseVisitor<LocalVariableDeclaration> {
  @Override
  public LocalVariableDeclaration visitLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
    String name = ctx.IDENT().getText();
    MythType type = ctx.type() == null ? null : ctx.type().accept(new TypeVisitor());

    Expression expr = ctx.expression() == null ? null : ctx.expression().accept(new ExpressionVisitor());

    return new LocalVariableDeclaration(name, type, expr);
  }
}
