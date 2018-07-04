package com.keenant.myth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Type;

public class CompileContext {
  private final Map<String, List<String>> imports = new HashMap<>();

  public CompileContext() {
    addImport("*", "java.lang");
  }

  public void addImport(String name, String packageForImport) {
    imports.computeIfAbsent(name, (key) -> new ArrayList<>());
    imports.get(name).add(packageForImport);
  }

  public Type lookupType(String name) {
    try {
      return Type.getType(Class.forName(name));
    } catch (Exception e) {

    }

    List<String> importPackages = imports.getOrDefault(name, Collections.emptyList());
    for (String importPackage : importPackages) {
      try {
        return Type.getType(Class.forName(importPackage + "." + name));
      }
      catch (ClassNotFoundException e) {
        // continue
      }
    }

    importPackages = imports.get("*");
    for (String importPackage : importPackages) {
      try {
        return Type.getType(Class.forName(importPackage + "." + name));
      }
      catch (ClassNotFoundException e) {
        // continue
      }
    }

    throw new UnsupportedOperationException("Unknown class: " + name);
  }
}
