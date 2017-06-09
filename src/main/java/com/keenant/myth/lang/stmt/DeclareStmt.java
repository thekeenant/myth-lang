package com.keenant.myth.lang.stmt;

import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.exception.BadDeclarationException;
import com.keenant.myth.exception.ScopeException;
import com.keenant.myth.exception.TypeCheckException;
import com.keenant.myth.lang.Stmt;
import com.keenant.myth.lang.VarMode;
import org.objectweb.asm.MethodVisitor;
import lombok.ToString;
import org.antlr.v4.runtime.ParserRuleContext;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Optional;

@ToString
public class DeclareStmt extends Stmt {
    private final VarMode varMode;
    private final String name;
    private final ClassName declaredType;

    public DeclareStmt(ParserRuleContext context, Scope scope, VarMode varMode, String name, ClassName declaredType) {
        super(context, scope);
        this.varMode = varMode;
        this.name = name;
        this.declaredType = declaredType;
    }

    public VarMode getVarMode() {
        return varMode;
    }

    public String getName() {
        return name;
    }

    public Optional<ClassName> getDeclaredType() {
        return Optional.ofNullable(declaredType);
    }

    public ClassName resolveType() {
        return declaredType;
    }

    public void typeCheck() {
        if (resolveType() == null) {
            throw new BadDeclarationException(this, "type cannot be inferred");
        }

        try {
            getScope().addDecl(this);
        } catch (ScopeException e) {
            throw new BadDeclarationException(this, "name already in scope");
        }
    }

    @Override
    public void typeCheck(ClassName returnType) throws TypeCheckException {
        typeCheck();
    }

    @Override
    public void generate(MethodVisitor method) {
        throw new UnsupportedOperationException("declare stmt not supported yet");
    }

    public void generate(ClassWriter target) {
        FieldVisitor field = target.visitField(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                name,
                resolveType().getDescriptor(),
                null,
                null
        );
        field.visitEnd();
    }
}
