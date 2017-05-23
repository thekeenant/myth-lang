package com.keenant.myth;

import com.keenant.myth.lang.Program;
import com.keenant.myth.parsing.Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.InputStream;
import java.io.OutputStream;

public class Compiler {
    private final InputStream source;
    private final OutputStream target;

    public Compiler(InputStream source, OutputStream target) {
        this.source = source;
        this.target = target;
    }

    public void compile() throws Exception {
        CharStream stream = CharStreams.fromStream(source);

        Parser parser = new Parser(stream);

        Program program = parser.parse();

        System.out.println("Parsed program:");
        System.out.println(program);

        program.typeCheck();
    }
}
