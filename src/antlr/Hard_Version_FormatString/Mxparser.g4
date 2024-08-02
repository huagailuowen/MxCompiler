grammar Mxparser;
import Mxlexer;
// program
//int[][] a={2,4,{6,7,0},{8} };
//int a=f"{a+b}hello{c+d}world{e+f+f"eqw{a}we"}}}";
//int a=f"{f"{{}}" == "{}"}" == "true";
/*dfakldfj */
// a[0].f(0,0)[1]
//int a=f"{a+b}hello{c+d}world{e+f+f"eqw{a}we"}}}";//8798458934789435"""
program : (classDeclaration | functionDeclaration | variableDeclaration )* EOF;
classDeclaration : Class Identifier LeftBrace
	(variableDeclaration
	|functionDeclaration
	|classConstructor)*
	RightBrace Semicolon;

functionDeclaration : type Identifier '(' parameterDeclarationList? ')' block;
parameterDeclarationList : parameterDeclaration (',' parameterDeclaration)*;
parameterDeclaration : type atomVariableDeclaration;

parameterList : expression? (',' expression)*; 
variableDeclaration : type atomVariableDeclaration (',' atomVariableDeclaration)*  Semicolon;
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
block : (LeftBrace statement* RightBrace) ;
ifStatement : 
	If '(' expression ')' (statement) 
	(Else statement)?;
forStatement :
	For '(' (statement) expression?  Semicolon expression? ')' statement;
whileStatement :
	While '(' expression ')' statement;
returnStatement : Return expression?  Semicolon;
breakStatement : Break  Semicolon;
continueStatement : Continue  Semicolon;
expressionStatement : expression  Semicolon;
initalstatement : type? parameterList;

type : (Int | Bool | String | Identifier | Void) arrayLable*;
arrayLable : ('['expression?']');
// formatStringexpression 
// 	: 'f"'  ( FormatChar | LeftBrace expression RightBrace  )* '"'	;

formatStringElement: 
	FormatStringI
	|(FormatStringL FormatStringM? (LeftBrace expression? RightBrace (FormatStringM* LeftBrace expression? RightBrace)*)? FormatStringM? FormatStringR);


expression : 
	'('expression')'
	|New type ('[' (expression)? ']')* atom?
	|expression Member atom
	|expression ('('parameterList ')'| (('['expression']')+))
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
	|<assoc = right> expression '?' expression ':' expression
	|<assoc = right> expression (Assign) expression
	|atom;

	

atom :
	array
	|Identifier
	|constElement
	|formatStringElement;
array:
	Identifier ('[' expression? ']')+;
constArray : LeftBrace (constElement)? (',' constElement)* RightBrace;

constElement : Interger | Identifier | ConstString| constArray | True | False | This | Null;
