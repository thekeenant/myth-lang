package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ClassDeclContext;
import com.keenant.myth.MythParser.ClassDefContext;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.ClassDefinition;
import com.keenant.myth.lang.stmt.DeclareStmt;

import java.util.ArrayList;
import java.util.List;

public class ClassDefVisitor extends MythBaseVisitor<ClassDefinition> {
    @Override
    public ClassDefinition visitClassDef(ClassDefContext ctx) {
        String nameText = ctx.IDENT().getSymbol().getText();
        ClassName name = new ClassName(nameText, ClassName.OBJECT);

        Scope scope = new Scope(name);

        List<DeclareStmt> decls = new ArrayList<>();

        DeclVisitor visitor = new DeclVisitor(scope);

        for (ClassDeclContext classDecl : ctx.classDecls().classDecl()) {
            DeclareStmt decl = classDecl.accept(visitor);
            decls.add(decl);
        }

        return new ClassDefinition(ctx, scope, decls);
    }
}
