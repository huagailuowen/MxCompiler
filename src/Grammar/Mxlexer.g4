lexer grammar Mxlexer;


//reserved word

Void : 'void';
Null : 'null';
Bool : 'bool';
Int : 'int';
String : 'string';
New : 'new';
Class : 'class';
True : 'true';
False : 'false';
This : 'this';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';


// DoubleLeftBrace : '{{';
// DoubleRightBrace : '}}';

//operators

Plus : '+';
Minus : '-';
Multiply : '*';
Divide : '/';
Mod : '%';

Greater : '>';
Less : '<';
GreaterEqual : '>=';
LessEqual : '<=';
Equal : '==';
InEqual : '!=';

And : '&&';
Or : '||';
Not : '!';

LeftMove : '<<';
RightMove : '>>';
AndBit : '&';
OrBit : '|';
XorBit : '^';
NotBit : '~';

Assign : '=';


SelfPlus : '++';
SelfMinus : '--';

Member : '.';
// LeftBracket : '(';
// RightBracket : ')';
// LeftSquareBracket : '[';
// RightSquareBracket : ']';
// LeftBrace : '{';
// RightBrace : '}';
Comma : ',';
Semicolon : ';';

Comment : '//' ~[\r\n]* -> skip;
BlockComment : '/*' .*? '*/' -> skip;
Whitespace : [ \t\r\n]+ -> skip;

//elements
Interger : [1-9] [0-9]* | '0';
Identifier : ([a-zA-Z] )([a-zA-Z0-9_])*;
fragment SringChar: ~["\r\n\\] | '\\"' | '\\n' |'\\r' | '\\\\' ;
fragment FormatStringChar : '$$'| ~[$\n\\\r"] | '\\"' | '\\n'|'\\r' | '\\\\';
ConstString : '"' ( ~["\r\n\\] | '\\"' | '\\n' |'\\r' | '\\\\' )* '"';
FormatStringI: 
	'f"' (FormatStringChar)* '"';

FormatStringL: 
	'f"' (FormatStringChar)* '$';
FormatStringM: 
	'$' (FormatStringChar)* '$';
FormatStringR: 
	'$' (FormatStringChar)* '"';
// FormatChar : DoubleLeftBrace
//     | DoubleRightBrace
// 		| ~[{}];
//this is the normal string, without format string

// f" { f"{ }  }"