package com.keenant.myth.parsing;

import com.keenant.myth.MythLexer;
import com.keenant.myth.MythParser;
import com.keenant.myth.lang.Program;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class Parser {
    private CharStream stream;

    public Parser(CharStream stream) {
        this.stream = stream;
    }

    public Program parse() throws Exception {
        MythLexer lexer = new MythLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        MythParser parser = new MythParser(tokens);

        return parser.program().accept(new ProgramVisitor());
    }
}
