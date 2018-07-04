package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.StatementContext;
import com.keenant.myth.lang.Block;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.statement.CodeBlockStatement;
import com.keenant.myth.lang.statement.ExpressionStatement;
import com.keenant.myth.lang.statement.IfStatement;
import com.keenant.myth.lang.statement.Statement;

public class StatementVisitor extends MythBaseVisitor<Statement> {
  @Override
  public Statement visitStatement(StatementContext ctx) {
    if (ctx.statementExpression != null) {
      Expression expr = ctx.statementExpression.accept(new ExpressionVisitor());
      return new ExpressionStatement(expr);
    }
    else if (ctx.codeBlock != null) {
      Block block = ctx.codeBlock.accept(new BlockVisitor());
      return new CodeBlockStatement(block);
    }
    else if (ctx.ifStatement != null) {
      Expression expr = ctx.expression().accept(new ExpressionVisitor());
      Statement statement = ctx.thenStatement.accept(this);
      Statement elseStatement = ctx.elseStatement == null ? null : ctx.elseStatement.accept(this);
      return new IfStatement(expr, statement, elseStatement);
    }
    else {
      throw new UnsupportedOperationException("Unknown statement: " + ctx.getText());
    }
  }
}
