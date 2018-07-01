package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.StatementContext;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.statement.ExpressionStatement;
import com.keenant.myth.lang.statement.Statement;

public class StatementVisitor extends MythBaseVisitor<Statement> {
  @Override
  public Statement visitStatement(StatementContext ctx) {
    if (ctx.statementExpression != null) {
      Expression expr = ctx.statementExpression.accept(new ExpressionVisitor());
      return new ExpressionStatement(expr);
    }
    else {
      throw new UnsupportedOperationException("Unknown statement: " + ctx.getText());
    }
  }
}
