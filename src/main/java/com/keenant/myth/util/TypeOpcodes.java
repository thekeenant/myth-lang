package com.keenant.myth.util;

import static org.objectweb.asm.Opcodes.*;

public enum TypeOpcodes {
  INT     (ILOAD, ISTORE, IRETURN, IADD, ISUB, IMUL, IDIV),
  LONG    (LLOAD, LSTORE, LRETURN, LADD, LSUB, LMUL, LDIV),
  FLOAT   (FLOAD, FSTORE, FRETURN, FADD, FSUB, FMUL, FDIV),
  DOUBLE  (DLOAD, DSTORE, DRETURN, DADD, DSUB, DMUL, DDIV),
  VOID    (ALOAD, ASTORE, RETURN),
  OBJECT  (ALOAD, ASTORE, ARETURN);

  public final int load;
  public final int store;
  public final int ret;
  public final int add;
  public final int sub;
  public final int mul;
  public final int div;

  TypeOpcodes(int load, int store, int ret, int add, int sub, int mul, int div) {
    this.load = load;
    this.store = store;
    this.ret = ret;
    this.add = add;
    this.sub = sub;
    this.mul = mul;
    this.div = div;
  }

  TypeOpcodes(int load, int store, int ret) {
    this(load, store, ret, 0, 0, 0, 0);
  }
}
