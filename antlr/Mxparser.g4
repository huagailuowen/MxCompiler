parser grammar Mx;
options {
	tokenVocab = Mxlexer;
}
r: 'hello' ID;
ID: [a-z]+;
WS:  [ \t\r\n]+ -> skip;