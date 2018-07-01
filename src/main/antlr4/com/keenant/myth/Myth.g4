grammar Myth;
import Literals, Terminals;

compilationUnit
  : importDeclaration* classDeclaration EOF
  ;

importDeclaration
  : IMPORT qualifiedName
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
  : '{' blockStatement* '}'
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
  ;

expression
  : expression bop=('.' | '::') (IDENT | methodCall)
  | methodCall
  | literal
  | IDENT
  | <assoc=right> expression
    bop=('=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '>>=' | '>>>=' | '<<=' | '%=')
    expression
  ;

methodCall
  : IDENT '(' expressionList? ')'
  ;

expressionList
  : expression (',' expression)*
  ;