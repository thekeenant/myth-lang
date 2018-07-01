grammar Literals;
import Terminals;

literal
  : integerLiteral
  ;

integerLiteral
  : DIGIT+
  ;