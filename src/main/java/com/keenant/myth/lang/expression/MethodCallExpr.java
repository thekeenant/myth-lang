package com.keenant.myth.lang.expression;

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

  private Method javaMethod;
  private Type methodType;
  private Type returnType;

  public MethodCallExpr(String name, List<Expression> args, Expression owner) {
    this.name = name;
    this.args = args;
    this.owner = owner;
  }

  @Override
  public void analyze(Scope scope) {
    owner.analyze(scope);
    args.forEach(arg -> arg.analyze(scope));

    List<Type> argTypes = args.stream()
        .map(Expression::getResolvedType)
        .collect(Collectors.toList());

    Type ownerType = owner.getResolvedType();

    try {
      Class<?> clazz = Class.forName(ownerType.getClassName());
      for (Method method : clazz.getDeclaredMethods()) {
        if (method.getName().equals(name)) {
          List<Type> paramTypes = Arrays.asList(Type.getArgumentTypes(method));

          if (argTypes.equals(paramTypes)) {
            javaMethod = method;
            methodType = Type.getType(method);
            returnType = Type.getType(method.getReturnType());
            break;
          }
        }
      }
    }
    catch (ClassNotFoundException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Override
  public Type getResolvedType() {
    return returnType;
  }

  @Override
  public void codegen(MethodVisitor mv) {
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
