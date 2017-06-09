package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ClassNameContext;
import com.keenant.myth.codegen.ClassName;

public class ClassNameVisitor extends MythBaseVisitor<ClassName> {
    @Override
    public ClassName visitClassName(ClassNameContext ctx) {
        String name = ctx.IDENT().get(0).getText();
        for (int i = 1; i < ctx.IDENT().size(); i++) {
            name += "." + ctx.IDENT(i).getText();
        }
        // Todo: ClassList
        return new ClassName(name);
    }
}
