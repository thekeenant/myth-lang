package com.keenant.myth.lang.variable;

import com.keenant.myth.lang.MethodDeclaration;

public class MethodSymbol extends Symbol {
  private final MethodDeclaration method;

  public MethodSymbol(MethodDeclaration method) {
    this.method = method;
  }
}
