package com.keenant.myth.lang.stmt;

import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.codegen.Scope;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;

@ToString
public class FunctionDefinition extends DeclareStmt {
    private final Params params;
    private final Procedure procedure;
    private final ClassName returnType;

    public FunctionDefinition(ParserRuleContext context, Scope scope, DeclareStmt stmt, Params params, Procedure procedure, ClassName returnType) {
        super(context, scope, stmt.getVarMode(), stmt.getName(), stmt.getDeclaredType().orElse(null));
        this.params = params;
        this.procedure = procedure;
        this.returnType = returnType;
    }

    @Override
    public ClassName resolveType() {
        // Todo: Make this relative to the class name?
        return new ClassName(getName());
    }

    @Override
    public void typeCheck() {
        params.typeCheck();
        procedure.typeCheck(returnType);
    }

    @Override
    public void typeCheck(ClassName returnType) throws TypeCheckException {
        params.typeCheck();
        procedure.typeCheck(this.returnType);
    }

    public String getDescriptor() {
        String result = params.getDescriptor();
        result += returnType == null ? "V" : returnType.getDescriptor();
        return result;
    }

    public void generate(ClassWriter target) {
        String desc = getDescriptor();

        MethodVisitor method = target.visitMethod(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                getName(),
                desc,
                null,
                null
        );
        method.visitCode();

        procedure.generate(method);

        method.visitInsn(Opcodes.RETURN);

        method.visitMaxs(-1, -1);
        method.visitEnd();
    }
}
