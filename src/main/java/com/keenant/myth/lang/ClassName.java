package com.keenant.myth.lang;

public class ClassName {
    private final String name;

    public ClassName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ClassName)
            return ((ClassName) obj).name.equals(name);
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
