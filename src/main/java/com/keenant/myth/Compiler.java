package com.keenant.myth;

import com.keenant.myth.lang.Program;
import com.keenant.myth.parsing.Parser;
import org.objectweb.asm.ClassWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map.Entry;

public class Compiler {
    private final InputStream source;
    private final File targetDirectory;

    public Compiler(InputStream source, File targetDirectory) {
        this.source = source;
        this.targetDirectory = targetDirectory;
    }

    public void compile() throws Exception {
        CharStream stream = CharStreams.fromStream(source);

        Parser parser = new Parser(stream);

        Program program = parser.parse();

        System.out.println("Parsing...");
        System.out.println(program);


        System.out.println("Type checking...");
        program.typeCheck();

        System.out.println("Generating...");
        program.generate();


        System.out.println(program.getScope());

        for (Entry<String, ClassWriter> entry : program.getScope().getTargets().entrySet()) {
            File targetFile = new File(targetDirectory, entry.getKey() + ".class");
            OutputStream out = new FileOutputStream(targetFile);
            out.write(entry.getValue().toByteArray());
            out.flush();
            out.close();
        }
    }
}
