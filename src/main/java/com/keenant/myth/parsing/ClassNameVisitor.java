package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ClassNameContext;
import com.keenant.myth.lang.ClassName;

public class ClassNameVisitor extends MythBaseVisitor<ClassName> {
    @Override
    public ClassName visitClassName(ClassNameContext ctx) {
        String name = ctx.IDENT().getSymbol().getText();
        // Todo: ClassList
        return new ClassName(name);
    }
}
