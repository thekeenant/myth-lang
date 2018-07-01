package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.BlockStatementContext;
import com.keenant.myth.lang.statement.BlockStatement;

public class BlockStatementVisitor extends MythBaseVisitor<BlockStatement> {

  @Override
  public BlockStatement visitBlockStatement(BlockStatementContext ctx) {
    if (ctx.localVariableDeclaration() != null) {
      return ctx.localVariableDeclaration().accept(new LocalVariableDeclarationVisitor());
    }
    else if (ctx.statement() != null) {
      return ctx.statement().accept(new StatementVisitor());
    }
    else {
      throw new UnsupportedOperationException("Unknown block statement: " + ctx.getText());
    }
  }
}
