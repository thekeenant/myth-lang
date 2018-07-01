lexer grammar Terminals;

// Primitives
BOOL        : 'bool';
CHAR        : 'char';
BYTE        : 'byte';
SHORT       : 'short';
INT         : 'int';
LONG        : 'long';
FLOAT       : 'float';
DOUBLE      : 'double';

// Keywords
TRUE        : 'true';
FALSE       : 'false';
VAR         : 'var';
DEF         : 'def';
FINAL       : 'final';
RETURN      : 'return';
CLASS       : 'class';
STATIC      : 'static';
IMPORT      : 'import';

// Symbols
PRIV        : '_';
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
BRACKETS    : '[]';


// Identifiers start with a character, may have digits

IDENT       : [a-zA-Z][_a-zA-Z0-9]*;

// Basics
DIGIT       : [0-9]+;
COMMENT     : '//'~[\r\n]* -> skip;
BLOCK_COM   : '/*'.*?'*/' -> skip;
WS          : [ \t\r\n]+ -> channel(HIDDEN);
