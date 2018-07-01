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
}
