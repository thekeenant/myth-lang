package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.PrimitiveTypeContext;
import com.keenant.myth.lang.PrimitiveType;
import org.objectweb.asm.Type;

public class PrimitiveTypeVisitor extends MythBaseVisitor<PrimitiveType> {
  @Override
  public PrimitiveType visitPrimitiveType(PrimitiveTypeContext ctx) {
    String name = ctx.getText();

    if (name.equals("bool")) {
      name = "boolean";
    }

    // TODO: Check if this works
    return new PrimitiveType(Type.getType(name));
  }
}
