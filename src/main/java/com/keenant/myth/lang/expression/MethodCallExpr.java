package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.ClassType;
import com.keenant.myth.lang.scope.Scope;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class MethodCallExpr extends Expression {
  private final String name;
  private final List<Expression> args;
  private final Expression owner;

  private ConstructorCallExpr constructorCall;

  private Type methodType;
  private Type returnType;

  public MethodCallExpr(String name, List<Expression> args, Expression owner) {
    this.name = name;
    this.args = args;
    this.owner = owner;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    // try as a constructor first
    try {
      IdentExpr test = new IdentExpr(name, owner);
      test.analyze(scope, context);
      test.getResolvedType();

      constructorCall = new ConstructorCallExpr(new ClassType(test.qualifiedName()), args);
      constructorCall.analyze(scope, context);
      return;
    } catch (Exception e) {
      // ignore error, we try method call
    }

    if (owner == null) {
      throw new UnsupportedOperationException("Not found: " + name);
    }
    else {
      owner.analyze(scope, context);
      args.forEach(arg -> arg.analyze(scope, context));

      List<Type> argTypes = args.stream()
          .map(Expression::getResolvedType)
          .collect(Collectors.toList());

      Type ownerType = owner.getResolvedType();
      Class<?> clazz;
      try {
        clazz = Class.forName(ownerType.getClassName());
      }
      catch (ClassNotFoundException e) {
        throw new IllegalStateException(e);
      }
      for (Method method : clazz.getDeclaredMethods()) {
        if (method.getName().equals(name)) {
          List<Type> paramTypes = Arrays.asList(Type.getArgumentTypes(method));

          if (argTypes.equals(paramTypes)) {
            methodType = Type.getType(method);
            returnType = Type.getType(method.getReturnType());
            return;
          }
        }
      }
    }
  }

  @Override
  public Type getResolvedType() {
    if (constructorCall != null) {
      return constructorCall.getResolvedType();
    }
    return returnType;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    if (constructorCall != null) {
      constructorCall.codegen(mv);
      return;
    }

    owner.codegen(mv);
    args.forEach(expr -> expr.codegen(mv));

    mv.visitMethodInsn(
        Opcodes.INVOKEVIRTUAL,
        owner.getResolvedType().getInternalName(),
        name,
        methodType.getDescriptor(),
        false
    );
  }
}
