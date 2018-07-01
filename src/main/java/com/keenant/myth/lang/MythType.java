package com.keenant.myth.lang;

import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class MythType<T extends NonArrayType> {
  private final T type;
  private final int arrayDimensions;

  public MythType(T type, int arrayDimensions) {
    this.type = type;
    this.arrayDimensions = arrayDimensions;
  }

  public Type resolveType() {
    Type childType = type.resolveType();

    // TODO: Make sure this works?
    StringBuilder result = new StringBuilder(childType.getDescriptor());
    for (int i = 0; i < arrayDimensions; i++) {
      result.insert(0, "[");
    }

    return Type.getType(result.toString());
  }
}
