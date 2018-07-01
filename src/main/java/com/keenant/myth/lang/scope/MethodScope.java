package com.keenant.myth.lang.scope;

import com.keenant.myth.lang.MethodDeclaration;

public class MethodScope extends LocalScope {
  private final MethodDeclaration method;

  public MethodScope(Scope parent, MethodDeclaration method) {
    super(parent);
    this.method = method;
  }
}
