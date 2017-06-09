package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ProgramContext;
import com.keenant.myth.lang.ClassDefinition;
import com.keenant.myth.lang.Program;

public class ProgramVisitor extends MythBaseVisitor<Program> {
    @Override
    public Program visitProgram(ProgramContext ctx) {
        ClassDefinition def = ctx.classDef().accept(new ClassDefVisitor());
        return new Program(ctx, def);
    }
}
