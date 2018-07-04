package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class MythType<T extends NonArrayType> {
  private final T type;
  private final int arrayDimensions;

  private Type resolvedType;

  public MythType(T type, int arrayDimensions) {
    this.type = type;
    this.arrayDimensions = arrayDimensions;
  }

  public MythType(Type resolvedType) {
    this.type = null;
    this.arrayDimensions = -1;
    this.resolvedType = resolvedType;
  }

  public static MythType<ClassType> forClassName(String className) {
    return new MythType<>(new ClassType(className), 0);
  }

  public String getClassName() {
    return type == null ? resolvedType.getClassName() : type.getClassName();
  }

  public Type resolveType(CompileContext context) {
    if (resolvedType != null) {
      return resolvedType;
    }

    if (type == null) {
      throw new IllegalStateException("Type cannot be null");
    }

    Type childType = type.resolveType(context);

    // TODO: Make sure this works?
    StringBuilder result = new StringBuilder(childType.getDescriptor());
    for (int i = 0; i < arrayDimensions; i++) {
      result.insert(0, "[");
    }

    resolvedType = Type.getType(result.toString());
    return resolvedType;
  }
}
