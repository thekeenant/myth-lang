package com.keenant.myth.lang.scope;

import com.keenant.myth.lang.variable.Symbol;
import java.util.HashMap;
import java.util.Map;

public class Scope {
  protected final Scope parent;
  private final Map<String, Symbol> table = new HashMap<>();

  public Scope(Scope parent) {
    this.parent = parent;
  }

  public Symbol lookup(String name) {
    return lookup(name, Symbol.class);
  }

  public boolean exists(String name) {
    // todo: make better :0
    try {
      lookup(name);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  public <T extends Symbol> T lookup(String name, Class<T> forceType) {
    Scope curr = this;

    // keep looking at parents until we find one
    while (curr != null) {
      Symbol result = table.get(name);

      if (result != null) {
        if (forceType.isInstance(result)) {
          return (T) result;
        }
        else {
          throw new IllegalStateException("Type mismatch for " + name + ", expected " + forceType.getSimpleName());
        }
      }

      curr = curr.parent;
    }

    throw new IllegalArgumentException("No variable found in scope: " + name);
  }

  public void set(String name, Symbol symbol) {
    if (table.containsKey(name)) {
      throw new IllegalArgumentException("Duplicate symbol defined: " + name);
    }
    table.put(name, symbol);
  }
}
