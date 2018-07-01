package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ParametersContext;
import com.keenant.myth.lang.Parameter;
import com.keenant.myth.lang.Parameters;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class ParametersVisitor extends MythBaseVisitor<Parameters> {
  @Override
  public Parameters visitParameters(@Nullable ParametersContext ctx) {
    if (ctx == null || ctx.parameterList() == null) {
      return new Parameters(Collections.emptyList());
    }
    List<Parameter> list = ctx.parameterList().accept(new ParameterListVisitor());
    return new Parameters(list);
  }
}
