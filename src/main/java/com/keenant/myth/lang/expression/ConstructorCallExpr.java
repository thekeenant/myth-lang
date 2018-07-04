package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.ClassType;
import com.keenant.myth.lang.scope.Scope;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@ToString
public class ConstructorCallExpr extends Expression {
  private final ClassType type;
  private final List<Expression> args;

  private Constructor javaConstructor;
  private Type methodType;
  private Type returnType;

  public ConstructorCallExpr(ClassType type, List<Expression> args) {
    this.type = type;
    this.args = args;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    returnType = type.resolveType(context);
    args.forEach(arg -> arg.analyze(scope, context));

    List<Type> argTypes = args.stream()
        .map(Expression::getResolvedType)
        .collect(Collectors.toList());

    try {
      Class<?> clazz = Class.forName(returnType.getClassName());
      for (Constructor constructor : clazz.getConstructors()) {
        String constructorDesc = Type.getConstructorDescriptor(constructor);
        Type[] paramTypeArr = Type.getArgumentTypes(constructorDesc);
        List<Type> paramTypes = Arrays.asList(paramTypeArr);

        if (argTypes.equals(paramTypes)) {
          javaConstructor = constructor;
          methodType = Type.getMethodType(Type.VOID_TYPE, paramTypeArr);
        }
      }
    }
    catch (Exception e) {
      // ignore
    }
  }

  @Override
  public Type getResolvedType() {
    return returnType;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    mv.visitTypeInsn(
        Opcodes.NEW,
        returnType.getInternalName()
    );
    mv.visitInsn(Opcodes.DUP);
    args.forEach(expr -> expr.codegen(mv));

    mv.visitMethodInsn(
        Opcodes.INVOKESPECIAL,
        returnType.getInternalName(),
        "<init>",
        methodType.getDescriptor(),
        false
    );
  }
}
