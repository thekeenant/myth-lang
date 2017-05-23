package com.keenant.myth.lang;

import lombok.ToString;

@ToString
public class Metadata {
    private final String className;
    private final String superClassName;

    public Metadata(String className, String superClassName) {
        this.className = className;
        this.superClassName = superClassName;
    }

    public String getClassName() {
        return className;
    }

    public String getSuperClassName() {
        return superClassName;
    }
}
