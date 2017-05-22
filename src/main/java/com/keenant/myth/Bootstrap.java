package com.keenant.myth;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Bootstrap {
    public static void main(String[] args) {
        String source = args[0];
        String target = args[1];

        new Compiler(source, target).run();
    }
}
