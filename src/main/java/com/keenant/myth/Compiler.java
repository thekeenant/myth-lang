package com.keenant.myth;

public class Compiler implements Runnable {
    private final String source;
    private final String target;

    public Compiler(String source, String target) {
        this.source = source;
        this.target = target;

    }

    @Override
    public void run() {
//        CharStream stream = CharStreams.fromFileName(source);
    }
}
