package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.DeclAssignContext;
import com.keenant.myth.MythParser.DeclContext;
import com.keenant.myth.MythParser.FuncDefContext;
import com.keenant.myth.codegen.ClassName;
import com.keenant.myth.codegen.Scope;
import com.keenant.myth.lang.Params;
import com.keenant.myth.lang.Procedure;
import com.keenant.myth.lang.VarMode;
import com.keenant.myth.lang.expr.Expr;
import com.keenant.myth.lang.stmt.DeclareAssignStmt;
import com.keenant.myth.lang.stmt.DeclareStmt;
import com.keenant.myth.lang.stmt.FunctionDefinition;

public class DeclVisitor extends MythBaseVisitor<DeclareStmt> {
    private final Scope scope;

    public DeclVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public DeclareStmt visitDecl(DeclContext ctx) {
        VarMode mode = ctx.varMode().accept(new TypeVisitor());
        String name = ctx.IDENT().getSymbol().getText();
        ClassName type = null;
        if (ctx.className() != null) {
            type = ctx.className().accept(new ClassNameVisitor());
        }
        return new DeclareStmt(ctx, scope, mode, name, type);
    }

    @Override
    public DeclareStmt visitDeclAssign(DeclAssignContext ctx) {
        DeclareStmt decl = ctx.decl().accept(this);

        if (ctx.expr() != null) {
            Expr expr = ctx.expr().accept(new ExprVisitor(scope));
            return new DeclareAssignStmt(ctx, scope, decl, expr);
        }
        else if (ctx.funcDef() != null) {
            FuncDefContext def = ctx.funcDef();

            Scope nested = new Scope(scope);

            Params params = def.params().accept(new ParamVisitor(nested));
            Procedure procedure = def.procedure().accept(new ProcedureVisitor(nested));
            ClassName returnType = def.className() == null ? null : def.className().accept(new ClassNameVisitor());

            return new FunctionDefinition(ctx, scope, decl, params, procedure, returnType);
        }

        throw new IllegalStateException("invalid declaration");
    }
}
