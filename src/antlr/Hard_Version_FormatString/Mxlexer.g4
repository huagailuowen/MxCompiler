lexer grammar Mxlexer;


//reserved word
@header {
	import java.util.Stack;
}
@lexer::members {
	int l_brace = 0;
	int stack_count = 0;
	Stack<Integer> stack = new Stack<Integer>();
}
fragment FormatStringChar : {stack_count!=0&&l_brace == 0}?'{{'|'}}'| ~[{}\n\\\r"] | '\\"' | '\\n'|'\\r' | '\\\\';
FormatStringM: 
	{stack_count>0 && l_brace==0}? FormatStringChar+;
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
LeftBrace: '{'
{
	l_brace++;
};
RightBrace: '}'
{
	l_brace--;
};


Comment : '//' ~[\r\n]* -> skip;
BlockComment : '/*' .*? '*/' -> skip;
Whitespace : [ \t\r\n]+ -> skip;

//elements
Interger : [1-9] [0-9]* | '0';
Identifier : ([a-zA-Z] )([a-zA-Z0-9_])*;
fragment SringChar: ~["\r\n\\] | '\\"' | '\\n' |'\\r' | '\\\\' ;
ConstString : {stack_count==0||l_brace > 0}?('"' ( ~["\r\n\\] | '\\"' | '\\n' |'\\r' | '\\\\' )* '"');
FormatStringI: 
	'f"' FormatStringM? '"';

FormatStringL: 
	'f"' (FormatStringM)?
	{
		if(stack_count == 0){
			stack_count++;
			l_brace=0;
		}
		else{
			stack.push(l_brace);
			System.out.println("push "+l_brace);
			l_brace=0;
			stack_count++;
		}
	};
FormatStringR: 
	{stack_count!=0&&l_brace==0}?
	(FormatStringM)? '"'
	{
		if(stack_count == 1){
			stack_count--;
			l_brace = 0;
		}
		else{
			if(l_brace!=0){
				System.err.println("pop!"+l_brace);
			}
			if(stack.empty()){
				System.err.println("stack empty");
			}
			l_brace = stack.pop();
			stack_count--;
		}
	};
	
// FormatChar : DoubleLeftBrace
//     | DoubleRightBrace
// 		| ~[{}];
//this is the normal string, without format string
// f" { f"{ }  }"

//f"We are testing whether {f"{{1,1,4,5}}[2]=={{1,1,1}}[1]"}: 5634563456{f"{new int[]{1,1,4,5}[2]==new int[]{1,1,1}[1]}"}"

