grammar Mxparser;
import Mxlexer;
// program
//int[][] a={2,4,{6,7,0},{8} };
// int a=f"{a+b}hello{c+d}world{e+f+f"eqw{a}we"}}}";
program : (classDeclaration | functionDeclaration | variableDeclaration )* EOF;
classDeclaration : Class Identifier '{'
	(variableDeclaration
	|functionDeclaration
	|classConstructor)*
	'}';

functionDeclaration : type Identifier '(' parameterList? ')' block;
parameterList : parameter (Comma parameter)*;
parameter : type atomVariableDeclaration;
variableDeclaration : type atomVariableDeclaration (Comma atomVariableDeclaration)*  Semicolon;
atomVariableDeclaration : atom (Assign expression)?;

classConstructor : Identifier '(' ')' block;

statement : 
	block
	|ifStatement
	|forStatement
	|whileStatement
	|returnStatement
	|breakStatement
	|continueStatement
	|expressionStatement
	|variableDeclaration
	| Semicolon;
block : ('{' statement* '}') ;
ifStatement : 
	If '(' expression ')' (statement) 
	(Else (statement))?;
forStatement :
	For '(' (initalstatement Semicolon) expression?  Semicolon expression? ')' statement;
whileStatement :
	While '(' expression ')' statement;
returnStatement : Return expression?  Semicolon;
breakStatement : Break  Semicolon;
continueStatement : Continue  Semicolon;
expressionStatement : expression  Semicolon;
initalstatement : type? expression;

type : (Int | Bool | String | Identifier | Void) arrayLable*;
arrayLable : ('['expression?']');
// formatStringexpression 
// 	: 'f"'  ( FormatChar | '{' expression '}'  )* '"'	;

formatStringElement: 
	FormatStringI
	|(FormatStringL expression (FormatStringM expression)* FormatStringR);


expression : 
	'('expression')'
	New type ('[' (expression)? ']')* atom?
	|expression Member Identifier 
	|expression(SelfPlus|SelfMinus)
	|<assoc = right> (SelfPlus|SelfMinus|Not|Minus|NotBit) expression
	|expression (Multiply|Divide|Mod) expression
	|expression (Plus|Minus) expression
	|expression (LeftMove|RightMove) expression
	|expression (Greater|Less|GreaterEqual|LessEqual) expression
	|expression (Equal|InEqual) expression
	|expression (AndBit) expression
	|expression (XorBit) expression
	|expression (OrBit) expression
	|expression And expression
	|expression Or expression 
	|<assoc = right> QestionMark expression Colon expression
	|<assoc = right> expression (Assign) expression
	|atom;

	

atom :
	array
	|Identifier
	|constElement
	|formatStringElement;
array:
	Identifier ('[' expression? ']')+;
constArray : '{' (constElement)? (',' constElement)* '}';

constElement : Interger | Identifier | ConstString| constArray | True | False | This;
