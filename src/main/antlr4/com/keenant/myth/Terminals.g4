lexer grammar Terminals;

// Primitives
BOOL        : 'boolean';
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

ARROW       : '->';
BRACKETS    : '[]';

// Identifiers start with a character, may have digits
IDENT       : [a-zA-Z][_a-zA-Z0-9]*;

// Basics
DIGIT       : [0-9];
STRING      : '"' ('\\"' | ~('"'))* '"';
COMMENT     : '//' ~[\r\n]* -> skip;
WS          : [ \t\r\n] -> channel(HIDDEN);