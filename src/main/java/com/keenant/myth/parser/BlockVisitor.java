package com.keenant.myth.parser;

import com.keenant.myth.MythBaseVisitor;
import com.keenant.myth.MythParser.BlockContext;
import com.keenant.myth.lang.Block;
import com.keenant.myth.lang.statement.BlockStatement;
import java.util.List;
import java.util.stream.Collectors;

public class BlockVisitor extends MythBaseVisitor<Block> {
  @Override
  public Block visitBlock(BlockContext ctx) {
    List<BlockStatement> statements = ctx.blockStatement().stream()
        .map(childCtx -> childCtx.accept(new BlockStatementVisitor()))
        .collect(Collectors.toList());

    return new Block(statements);
  }
}
