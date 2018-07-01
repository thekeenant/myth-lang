package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ParameterListContext;
import com.keenant.myth.lang.Parameter;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterListVisitor extends MythBaseVisitor<List<Parameter>> {
  @Override
  public List<Parameter> visitParameterList(ParameterListContext ctx) {
    return ctx.parameter().stream()
        .map(param -> param.accept(new ParameterVisitor()))
        .collect(Collectors.toList());
  }
}
