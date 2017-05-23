package com.keenant.myth.lang;

import com.keenant.myth.exception.ScopeException;
import com.keenant.myth.lang.stmt.Decl;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
public class Scope {
    private final Scope parent;
    private final Metadata metadata;

    private final List<Decl> decls = new ArrayList<>();

    public Scope(Scope parent) {
        this.parent = parent;
        metadata = null;
    }

    public Scope(Metadata metadata) {
        this.metadata = metadata;
        parent = null;
    }

    public Optional<Decl> findDecl(String name) {
        return decls.stream().filter(decl -> decl.getName().equals(name)).findAny();
    }

    public Optional<Decl> findDeclGlobal(String name) {
        Optional<Decl> local = findDecl(name);
        if (local.isPresent())
            return local;
        else if (parent != null)
            return parent.findDeclGlobal(name);
        return Optional.empty();
    }

    public void addDecl(Decl decl) throws ScopeException {
        if (findDecl(decl.getName()).isPresent()) {
            throw new ScopeException("decl with that name already exists");
        }
        decls.add(decl);
    }

    public Metadata getMetadata() {
        if (metadata != null)
            return metadata;
        if (parent != null)
            return parent.getMetadata();
        throw new IllegalStateException("scope has no metadata/parent metadata");
    }
}
