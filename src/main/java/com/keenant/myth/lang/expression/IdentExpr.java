package com.keenant.myth.lang.expression;

import com.keenant.myth.CompileContext;
import com.keenant.myth.lang.ClassType;
import com.keenant.myth.lang.scope.Scope;
import com.keenant.myth.lang.variable.LocalVariable;
import com.keenant.myth.util.Bytecode;
import com.keenant.myth.util.TypeOpcodes;
import lombok.ToString;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

@ToString(callSuper = true)
public class IdentExpr extends ReferenceExpr {
  private final Expression owner;

  private Type type;

  private LocalVariable localVariable;

  public IdentExpr(String name, Expression owner) {
    super(name);
    this.owner = owner;
  }

  public String qualifiedName() {
    String result = name;
    if (owner instanceof IdentExpr) {
      result = ((IdentExpr) owner).qualifiedName() + "." + result;
    }
    return result;
  }

  @Override
  public void analyze(Scope scope, CompileContext context) {
    String qualifiedName = qualifiedName();

    if (owner == null) {
      boolean identExists = scope.exists(qualifiedName);
      if (identExists) {
        localVariable = scope.lookup(qualifiedName, LocalVariable.class);
        type = localVariable.getType().resolveType(context);
        return;
      }
    }
    else {
      owner.analyze(scope, context);
    }

    ClassType classType = new ClassType(qualifiedName());
    try {
      type = classType.resolveType(context);
    } catch (Exception e) {

    }
  }

  @Override
  public Type getResolvedType() {
    if (type == null) {
      throw new IllegalStateException("Identifier not found: " + name);
    }
    return type;
  }

  @Override
  public void codegen(MethodVisitor mv) {
    codegenGet(mv);
  }

  @Override
  public void codegenGet(MethodVisitor mv) {
    TypeOpcodes opcodes = Bytecode.getTypeOpcodes(type);

    mv.visitVarInsn(
        opcodes.load,
        localVariable.getIndex()
    );
  }

  @Override
  public void codegenSet(MethodVisitor mv) {
    TypeOpcodes opcodes = Bytecode.getTypeOpcodes(type);

    mv.visitVarInsn(
        opcodes.store,
        localVariable.getIndex()
    );
  }
}
