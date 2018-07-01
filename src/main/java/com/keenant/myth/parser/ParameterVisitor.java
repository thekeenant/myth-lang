package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ParameterContext;
import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.Parameter;

public class ParameterVisitor extends MythBaseVisitor<Parameter> {
  @Override
  public Parameter visitParameter(ParameterContext ctx) {
    String name = ctx.IDENT().getText();
    MythType type = ctx.type().accept(new TypeVisitor());

    return new Parameter(name, type);
  }
}
