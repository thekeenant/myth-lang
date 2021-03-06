package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ConstructorCallContext;
import com.keenant.myth.MythParser.ExpressionContext;
import com.keenant.myth.lang.ClassType;
import com.keenant.myth.lang.expression.ArithmeticExpr;
import com.keenant.myth.lang.expression.ArithmeticExpr.MathOperator;
import com.keenant.myth.lang.expression.AssignmentExpr;
import com.keenant.myth.lang.expression.ComparisonExpr;
import com.keenant.myth.lang.expression.ComparisonExpr.ComparisonOperator;
import com.keenant.myth.lang.expression.ConstructorCallExpr;
import com.keenant.myth.lang.expression.Expression;
import com.keenant.myth.lang.expression.IdentExpr;
import com.keenant.myth.lang.expression.MethodCallExpr;
import com.keenant.myth.lang.expression.ReferenceExpr;
import com.keenant.myth.lang.expression.StaticReferenceExpr;
import java.util.Collections;
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
          List<Expression> args = ctx.methodCall().expressionList() == null ?
              Collections.emptyList() :
              ctx.methodCall().expressionList().accept(new ExpressionListVisitor());
          return new MethodCallExpr(name, args, expr1);
        }
      }
      else {
        Expression expr2 = ctx.expression(1).accept(this);
        if (bop.equals("+")) {
          return new ArithmeticExpr(expr1, expr2, MathOperator.ADD);
        }
        else if (bop.equals("-")) {
          return new ArithmeticExpr(expr1, expr2, MathOperator.SUB);
        }
        else if (bop.equals("*")) {
          return new ArithmeticExpr(expr1, expr2, MathOperator.MUL);
        }
        else if (bop.equals("/")) {
          return new ArithmeticExpr(expr1, expr2, MathOperator.DIV);
        }
        else if (bop.equals("<=")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.LEQ);
        }
        else if (bop.equals("<")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.LESS);
        }
        else if (bop.equals(">=")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.GEQ);
        }
        else if (bop.equals(">")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.GREATER);
        }
        else if (bop.equals("!=")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.NEQ);
        }
        else if (bop.equals("==")) {
          return new ComparisonExpr(expr1, expr2, ComparisonOperator.EQUAL);
        }
        else if (bop.equals("=")) {
          if (!(expr1 instanceof ReferenceExpr)) {
            throw new UnsupportedOperationException("Left side of assignment must be reference: " + expr1);
          }
          return new AssignmentExpr((ReferenceExpr) expr1, expr2);
        }
      }
    }
    else if (ctx.constructorCall() != null) {
      ConstructorCallContext childCtx = ctx.constructorCall();

      ClassType type = childCtx.classType().accept(new ClassTypeVisitor());
      List<Expression> args = childCtx.expressionList() == null ?
          Collections.emptyList() :
          childCtx.expressionList().accept(new ExpressionListVisitor());

      return new ConstructorCallExpr(type, args);
    }
    else if (ctx.methodCall() != null) {
      String name = ctx.methodCall().IDENT().getText();
      List<Expression> args = ctx.methodCall().expressionList() == null ?
          Collections.emptyList() :
          ctx.methodCall().expressionList().accept(new ExpressionListVisitor());
      return new MethodCallExpr(name, args, null);
    }
    else if (ctx.IDENT() != null) {
      return new IdentExpr(ctx.IDENT().getText(), null);
    }

    throw new UnsupportedOperationException("Unknown expression: " + ctx.getText());
  }
}
