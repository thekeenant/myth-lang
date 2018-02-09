grammar Myth;

//
// Non-Terminals
//

program     : classDef EOF
            ;

procedure   : (stmt SEMI)*
            ;

stmt        : typedDecl     # StmtDecl
            | declAssign    # StmtDecl
            | expr          # StmtExpr
            | RETURN expr   # StmtReturn
            | RETURN        # StmtReturn
            ;

declAssign  : decl EQUAL expr
            | decl EQUAL funcDef
            ;

typedDecl   : decl COLON className
            ;

decl        : varMode IDENT
            ;

className   : IDENT ('.' IDENT)*
            | IDENT LANGLE classList RANGLE
            ;

classList   : className
            | className ',' classList
            ;

expr        : MINUS expr                    # ExprMinus
            | expr (TIMES | DIVIDE) expr    # ExprArith
            | expr (PLUS | MINUS) expr      # ExprArith
            | LPAREN expr RPAREN            # ExprParen
            | term                          # ExprTerm
            | ref EQUAL expr                # ExprAssign
            | arrayDef                      # ExprArrayDef
            | expr IS IDENT                 # ExprIsInstance
            ;

term        : LIT_INT
            | TRUE
            | FALSE
            | ref
            | funcCall
            | newObject
            ;

newObject   : NEW funcCall
            ;

ref         : IDENT
            ;

exprList    : expr
            | expr COMMA exprList
            ;

funcCall    : IDENT args
            ;

args        : LPAREN RPAREN
            | LPAREN exprList RPAREN
            ;

arrayDef    : LANGLE className RANGLE LSQUARE exprList RSQUARE
            | LANGLE className RANGLE LSQUARE RSQUARE
            ;

funcDef     : params COLON className LCURLY procedure RCURLY
            | params LCURLY procedure RCURLY
            | params COLON className ARROW expr
            | params ARROW expr
            | params
            | params COLON className
            ;

params      : LPAREN paramList RPAREN
            | LPAREN RPAREN
            ;

paramList   : typedDecl
            | typedDecl COMMA paramList
            ;

classDef    : CLASS IDENT LCURLY classDecls RCURLY
            | CLASS IDENT COLON classList LCURLY classDecls RCURLY
            | CLASS IDENT LCURLY RCURLY
            | CLASS IDENT COLON classList LCURLY RCURLY
            ;

classDecls  : (classDecl SEMI)*
            ;

classDecl   : decl
            | declAssign
            ;

varMode     : VAR
            | VAL
            ;


//
// Terminals
//

// Literals Todo: string literal
LIT_STR     : '"' .* '"' -> skip;
LIT_INT     : (DIGIT)+;
TRUE        : 'true';
FALSE       : 'false';

// Keywords
NEW         : 'new';
VAR         : 'var';
VAL         : 'val';
RETURN      : 'return';
CLASS       : 'class';
IS          : 'is';

// Symbols
PRIV       : '_';
ARROW       : '->';
LSQUARE     : '[';
RSQUARE     : ']';
COMMA       : ',';
LANGLE      : '<';
RANGLE      : '>';
LCURLY      : '{';
RCURLY      : '}';
LPAREN      : '(';
RPAREN      : ')';
COLON       : ':';
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
