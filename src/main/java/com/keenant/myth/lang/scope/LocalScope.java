package com.keenant.myth.lang.scope;

import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.variable.LocalVariable;
import org.objectweb.asm.Type;

public class LocalScope extends Scope {
  private int currLocalIndex;

  public LocalScope(Scope parent) {
    super(parent);
  }

  public LocalVariable setLocal(String name, MythType type) {
    int index = nextLocalIndex();
    LocalVariable variable = new LocalVariable(type, index);
    set(name, variable);
    return variable;
  }

  private int nextLocalIndex() {
    if (parent instanceof LocalScope) {
      LocalScope parentLocal = (LocalScope) parent;
      parentLocal.nextLocalIndex();
    }

    return currLocalIndex++;
  }
}
