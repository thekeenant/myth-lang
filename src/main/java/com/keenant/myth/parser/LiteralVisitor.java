package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.BooleanLiteralContext;
import com.keenant.myth.MythParser.DoubleLiteralContext;
import com.keenant.myth.MythParser.FloatLiteralContext;
import com.keenant.myth.MythParser.IntegerLiteralContext;
import com.keenant.myth.MythParser.LiteralContext;
import com.keenant.myth.MythParser.StringLiteralContext;
import com.keenant.myth.lang.literal.BooleanLiteralExpr;
import com.keenant.myth.lang.literal.DoubleLiteralExpr;
import com.keenant.myth.lang.literal.FloatLiteralExpr;
import com.keenant.myth.lang.literal.IntLiteralExpr;
import com.keenant.myth.lang.literal.LiteralExpr;
import com.keenant.myth.lang.literal.StringLiteralExpr;

public class LiteralVisitor extends MythBaseVisitor<LiteralExpr> {
  @Override
  public LiteralExpr visitLiteral(LiteralContext ctx) {
    if (ctx.integerLiteral() != null) {
      return ctx.integerLiteral().accept(this);
    }
    else if (ctx.stringLiteral() != null) {
      return ctx.stringLiteral().accept(this);
    }
    else if (ctx.doubleLiteral() != null) {
      return ctx.doubleLiteral().accept(this);
    }
    else if (ctx.floatLiteral() != null) {
      return ctx.floatLiteral().accept(this);
    }
    else if (ctx.booleanLiteral() != null) {
      return ctx.booleanLiteral().accept(this);
    }
    else {
      throw new UnsupportedOperationException("Unknown literal: " + ctx.getText());
    }
  }

  @Override
  public LiteralExpr visitBooleanLiteral(BooleanLiteralContext ctx) {
    String valueStr = ctx.getText();
    return new BooleanLiteralExpr(valueStr);
  }

  @Override
  public LiteralExpr visitDoubleLiteral(DoubleLiteralContext ctx) {
    String valueStr = ctx.getText();
    return new DoubleLiteralExpr(valueStr);
  }

  @Override
  public LiteralExpr visitFloatLiteral(FloatLiteralContext ctx) {
    String valueStr = ctx.getText();
    if (valueStr.toLowerCase().endsWith("f")) {
      valueStr = valueStr.substring(0, valueStr.length() - 1);
    }
    return new FloatLiteralExpr(valueStr);
  }

  @Override
  public LiteralExpr visitIntegerLiteral(IntegerLiteralContext ctx) {
    String valueStr = ctx.getText();
    return new IntLiteralExpr(valueStr);
  }

  @Override
  public LiteralExpr visitStringLiteral(StringLiteralContext ctx) {
    String value = ctx.getText();
    value = value.substring(1, value.length() - 1);
    return new StringLiteralExpr(value);
  }
}
