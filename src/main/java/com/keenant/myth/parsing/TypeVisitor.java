package com.keenant.myth.parsing;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.VarModeContext;
import com.keenant.myth.lang.VarMode;

public class TypeVisitor extends MythBaseVisitor<VarMode> {
    @Override
    public VarMode visitVarMode(VarModeContext ctx) {
        if (ctx.VAL() != null)
            return VarMode.VAL;
        else
            return VarMode.VAR;
    }
}
