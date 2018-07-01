package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.TypeContext;
import com.keenant.myth.lang.MythType;
import com.keenant.myth.lang.NonArrayType;

public class TypeVisitor extends MythBaseVisitor<MythType> {
  @Override
  public MythType visitType(TypeContext ctx) {
    NonArrayType type;

    if (ctx.classType() != null) {
      type = ctx.classType().accept(new ClassTypeVisitor());
    }
    else if (ctx.primitiveType() != null) {
      type = ctx.primitiveType().accept(new PrimitiveTypeVisitor());
    }
    else {
      throw new UnsupportedOperationException("Unknown type: " + ctx.getText());
    }

    return new MythType<>(type, ctx.BRACKETS().size());
  }
}
