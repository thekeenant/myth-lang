package com.keenant.myth;

import org.junit.Test;

import java.io.File;

public class FirstTests {
    @Test
    public void firstTest() throws Exception {
        Compiler compiler = new Compiler(
                getClass().getResourceAsStream("/first.myth"),
                new File("/tmp/myth")
        );
        compiler.compile();


    }
}
