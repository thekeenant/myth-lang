package com.keenant.myth.lang;

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

  public Type resolveType() {
    if (resolvedType != null) {
      return resolvedType;
    }

    if (type == null) {
      throw new IllegalStateException("Type cannot be null");
    }

    Type childType = type.resolveType();

    // TODO: Make sure this works?
    StringBuilder result = new StringBuilder(childType.getDescriptor());
    for (int i = 0; i < arrayDimensions; i++) {
      result.insert(0, "[");
    }

    resolvedType = Type.getType(result.toString());
    return resolvedType;
  }
}
