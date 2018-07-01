package com.keenant.myth.lang.variable;

import com.keenant.myth.lang.MythType;
import lombok.ToString;

@ToString
public class LocalVariable extends Symbol {
  private final MythType type;
  private final int index;

  public LocalVariable(MythType type, int index) {
    this.type = type;
    this.index = index;
  }

  public MythType getType() {
    return type;
  }

  public int getIndex() {
    return index;
  }
}
