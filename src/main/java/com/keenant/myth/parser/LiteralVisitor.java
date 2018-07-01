package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.IntegerLiteralContext;
import com.keenant.myth.MythParser.LiteralContext;
import com.keenant.myth.lang.literal.IntLiteralExpr;
import com.keenant.myth.lang.literal.LiteralExpr;

public class LiteralVisitor extends MythBaseVisitor<LiteralExpr> {
  @Override
  public LiteralExpr visitLiteral(LiteralContext ctx) {
    if (ctx.integerLiteral() != null) {
      return ctx.integerLiteral().accept(this);
    }
    else {
      throw new UnsupportedOperationException("Unknown literal: " + ctx.getText());
    }
  }

  @Override
  public LiteralExpr visitIntegerLiteral(IntegerLiteralContext ctx) {
    String valueStr = ctx.getText();
    return new IntLiteralExpr(valueStr);
  }
}
