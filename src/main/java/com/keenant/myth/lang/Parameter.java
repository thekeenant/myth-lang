package com.keenant.myth.lang;

import com.keenant.myth.lang.scope.MethodScope;
import com.keenant.myth.lang.variable.LocalVariable;
import lombok.ToString;
import org.objectweb.asm.Type;

@ToString
public class Parameter {
  private final String name;
  private final MythType type;

  private Type resolvedType;
  private LocalVariable symbol;

  public Parameter(String name, MythType type) {
    this.name = name;
    this.type = type;
  }

  public void analyze(MethodScope scope) {
    symbol = scope.setLocal(name, type);
    resolvedType = type.resolveType();
  }

  public Type getResolvedType() {
    return resolvedType;
  }
}
