package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class PrimitiveType extends NonArrayType {
  private final Type type;

  public PrimitiveType(Type type) {
    this.type = type;
  }

  @Override
  public Type resolveType(CompileContext context) {
    return type;
  }

  @Override
  public String getClassName() {
    return type.getClassName();
  }
}
