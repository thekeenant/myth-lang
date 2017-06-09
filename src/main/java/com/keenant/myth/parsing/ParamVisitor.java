package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ParamListContext;
import com.keenant.myth.MythParser.ParamsContext;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.stmt.DeclareStmt;

import java.util.ArrayList;
import java.util.List;

public class ParamVisitor extends MythBaseVisitor<Params> {
    private final Scope scope;

    public ParamVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Params visitParams(ParamsContext ctx) {
        List<DeclareStmt> result = new ArrayList<>();
        if (ctx.paramList() != null) {
            buildList(result, ctx.paramList());
        }
        return new Params(ctx, scope, result);
    }

    @Override
    public Params visitParamList(ParamListContext ctx) {
        List<DeclareStmt> result = new ArrayList<>();
        buildList(result, ctx);



        return new Params(ctx, scope, result);
    }

    private void buildList(List<DeclareStmt> list, ParamListContext ctx) {
        DeclVisitor visitor = new DeclVisitor(scope);

        if (ctx.decl() != null)
            list.add(ctx.decl().accept(visitor));
        else if (ctx.declAssign() != null)
            list.add(ctx.declAssign().accept(visitor));

        if (ctx.paramList() != null)
            buildList(list, ctx.paramList());
    }
}
