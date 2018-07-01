package com.keenant.myth.lang;

import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class ClassType extends NonArrayType {
  private final String name;

  public ClassType(String name) {
    this.name = name;
  }

  @Override
  public Type resolveType() {
    try {
      return Type.getType(Class.forName(name));
    }
    catch (ClassNotFoundException e) {

    }

    try {
      return Type.getType(Class.forName("java.lang." + name));
    }
    catch (ClassNotFoundException e) {

    }

    throw new IllegalArgumentException("Class not found: " + name);
  }
}
