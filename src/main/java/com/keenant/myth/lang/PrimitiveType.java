package com.keenant.myth.lang;

import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class PrimitiveType extends NonArrayType {
  private final Type type;

  public PrimitiveType(Type type) {
    this.type = type;
  }

  @Override
  public Type resolveType() {
    return type;
  }
}
