package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ProcedureContext;
import com.keenant.myth.MythParser.StmtContext;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.Stmt;

import java.util.ArrayList;
import java.util.List;

public class ProcedureVisitor extends MythBaseVisitor<Procedure> {
    private final Scope scope;

    public ProcedureVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Procedure visitProcedure(ProcedureContext ctx) {
        StmtVisitor visitor = new StmtVisitor(scope);

        List<Stmt> stmts = new ArrayList<>();
        for (StmtContext stmtContext : ctx.stmt()) {
            stmts.add(stmtContext.accept(visitor));
        }

        return new Procedure(ctx, scope, stmts);
    }
}
