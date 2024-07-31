parser grammar Mxparser;
options { tokenVocab=Mxlexer; }
// program

program : (classDeclaration | functionDeclaration | variableDeclaration )* EOF;
classDeclaration : Class Identifier '{'
	(variableDeclaration
	|functionDeclaration
	|classConstructor)*
	'}';

functionDeclaration : type Identifier '(' parameterList? ')' block;
parameterList : parameter (',' parameter)*;
parameter : type atomVariableDeclaration;
variableDeclaration : type atomVariableDeclaration (',' atomVariableDeclaration)* ';';
atomVariableDeclaration : Identifier ('=' expression)?;

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
	|';';
block : ('{' statement* '}') ;
ifStatement : 
	If '(' expression ')' (statement) 
	(Else (statement))?;
forStatement :
	For '(' (initalstatement';') expression? ';' expression? ')' statement;
whileStatement :
	While '(' expression ')' statement;
returnStatement : Return expression? ';';
breakStatement : Break ';';
continueStatement : Continue ';';
expressionStatement : expression ';';
initalstatement : type? expression;

type : Int | Bool | String | Identifier | Void;
// formatStringexpression : 
// 	'f''\"' (~[{]|)*'\"'
formattedStringExpression
    : F_PREFIX QUOTE ( FormatChar | '{' expression '}'  )* QUOTE;
expression : 
	'('expression')'
	New type ('[' (expression)? ']')? atom?
	|expression'.'Identifier 
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
	|<assoc = right> expression (AssignType) expression
	|expression Comma expression
	|atom;

	

atom :
	array
	|Identifier
	|ConstElement
	|formattedStringExpression;
array:
	Identifier ('[' expression? ']')+;
