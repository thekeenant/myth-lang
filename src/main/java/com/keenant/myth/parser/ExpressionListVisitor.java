package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ExpressionListContext;
import com.keenant.myth.lang.expression.Expression;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionListVisitor extends MythBaseVisitor<List<Expression>> {
  @Override
  public List<Expression> visitExpressionList(ExpressionListContext ctx) {
    return ctx.expression().stream()
        .map(childCtx -> childCtx.accept(new ExpressionVisitor()))
        .collect(Collectors.toList());
  }
}
