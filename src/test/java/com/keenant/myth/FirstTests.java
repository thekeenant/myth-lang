package com.keenant.myth;

import org.junit.Test;

public class FirstTests {
    @Test
    public void firstTest() throws Exception {
        Compiler compiler = new Compiler(getClass().getResourceAsStream("/first.myth"), null);
        compiler.compile();
    }
}
