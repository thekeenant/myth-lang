package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ClassTypeContext;
import com.keenant.myth.lang.ClassType;

public class ClassTypeVisitor extends MythBaseVisitor<ClassType> {
  @Override
  public ClassType visitClassType(ClassTypeContext ctx) {
    String name = ctx.getText();
    return new ClassType(name);
  }
}
