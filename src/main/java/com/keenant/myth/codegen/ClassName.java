package com.keenant.myth.codegen;

public class ClassName {
    public static final ClassName OBJECT = new ClassName("java.lang.Object", null);

    private final String name;
    private final ClassName parent;

    public ClassName(String name) {
        this(name, OBJECT);
    }

    public ClassName(String name, ClassName parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getInternalName() {
        return name.replace(".", "/");
    }

    public ClassName getParent() {
        return parent;
    }

    public String getDescriptor() {
        return "L" + getInternalName() + ";";
    }

    public boolean isSubTypeOf(ClassName other) {
        if (other == null)
            return false;

        if (name.equals(other.name)) {
            return true;
        }

        if (parent != null)
            return parent.isSubTypeOf(other);

        return false;
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
