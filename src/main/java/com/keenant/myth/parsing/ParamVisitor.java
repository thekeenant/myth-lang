package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ParamListContext;
import com.keenant.myth.MythParser.ParamsContext;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.stmt.Decl;

import java.util.ArrayList;
import java.util.List;

public class ParamVisitor extends MythBaseVisitor<Params> {
    @Override
    public Params visitParams(ParamsContext ctx) {
        List<Decl> result = new ArrayList<>();
        if (ctx.paramList() != null) {
            buildList(result, ctx.paramList());
        }
        return new Params(ctx, result);
    }

    @Override
    public Params visitParamList(ParamListContext ctx) {
        List<Decl> result = new ArrayList<>();
        buildList(result, ctx);
        return new Params(ctx, result);
    }

    private void buildList(List<Decl> list, ParamListContext ctx) {
        DeclVisitor visitor = new DeclVisitor();

        if (ctx.decl() != null)
            list.add(ctx.decl().accept(visitor));
        else if (ctx.declAssign() != null)
            list.add(ctx.declAssign().accept(visitor));

        if (ctx.paramList() != null)
            buildList(list, ctx.paramList());
    }
}
