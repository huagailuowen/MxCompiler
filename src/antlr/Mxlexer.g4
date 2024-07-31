lexer grammar Mxlexer;


//reserved word

Void : 'void';
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

//elements
SmallLetter : [a-z];
CapitalLetter : [A-Z];
Number : [0-9];
Interger : [1-9] [0-9]* | '0';
Identifier : (SmallLetter | CapitalLetter )(SmallLetter | CapitalLetter | Number| '_' )*;

ConstString : '"' ( ~["\r\n\\] | '\\"' | '\\n' | '\\\\' )* '"';
F_PREFIX : 'f'; 
QUOTE : '"'; 
FormatChar : DoubleLeftBrace
    | DoubleRightBrace
		| ~[{}];
//this is the normal string, without format string
ConstArray : '{' (ConstElement)? (',' ConstElement)* '}';

ConstElement : Interger | Identifier | ConstString| ConstArray | True | False | This;

Comment : '//' ~[\r\n]* -> skip;
BlockComment : '/*' .*? '*/' -> skip;
Whitespace : [ \t\r\n]+ -> skip;

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
AddAssign : '+=';
SubAssign : '-=';
MulAssign : '*=';
DivAssign : '/=';
ModAssign : '%=';
LeftMoveAssign : '<<=';
RightMoveAssign : '>>=';
AndAssign : '&=';
OrAssign : '|=';
XorAssign : '^=';
AssignType :Assign|AddAssign|SubAssign|MulAssign|DivAssign|ModAssign|LeftMoveAssign|RightMoveAssign|AndAssign|OrAssign|XorAssign;

SelfPlus : '++';
SelfMinus : '--';

Member : '.';
LeftBracket : '(';
RightBracket : ')';
LeftSquareBracket : '[';
RightSquareBracket : ']';
LeftBrace : '{';
RightBrace : '}';
Comma : ',';
Semicolon : ';';
Colon : ':';
QestionMark : '?';
DoubleLeftBrace : '{{';
DoubleRightBrace : '}}';






