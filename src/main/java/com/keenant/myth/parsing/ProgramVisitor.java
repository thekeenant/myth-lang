package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ProcedureContext;
import com.keenant.myth.MythParser.ProgramContext;
import com.keenant.myth.lang.Metadata;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.Program;
import com.keenant.myth.lang.Scope;

public class ProgramVisitor extends MythBaseVisitor<Program> {
    @Override
    public Program visitProgram(ProgramContext ctx) {
        Procedure procedure = ctx.procedure().accept(new ProcedureVisitor());

        return new Program(ctx, procedure);
    }
}
