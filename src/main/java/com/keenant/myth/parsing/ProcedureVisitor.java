package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ProcedureContext;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.Scope;
import com.keenant.myth.lang.Stmt;

import java.util.List;
import java.util.stream.Collectors;

public class ProcedureVisitor extends MythBaseVisitor<Procedure> {
    @Override
    public Procedure visitProcedure(ProcedureContext ctx) {
        List<Stmt> statements = ctx.stmt().stream().map((stmtContext) -> {
            return stmtContext.accept(new StmtVisitor());
        }).collect(Collectors.toList());
        return new Procedure(ctx, statements);
    }
}
