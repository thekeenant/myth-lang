package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.ToString;

@ToString
public class ImportDeclaration {
  private final String name;

  public ImportDeclaration(String name) {
    this.name = name;
  }

  public void analyze(CompileContext context) {
    String[] qualifiers = name.split("\\.");

    // grab name of class or *
    String name = qualifiers[qualifiers.length - 1];

    // grab all except name
    String[] packageArray = Arrays.copyOfRange(qualifiers, 0, qualifiers.length - 1);

    // join package array
    String qualifiedPackage = Arrays.stream(packageArray).collect(Collectors.joining("."));

    context.addImport(name, qualifiedPackage);
  }
}
