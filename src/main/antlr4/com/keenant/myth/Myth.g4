grammar Myth;
import Terminals;

compilationUnit
  : importDeclaration* classDeclaration EOF
  ;

importDeclaration
  : IMPORT qualifiedName ('.' wildcard='*')?
  ;

qualifiedName
  : IDENT ('.' IDENT)*
  ;

classDeclaration
  : CLASS IDENT '{' memberDeclaration* '}'
  ;

memberDeclaration
  : STATIC? methodDeclaration
  ;

methodDeclaration
  : DEF IDENT parameters (ARROW type)? block
  ;

parameters
  : '(' parameterList? ')'
  ;

parameterList
  : parameter (',' parameter)*
  ;

parameter
  : IDENT ':' type
  ;

type
  : (primitiveType | classType) (BRACKETS)*
  ;

primitiveType
  : BOOL
  | CHAR
  | BYTE
  | SHORT
  | INT
  | LONG
  | FLOAT
  | DOUBLE
  ;

classType
  : IDENT ('.' IDENT)*
  ;

block
  : '{' (blockStatement ';'?)* '}'
  ;

blockStatement
  : localVariableDeclaration
  | statement
  ;

localVariableDeclaration
  : VAR IDENT ':' type ('=' expression)?
  | VAR IDENT '=' expression
  ;

statement
  : statementExpression=expression
  | codeBlock=block
  | ifStatement='if' '(' expression ')' thenStatement=statement ('else' elseStatement=statement)?
  ;

expression
  : expression bop=('.' | '::') (IDENT | methodCall)
  | methodCall
  | constructorCall
  | literal
  | IDENT
  | <assoc=right> expression
    bop=('=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '>>=' | '>>>=' | '<<=' | '%=')
    expression
  ;

methodCall
  : IDENT '(' expressionList? ')'
  ;

constructorCall
  : 'new' classType '(' expressionList? ')'
  ;

expressionList
  : expression (',' expression)*
  ;


literal
  : booleanLiteral
  | integerLiteral
  | doubleLiteral
  | floatLiteral
  | stringLiteral
  ;

integerLiteral
  : DIGIT+
  ;

doubleLiteral
  : before=DIGIT* '.' after=DIGIT+
  ;

floatLiteral
  : before=DIGIT* ('.' after=DIGIT+)? ('f' | 'F')
  ;

booleanLiteral
  : ('true' | 'false')
  ;

stringLiteral
  : STRING
  ;