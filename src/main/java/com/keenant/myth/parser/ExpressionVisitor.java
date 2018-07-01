package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ExpressionContext;
import com.keenant.myth.MythParser.MethodCallContext;
import com.keenant.myth.lang.expression.AssignmentExpr;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.expression.IdentExpr;
import com.keenant.myth.lang.expression.MethodCallExpr;
import com.keenant.myth.lang.expression.ReferenceExpr;
import com.keenant.myth.lang.expression.StaticReferenceExpr;
import java.util.List;

public class ExpressionVisitor extends MythBaseVisitor<Expression> {
  @Override
  public Expression visitExpression(ExpressionContext ctx) {
    if (ctx.literal() != null) {
      return ctx.literal().accept(new LiteralVisitor());
    }
    else if (ctx.bop != null) {
      String bop = ctx.bop.getText();
      Expression expr1 = ctx.expression(0).accept(this);

      if (bop.equals(".") || bop.equals("::")) {
        boolean isStatic = bop.equals("::");

        if (ctx.IDENT() != null) {
          String name = ctx.IDENT().getText();

          if (isStatic) {
            return new StaticReferenceExpr(name, expr1);
          }
          else {
            return new IdentExpr(name, expr1);
          }
        }
        else if (ctx.methodCall() != null) {
          String name = ctx.methodCall().IDENT().getText();
          List<Expression> args = ctx.methodCall().expressionList().accept(new ExpressionListVisitor());
          return new MethodCallExpr(name, args, expr1);
        }
      }
      else {
        Expression expr2 = ctx.expression(1).accept(this);
        if (bop.equals("=")) {
          if (!(expr1 instanceof ReferenceExpr)) {
            throw new UnsupportedOperationException("Left side of assignment must be reference: " + expr1);
          }
          return new AssignmentExpr((ReferenceExpr) expr1, expr2);
        }
      }
    }
    else if (ctx.IDENT() != null) {
      return new IdentExpr(ctx.IDENT().getText(), null);
    }

    throw new UnsupportedOperationException("Unknown expression: " + ctx.getText());
  }

  @Override
  public Expression visitMethodCall(MethodCallContext ctx) {
    return super.visitMethodCall(ctx);
  }
}
