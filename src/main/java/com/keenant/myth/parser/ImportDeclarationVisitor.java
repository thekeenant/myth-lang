package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.ImportDeclarationContext;
import com.keenant.myth.lang.ImportDeclaration;

public class ImportDeclarationVisitor extends MythBaseVisitor<ImportDeclaration> {
  @Override
  public ImportDeclaration visitImportDeclaration(ImportDeclarationContext ctx) {
    String name = ctx.qualifiedName().getText();
    // hacky
    if (ctx.wildcard != null) {
      name += ".*";
    }
    return new ImportDeclaration(name);
  }
}
