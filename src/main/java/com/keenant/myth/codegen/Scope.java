package com.keenant.myth.codegen;

import com.keenant.myth.exception.ScopeException;
import com.keenant.myth.lang.stmt.DeclareStmt;
import lombok.ToString;
import org.objectweb.asm.ClassWriter;

import java.util.*;

@ToString(exclude = "parent")
public class Scope {
    private final Scope parent;
    private final ClassName name;
    private final List<Scope> children = new ArrayList<>();

    private final List<DeclareStmt> decls = new ArrayList<>();
    private final Map<String, ClassWriter> targets = new HashMap<>();

    public Scope(Scope parent) {
        this.parent = parent;
        name = null;

        parent.addChild(this);
    }

    public Scope(ClassName name) {
        parent = null;
        this.name = name;
    }

    public Map<String, ClassWriter> getTargets() {
        return targets;
    }

    public void addChild(Scope scope) {
        children.add(scope);
    }

    public ClassName getClassName() {
        Scope scope = this;
        while (scope != null && scope.name == null) {
            scope = scope.parent;
        }
        if (scope == null)
            throw new IllegalStateException("bad scope");
        return scope.name;
    }

    public ClassWriter createTarget(String name) {
        String targetName = name;
        int i = 0;
        while (targets.containsKey(targetName)) {
            targetName = name + "$" + i;
            i++;
        }
        ClassWriter target = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        targets.put(name, target);
        return target;
    }

    public String getTargetName(ClassWriter target) {
        for (String name : targets.keySet()) {
            if (targets.get(name).equals(target)) {
                return name;
            }
        }
        throw new IllegalArgumentException("target not found");
    }

    public Optional<DeclareStmt> findDecl(String name) {
        return decls.stream().filter(decl -> decl.getName().equals(name)).findAny();
    }

    public Optional<DeclareStmt> findDeclGlobal(String name) {
        Optional<DeclareStmt> local = findDecl(name);
        if (local.isPresent())
            return local;
        else if (parent != null)
            return parent.findDeclGlobal(name);
        return Optional.empty();
    }

    public void addDecl(DeclareStmt decl) throws ScopeException {
        if (findDecl(decl.getName()).isPresent()) {
            throw new ScopeException("decl with that name already exists");
        }
        decls.add(decl);
    }
}
