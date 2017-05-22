grammar Myth;

//
// Non-Terminals
//

program     : procedure EOF
            ;

procedure   : (stmt)*
            ;

stmt        : decl SEMI
            | declAssign SEMI
            ;

declAssign  : decl EQUAL expr
            ;

decl        : type IDENT
            ;

expr        : LIT_INT
            | function
            ;

function    : params '{' procedure '}'
            | '{' procedure '}'
            ;

params      : '(' paramList ')'
            | '()'
            ;

paramList   : decl
            | decl ',' paramList
            ;

type        : VAR
            | VAL
            ;


//
// Terminals
//

// Literals
LIT_INT     : (DIGIT)+;

// Keywords
VAR         : 'var';
VAL         : 'val';

// Symbols
EQUAL       : '=';

// Identifiers start with a character, may have digits

IDENT       : [a-zA-Z][_a-zA-Z0-9]*;
SEMI        : ';';

// Basics
DIGIT       : [0-9]+;
WS          : [ \t\r\n]+ -> skip;