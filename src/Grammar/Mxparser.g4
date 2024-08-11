grammar Mxparser;
@header {package Grammar;}
import Mxlexer;
// program
//int[][] a={2,4,{6,7,0},{8} };
//int a=f"{a+b}hello{c+d}world{e+f+f"eqw{a}we"}}}";
//int a=f"{f"{{}}" == "{}"}" == "true";
/*dfakldfj */
//int a=f"{a+b}hello{c+d}world{e+f+f"eqw{a}we"}}}";//8798458934789435"""
program : (classDeclaration | functionDeclaration | variableDeclaration )* EOF;
classDeclaration : Class Identifier '{'
	(variableDeclaration
	|functionDeclaration
	|classConstructor)*
	'}' Semicolon;

functionDeclaration : type Identifier ('(' parameterDeclarationList? ')'|'()') block;
parameterDeclarationList : parameterDeclaration (',' parameterDeclaration)*;
parameterDeclaration : type atomVariableDeclaration;

parameterList : expression? (',' expression)*; 
variableDeclaration : type atomVariableDeclaration (',' atomVariableDeclaration)*  Semicolon;
atomVariableDeclaration : atom (Assign expression)?;

classConstructor : Identifier '()' block;

statement : 
	ifStatement 								# ifStmt
	|block											# blockStmt
	|forStatement								# forStmt
	|whileStatement							# whileStmt
	|returnStatement						# returnStmt
	|breakStatement							# breakStmt
	|continueStatement					# continueStmt
	|expressionStatement				# expressionStmt
	|variableDeclaration				# variableDeclarationStmt
	|Semicolon                 	# emptyStmt;
block : ('{' statement* '}') ;
ifStatement : 
	If '(' cond=expression ')' (then=statement) 
	(Else els=statement)?;
forStatement :
	For '(' (init=statement) cond=expression?  Semicolon update=expression? ')' content=statement;
whileStatement :
	While '(' cond=expression ')' content=statement;
returnStatement : Return res=expression?  Semicolon;
breakStatement : Break  Semicolon;
continueStatement : Continue  Semicolon;
expressionStatement : expression  Semicolon;

type : typ=(Int | Bool | String | Identifier | Void) arrayLable*;
arrayLable : ('['expression?']');
// formatStringexpression 
// 	: 'f"'  ( FormatChar | '{' expression '}'  )* '"'	;

formatStringElement: 
	FormatStringI
	|(FormatStringL expression? (FormatStringM expression?)* FormatStringR);


expression : 
	'('expression')'																											# childExpr
	|New type (constArray | ('()'))?                      								# newExpr
	|expression Member atom																								# memberExpr
  |expression (('('parameterList ')')|'()')															# callExpr
	|expression ((arrayLable)+)																						# arrayExpr
	|expression op=(SelfPlus|SelfMinus)																		# selfOpExpr
	|<assoc = right> op=(SelfPlus|SelfMinus|Not|Minus|NotBit) expression	# preOpExpr
	|expression op=(Multiply|Divide|Mod) expression												# binaryExpr
	|expression op=(Plus|Minus) expression																# binaryExpr
	|expression op=(LeftMove|RightMove) expression												# binaryExpr
	|expression op=(Greater|Less|GreaterEqual|LessEqual) expression				# binaryExpr
	|expression op=(Equal|InEqual) expression															# binaryExpr
	|expression op=AndBit expression																			# binaryExpr
	|expression op=XorBit expression																			# binaryExpr
	|expression op=OrBit expression																				# binaryExpr
	|expression op=And expression																					# binaryExpr
	|expression op=Or expression 																					# binaryExpr
	|<assoc = right> expression '?' expression ':' expression							# ternaryExpr
	|<assoc = right> expression (Assign) expression												# assignExpr
	|formatStringElement																									# formatStringExpr
	|atom                                                                 # atomExpr;																																

	

atom :
	// array																																	# arrayAtom
	Identifier																														# idAtom
	|Interger																															# intAtom
	|ConstString																													# stringAtom
	|True																																	# trueAtom
	|False																																# falseAtom
	|This																																	# thisAtom
	|Null																																	# nullAtom
	// |constElement																													# constAtom
	|constArray																														# arrayAtom;
// array:
// 	Identifier ('[' expression? ']')+;
//{2+3,4,5} is not allowed
constArray : '{' (atom)? (',' atom)* '}';

// constElement : 
// 	atomElement=(Interger | Identifier | ConstString| True | False | This | Null) # constAtomElement
// 	|constArray 																																	# constArrayElement;