package com.keenant.myth.lang;

import lombok.ToString;

@ToString
public class ImportDeclaration {
  private final String name;

  public ImportDeclaration(String name) {
    this.name = name;
  }
}
