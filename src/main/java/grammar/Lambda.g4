grammar Lambda;

@header {
package grammar;
import lambda.*;
}

let_expr returns [Expression expr] : LET VAR EQUALS expr1=let_expr IN expr2=let_expr
                                   { $expr = new Let(new Variable($VAR.text), $expr1.expr, $expr2.expr); }
                                   | expr3=expression { $expr = $expr3.expr; }
                                   ;


expression returns [Expression expr] : app2=application { $expr = $app2.expr; }
                                     | abst2=abstraction { $expr = $abst2.expr; }
                                     | app1=application abst1=abstraction { $expr = new Application($app1.expr, $abst1.expr); }
                                     ;

application returns[Expression expr] : app=application atom1=atomic { $expr = new Application($app.expr, $atom1.expr); }
                                     | atom2=atomic { $expr = $atom2.expr; }
                                     ;


atomic returns[Expression expr] : OB exp=expression CB { $expr = $exp.expr; }
                                | VAR { $expr = new Variable($VAR.text); }
                                ;

abstraction returns[Expression expr] : LAMBDA VAR DOT exp=expression { $expr = new Abstraction(new Variable($VAR.text), $exp.expr); }
                                     ;

LET : 'let';
IN : 'in';
EQUALS : '=';
OB : '(';
CB : ')';
LAMBDA : '\\';
DOT : '.';
VAR :  [a-z][a-z0-9\\']*;
WS : (' ' | '\t' | '\r' '\n')+ -> skip ;
