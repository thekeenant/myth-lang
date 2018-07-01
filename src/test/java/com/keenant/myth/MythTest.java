package com.keenant.myth;

import com.keenant.myth.lang.CompilationUnit;
import com.keenant.myth.parser.CompilationUnitVisitor;
import java.io.FileOutputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class MythTest {

  @Test
  public void compile() throws Exception {
    CharStream in = CharStreams.fromStream(MythTest.class.getResourceAsStream("/first.myth"));
    FileOutputStream out = new FileOutputStream("First.class");

    MythLexer lexer = new MythLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MythParser parser = new MythParser(tokens);

    CompilationUnit unit = parser.compilationUnit().accept(new CompilationUnitVisitor());

    unit.analyze();
    byte[] output = unit.codegen();

    System.out.println(unit);

    out.write(output);
    out.flush();
    out.close();
  }
}
