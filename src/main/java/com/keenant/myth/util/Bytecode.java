package com.keenant.myth.util;

import java.util.Collection;
import java.util.stream.Collectors;
import org.objectweb.asm.Type;

public class Bytecode {
  public static String getDescriptor(Collection<Type> types) {
    return types.stream()
        .map(Type::getDescriptor)
        .collect(Collectors.joining());
  }

  public static TypeOpcodes getTypeOpcodes(Type type) {
    if (type.equals(Type.INT_TYPE)) {
      return TypeOpcodes.INT;
    }
    else if (type.equals(Type.LONG_TYPE)) {
      return TypeOpcodes.LONG;
    }
    else if (type.equals(Type.FLOAT_TYPE)) {
      return TypeOpcodes.FLOAT;
    }
    else if (type.equals(Type.DOUBLE_TYPE)) {
      return TypeOpcodes.DOUBLE;
    }
    else if (type.equals(Type.VOID_TYPE)) {
      return TypeOpcodes.VOID;
    }
    else if (type.equals(Type.BOOLEAN_TYPE)) {
      return TypeOpcodes.INT;
    }
    else {
      return TypeOpcodes.OBJECT;
    }
  }
}
