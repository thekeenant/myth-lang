grammar Myth;

//
// Non-Terminals
//

program     : procedure EOF
            ;

procedure   : (stmt SEMI)*
            ;

stmt        : decl          # StmtDecl
            | declAssign    # StmtDecl
            | expr          # StmtExpr
            | RETURN expr   # StmtReturnExpr
            | RETURN        # StmtReturn
            ;

declAssign  : decl EQUAL expr
            ;

decl        : varMode IDENT ':' className
            | varMode IDENT
            ;

className   : IDENT
            | IDENT '<' classList '>'
            ;

classList   : className
            | className ',' classList
            ;

expr        : MINUS expr                    # ExprMinus
            | expr (TIMES | DIVIDE) expr    # ExprArith
            | expr (PLUS | MINUS) expr      # ExprArith
            | '(' expr ')'                  # ExprParen
            | term                          # ExprTerm
            | ref EQUAL expr                # ExprAssign
            | funcDefn                      # ExprFuncDefn
            | classDefn                     # ExprClassDefn
            ;

term        : LIT_INT
            | TRUE
            | FALSE
            | ref
            | funcCall
            ;

ref         : IDENT
            ;

exprList    : expr
            | expr ',' exprList
            ;

funcCall    : IDENT args
            ;

args        : '()'
            | '(' exprList ')'
            ;

funcDefn    : params ':' className '{' procedure '}'
            | ':' className '{' procedure '}'
            ;

params      : '(' paramList ')'
            | '()'
            ;

paramList   : decl
            | declAssign
            | decl ',' paramList
            | declAssign ',' paramList
            ;

classDefn   : CLASS '{' classDecls '}'
            | CLASS ':' classList '{' classDecls '}'
            ;

classDecl   : declAssign SEMI
            | decl SEMI
            ;

classDecls  : classDecl classDecls
            | classDecl
            ;

varMode     : VAR
            | VAL
            ;


//
// Terminals
//

// Literals
LIT_INT     : (DIGIT)+;
TRUE        : 'true';
FALSE       : 'false';
CLASS       : 'class';

// Keywords
VAR         : 'var';
VAL         : 'val';
RETURN      : 'ret';

// Symbols
EQUAL       : '=';
PLUS        : '+';
MINUS       : '-';
TIMES       : '*';
DIVIDE      : '/';


// Identifiers start with a character, may have digits

IDENT       : [a-zA-Z][_a-zA-Z0-9]*;
SEMI        : ';';

// Basics
DIGIT       : [0-9]+;
COMMENT     : '//'~[\r\n]* -> skip;
BLOCK_COM   : '/*'.*'*/' -> skip;
WS          : [ \t\r\n]+ -> channel(HIDDEN);
