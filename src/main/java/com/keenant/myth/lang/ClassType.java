package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class ClassType extends NonArrayType {
  private final String name;

  public ClassType(String name) {
    this.name = name;
  }

  @Override
  public Type resolveType(CompileContext context) {
    return context.lookupType(name);
  }

  @Override
  public String getClassName() {
    return name;
  }
}
