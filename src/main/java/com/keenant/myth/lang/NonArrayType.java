package com.keenant.myth.lang;

import com.keenant.myth.CompileContext;
import org.objectweb.asm.Type;

public abstract class NonArrayType {
  public abstract Type resolveType(CompileContext context);

  public abstract String getClassName();
}
