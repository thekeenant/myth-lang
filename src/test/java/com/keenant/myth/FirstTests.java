package com.keenant.myth;

import com.keenant.myth.MythParser.ProgramContext;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;

public class FirstTests {
    @Test
    public void firstTest() throws IOException {
        MythLexer lexer = new MythLexer(CharStreams.fromStream(getClass().getResourceAsStream("/first.myth")));
        TokenStream tokens = new CommonTokenStream(lexer);
        MythParser parser = new MythParser(tokens);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        ProgramContext prog = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();

        MythBaseListener listener = new MythBaseListener() {
            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                System.out.println(ctx.getText());
                ctx.removeLastChild();

            }
        };

        walker.walk(listener, prog);
    }
}
