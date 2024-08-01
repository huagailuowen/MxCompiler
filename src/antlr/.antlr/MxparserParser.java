// Generated from /mnt/d/周宸源/大学/学习/程序/MxCompiler/src/antlr/Mxparser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MxparserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, Void=9, 
		Null=10, Bool=11, Int=12, String=13, New=14, Class=15, True=16, False=17, 
		This=18, If=19, Else=20, For=21, While=22, Break=23, Continue=24, Return=25, 
		Plus=26, Minus=27, Multiply=28, Divide=29, Mod=30, Greater=31, Less=32, 
		GreaterEqual=33, LessEqual=34, Equal=35, InEqual=36, And=37, Or=38, Not=39, 
		LeftMove=40, RightMove=41, AndBit=42, OrBit=43, XorBit=44, NotBit=45, 
		Assign=46, SelfPlus=47, SelfMinus=48, Member=49, Comma=50, Semicolon=51, 
		Comment=52, BlockComment=53, Whitespace=54, Interger=55, Identifier=56, 
		FormatStringChar=57, ConstString=58, FormatStringI=59, FormatStringL=60, 
		FormatStringM=61, FormatStringR=62;
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_functionDeclaration = 2, 
		RULE_parameterDeclarationList = 3, RULE_parameterDeclaration = 4, RULE_parameterList = 5, 
		RULE_variableDeclaration = 6, RULE_atomVariableDeclaration = 7, RULE_classConstructor = 8, 
		RULE_statement = 9, RULE_block = 10, RULE_ifStatement = 11, RULE_forStatement = 12, 
		RULE_whileStatement = 13, RULE_returnStatement = 14, RULE_breakStatement = 15, 
		RULE_continueStatement = 16, RULE_expressionStatement = 17, RULE_initalstatement = 18, 
		RULE_type = 19, RULE_arrayLable = 20, RULE_formatStringElement = 21, RULE_expression = 22, 
		RULE_atom = 23, RULE_array = 24, RULE_constArray = 25, RULE_constElement = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classDeclaration", "functionDeclaration", "parameterDeclarationList", 
			"parameterDeclaration", "parameterList", "variableDeclaration", "atomVariableDeclaration", 
			"classConstructor", "statement", "block", "ifStatement", "forStatement", 
			"whileStatement", "returnStatement", "breakStatement", "continueStatement", 
			"expressionStatement", "initalstatement", "type", "arrayLable", "formatStringElement", 
			"expression", "atom", "array", "constArray", "constElement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'['", "']'", "'?'", "':'", "'void'", 
			"'null'", "'bool'", "'int'", "'string'", "'new'", "'class'", "'true'", 
			"'false'", "'this'", "'if'", "'else'", "'for'", "'while'", "'break'", 
			"'continue'", "'return'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", 
			"'>='", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", 
			"'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "'.'", "','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "Void", "Null", 
			"Bool", "Int", "String", "New", "Class", "True", "False", "This", "If", 
			"Else", "For", "While", "Break", "Continue", "Return", "Plus", "Minus", 
			"Multiply", "Divide", "Mod", "Greater", "Less", "GreaterEqual", "LessEqual", 
			"Equal", "InEqual", "And", "Or", "Not", "LeftMove", "RightMove", "AndBit", 
			"OrBit", "XorBit", "NotBit", "Assign", "SelfPlus", "SelfMinus", "Member", 
			"Comma", "Semicolon", "Comment", "BlockComment", "Whitespace", "Interger", 
			"Identifier", "FormatStringChar", "ConstString", "FormatStringI", "FormatStringL", 
			"FormatStringM", "FormatStringR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mxparser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxparserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxparserParser.EOF, 0); }
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037975552L) != 0)) {
				{
				setState(57);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(54);
					classDeclaration();
					}
					break;
				case 2:
					{
					setState(55);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(56);
					variableDeclaration();
					}
					break;
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxparserParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public List<ClassConstructorContext> classConstructor() {
			return getRuleContexts(ClassConstructorContext.class);
		}
		public ClassConstructorContext classConstructor(int i) {
			return getRuleContext(ClassConstructorContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(Class);
			setState(65);
			match(Identifier);
			setState(66);
			match(T__0);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037942784L) != 0)) {
				{
				setState(70);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(67);
					variableDeclaration();
					}
					break;
				case 2:
					{
					setState(68);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(69);
					classConstructor();
					}
					break;
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(T__1);
			setState(76);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterDeclarationListContext parameterDeclarationList() {
			return getRuleContext(ParameterDeclarationListContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			type();
			setState(79);
			match(Identifier);
			setState(80);
			match(T__2);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037942784L) != 0)) {
				{
				setState(81);
				parameterDeclarationList();
				}
			}

			setState(84);
			match(T__3);
			setState(85);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterDeclarationListContext extends ParserRuleContext {
		public List<ParameterDeclarationContext> parameterDeclaration() {
			return getRuleContexts(ParameterDeclarationContext.class);
		}
		public ParameterDeclarationContext parameterDeclaration(int i) {
			return getRuleContext(ParameterDeclarationContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxparserParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxparserParser.Comma, i);
		}
		public ParameterDeclarationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclarationList; }
	}

	public final ParameterDeclarationListContext parameterDeclarationList() throws RecognitionException {
		ParameterDeclarationListContext _localctx = new ParameterDeclarationListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parameterDeclarationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			parameterDeclaration();
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(88);
				match(Comma);
				setState(89);
				parameterDeclaration();
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AtomVariableDeclarationContext atomVariableDeclaration() {
			return getRuleContext(AtomVariableDeclarationContext.class,0);
		}
		public ParameterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclaration; }
	}

	public final ParameterDeclarationContext parameterDeclaration() throws RecognitionException {
		ParameterDeclarationContext _localctx = new ParameterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameterDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			type();
			setState(96);
			atomVariableDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxparserParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxparserParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
				{
				setState(98);
				expression(0);
				}
			}

			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(101);
				match(Comma);
				setState(102);
				expression(0);
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<AtomVariableDeclarationContext> atomVariableDeclaration() {
			return getRuleContexts(AtomVariableDeclarationContext.class);
		}
		public AtomVariableDeclarationContext atomVariableDeclaration(int i) {
			return getRuleContext(AtomVariableDeclarationContext.class,i);
		}
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxparserParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxparserParser.Comma, i);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			type();
			setState(109);
			atomVariableDeclaration();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(110);
				match(Comma);
				setState(111);
				atomVariableDeclaration();
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomVariableDeclarationContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode Assign() { return getToken(MxparserParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AtomVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomVariableDeclaration; }
	}

	public final AtomVariableDeclarationContext atomVariableDeclaration() throws RecognitionException {
		AtomVariableDeclarationContext _localctx = new AtomVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_atomVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			atom();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(120);
				match(Assign);
				setState(121);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassConstructorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ClassConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classConstructor; }
	}

	public final ClassConstructorContext classConstructor() throws RecognitionException {
		ClassConstructorContext _localctx = new ClassConstructorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(Identifier);
			setState(125);
			match(T__2);
			setState(126);
			match(T__3);
			setState(127);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				ifStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				block();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				forStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				returnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(134);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(135);
				continueStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(136);
				expressionStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(137);
				variableDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(138);
				match(Semicolon);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(141);
			match(T__0);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2128408770725772810L) != 0)) {
				{
				{
				setState(142);
				statement();
				}
				}
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(148);
			match(T__1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxparserParser.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxparserParser.Else, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(If);
			setState(151);
			match(T__2);
			setState(152);
			expression(0);
			setState(153);
			match(T__3);
			{
			setState(154);
			statement();
			}
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(155);
				match(Else);
				setState(156);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxparserParser.For, 0); }
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(For);
			setState(160);
			match(T__2);
			{
			setState(161);
			statement();
			}
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
				{
				setState(162);
				expression(0);
				}
			}

			setState(165);
			match(Semicolon);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
				{
				setState(166);
				expression(0);
				}
			}

			setState(169);
			match(T__3);
			setState(170);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxparserParser.While, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(While);
			setState(173);
			match(T__2);
			setState(174);
			expression(0);
			setState(175);
			match(T__3);
			setState(176);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxparserParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(Return);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
				{
				setState(179);
				expression(0);
				}
			}

			setState(182);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxparserParser.Break, 0); }
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(Break);
			setState(185);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(MxparserParser.Continue, 0); }
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(Continue);
			setState(188);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			expression(0);
			setState(191);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitalstatementContext extends ParserRuleContext {
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public InitalstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initalstatement; }
	}

	public final InitalstatementContext initalstatement() throws RecognitionException {
		InitalstatementContext _localctx = new InitalstatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_initalstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(193);
				type();
				}
				break;
			}
			setState(196);
			parameterList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxparserParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxparserParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxparserParser.String, 0); }
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public TerminalNode Void() { return getToken(MxparserParser.Void, 0); }
		public List<ArrayLableContext> arrayLable() {
			return getRuleContexts(ArrayLableContext.class);
		}
		public ArrayLableContext arrayLable(int i) {
			return getRuleContext(ArrayLableContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_type);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594037942784L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(202);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(199);
					arrayLable();
					}
					} 
				}
				setState(204);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLableContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayLableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLable; }
	}

	public final ArrayLableContext arrayLable() throws RecognitionException {
		ArrayLableContext _localctx = new ArrayLableContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arrayLable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(205);
			match(T__4);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
				{
				setState(206);
				expression(0);
				}
			}

			setState(209);
			match(T__5);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormatStringElementContext extends ParserRuleContext {
		public TerminalNode FormatStringI() { return getToken(MxparserParser.FormatStringI, 0); }
		public TerminalNode FormatStringL() { return getToken(MxparserParser.FormatStringL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode FormatStringR() { return getToken(MxparserParser.FormatStringR, 0); }
		public List<TerminalNode> FormatStringM() { return getTokens(MxparserParser.FormatStringM); }
		public TerminalNode FormatStringM(int i) {
			return getToken(MxparserParser.FormatStringM, i);
		}
		public FormatStringElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatStringElement; }
	}

	public final FormatStringElementContext formatStringElement() throws RecognitionException {
		FormatStringElementContext _localctx = new FormatStringElementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_formatStringElement);
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FormatStringI:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(FormatStringI);
				}
				break;
			case FormatStringL:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(212);
				match(FormatStringL);
				setState(213);
				expression(0);
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FormatStringM) {
					{
					{
					setState(214);
					match(FormatStringM);
					setState(215);
					expression(0);
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221);
				match(FormatStringR);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode New() { return getToken(MxparserParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxparserParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxparserParser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(MxparserParser.Not, 0); }
		public TerminalNode Minus() { return getToken(MxparserParser.Minus, 0); }
		public TerminalNode NotBit() { return getToken(MxparserParser.NotBit, 0); }
		public TerminalNode Multiply() { return getToken(MxparserParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(MxparserParser.Divide, 0); }
		public TerminalNode Mod() { return getToken(MxparserParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(MxparserParser.Plus, 0); }
		public TerminalNode LeftMove() { return getToken(MxparserParser.LeftMove, 0); }
		public TerminalNode RightMove() { return getToken(MxparserParser.RightMove, 0); }
		public TerminalNode Greater() { return getToken(MxparserParser.Greater, 0); }
		public TerminalNode Less() { return getToken(MxparserParser.Less, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxparserParser.GreaterEqual, 0); }
		public TerminalNode LessEqual() { return getToken(MxparserParser.LessEqual, 0); }
		public TerminalNode Equal() { return getToken(MxparserParser.Equal, 0); }
		public TerminalNode InEqual() { return getToken(MxparserParser.InEqual, 0); }
		public TerminalNode AndBit() { return getToken(MxparserParser.AndBit, 0); }
		public TerminalNode XorBit() { return getToken(MxparserParser.XorBit, 0); }
		public TerminalNode OrBit() { return getToken(MxparserParser.OrBit, 0); }
		public TerminalNode And() { return getToken(MxparserParser.And, 0); }
		public TerminalNode Or() { return getToken(MxparserParser.Or, 0); }
		public TerminalNode Assign() { return getToken(MxparserParser.Assign, 0); }
		public TerminalNode Member() { return getToken(MxparserParser.Member, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(226);
				match(T__2);
				setState(227);
				expression(0);
				setState(228);
				match(T__3);
				}
				break;
			case New:
				{
				setState(230);
				match(New);
				setState(231);
				type();
				setState(239);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(232);
						match(T__4);
						setState(234);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
							{
							setState(233);
							expression(0);
							}
						}

						setState(236);
						match(T__5);
						}
						} 
					}
					setState(241);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				setState(243);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(242);
					atom();
					}
					break;
				}
				}
				break;
			case Minus:
			case Not:
			case NotBit:
			case SelfPlus:
			case SelfMinus:
				{
				setState(245);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 457946727186432L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(246);
				expression(14);
				}
				break;
			case T__0:
			case Null:
			case True:
			case False:
			case This:
			case Interger:
			case Identifier:
			case ConstString:
			case FormatStringI:
			case FormatStringL:
				{
				setState(247);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(299);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(251);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1879048192L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(252);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(254);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(255);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(257);
						_la = _input.LA(1);
						if ( !(_la==LeftMove || _la==RightMove) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(258);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(260);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(261);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(262);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(263);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==InEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(264);
						expression(10);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(265);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						{
						setState(266);
						match(AndBit);
						}
						setState(267);
						expression(9);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(268);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						{
						setState(269);
						match(XorBit);
						}
						setState(270);
						expression(8);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						{
						setState(272);
						match(OrBit);
						}
						setState(273);
						expression(7);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(275);
						match(And);
						setState(276);
						expression(6);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(278);
						match(Or);
						setState(279);
						expression(5);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(280);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(281);
						match(T__6);
						setState(282);
						expression(0);
						setState(283);
						match(T__7);
						setState(284);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(287);
						match(Assign);
						}
						setState(288);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(289);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(290);
						match(Member);
						setState(291);
						atom();
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(292);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(293);
						match(T__2);
						setState(294);
						parameterList();
						setState(295);
						match(T__3);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(297);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(298);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public ConstElementContext constElement() {
			return getRuleContext(ConstElementContext.class,0);
		}
		public FormatStringElementContext formatStringElement() {
			return getRuleContext(FormatStringElementContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_atom);
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(306);
				constElement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(307);
				formatStringElement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_array);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(Identifier);
			setState(316); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(311);
					match(T__4);
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2126156970846536714L) != 0)) {
						{
						setState(312);
						expression(0);
						}
					}

					setState(315);
					match(T__5);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(318); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstArrayContext extends ParserRuleContext {
		public List<ConstElementContext> constElement() {
			return getRuleContexts(ConstElementContext.class);
		}
		public ConstElementContext constElement(int i) {
			return getRuleContext(ConstElementContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxparserParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxparserParser.Comma, i);
		}
		public ConstArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constArray; }
	}

	public final ConstArrayContext constArray() throws RecognitionException {
		ConstArrayContext _localctx = new ConstArrayContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_constArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			match(T__0);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 396316767209063426L) != 0)) {
				{
				setState(321);
				constElement();
				}
			}

			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(324);
				match(Comma);
				setState(325);
				constElement();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(331);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstElementContext extends ParserRuleContext {
		public TerminalNode Interger() { return getToken(MxparserParser.Interger, 0); }
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public TerminalNode ConstString() { return getToken(MxparserParser.ConstString, 0); }
		public ConstArrayContext constArray() {
			return getRuleContext(ConstArrayContext.class,0);
		}
		public TerminalNode True() { return getToken(MxparserParser.True, 0); }
		public TerminalNode False() { return getToken(MxparserParser.False, 0); }
		public TerminalNode This() { return getToken(MxparserParser.This, 0); }
		public TerminalNode Null() { return getToken(MxparserParser.Null, 0); }
		public ConstElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constElement; }
	}

	public final ConstElementContext constElement() throws RecognitionException {
		ConstElementContext _localctx = new ConstElementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constElement);
		try {
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Interger:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				match(Interger);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				match(Identifier);
				}
				break;
			case ConstString:
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				match(ConstString);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
				constArray();
				}
				break;
			case True:
				enterOuterAlt(_localctx, 5);
				{
				setState(337);
				match(True);
				}
				break;
			case False:
				enterOuterAlt(_localctx, 6);
				{
				setState(338);
				match(False);
				}
				break;
			case This:
				enterOuterAlt(_localctx, 7);
				{
				setState(339);
				match(This);
				}
				break;
			case Null:
				enterOuterAlt(_localctx, 8);
				{
				setState(340);
				match(Null);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 22:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		case 14:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u0158\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000:\b\u0000\n\u0000\f\u0000=\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001G\b\u0001\n\u0001\f\u0001J\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002S\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003[\b\u0003\n\u0003\f\u0003^\t\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0003\u0005d\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005h\b\u0005\n\u0005\f\u0005k\t\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006q\b\u0006"+
		"\n\u0006\f\u0006t\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007{\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u008c\b\t\u0001\n\u0001\n\u0005\n\u0090\b\n\n\n\f\n"+
		"\u0093\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u009e\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u00a4\b\f\u0001\f\u0001\f\u0003\f\u00a8"+
		"\b\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0003\u000e\u00b5\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0003\u0012\u00c3"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u00c9"+
		"\b\u0013\n\u0013\f\u0013\u00cc\t\u0013\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u00d0\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u00d9\b\u0015\n\u0015\f\u0015\u00dc"+
		"\t\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00e0\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u00eb\b\u0016\u0001\u0016\u0005\u0016"+
		"\u00ee\b\u0016\n\u0016\f\u0016\u00f1\t\u0016\u0001\u0016\u0003\u0016\u00f4"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00f9\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u012c\b\u0016\n\u0016\f\u0016\u012f\t\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0135\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u013a\b\u0018\u0001\u0018\u0004"+
		"\u0018\u013d\b\u0018\u000b\u0018\f\u0018\u013e\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u0143\b\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0147\b"+
		"\u0019\n\u0019\f\u0019\u014a\t\u0019\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0003\u001a\u0156\b\u001a\u0001\u001a\u0000\u0001,\u001b"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.024\u0000\b\u0003\u0000\t\t\u000b\r88\u0004\u0000"+
		"\u001b\u001b\'\'--/0\u0001\u0000\u001c\u001e\u0001\u0000\u001a\u001b\u0001"+
		"\u0000()\u0001\u0000\u001f\"\u0001\u0000#$\u0001\u0000/0\u017e\u0000;"+
		"\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004N\u0001\u0000"+
		"\u0000\u0000\u0006W\u0001\u0000\u0000\u0000\b_\u0001\u0000\u0000\u0000"+
		"\nc\u0001\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000\u000ew\u0001\u0000"+
		"\u0000\u0000\u0010|\u0001\u0000\u0000\u0000\u0012\u008b\u0001\u0000\u0000"+
		"\u0000\u0014\u008d\u0001\u0000\u0000\u0000\u0016\u0096\u0001\u0000\u0000"+
		"\u0000\u0018\u009f\u0001\u0000\u0000\u0000\u001a\u00ac\u0001\u0000\u0000"+
		"\u0000\u001c\u00b2\u0001\u0000\u0000\u0000\u001e\u00b8\u0001\u0000\u0000"+
		"\u0000 \u00bb\u0001\u0000\u0000\u0000\"\u00be\u0001\u0000\u0000\u0000"+
		"$\u00c2\u0001\u0000\u0000\u0000&\u00c6\u0001\u0000\u0000\u0000(\u00cd"+
		"\u0001\u0000\u0000\u0000*\u00df\u0001\u0000\u0000\u0000,\u00f8\u0001\u0000"+
		"\u0000\u0000.\u0134\u0001\u0000\u0000\u00000\u0136\u0001\u0000\u0000\u0000"+
		"2\u0140\u0001\u0000\u0000\u00004\u0155\u0001\u0000\u0000\u00006:\u0003"+
		"\u0002\u0001\u00007:\u0003\u0004\u0002\u00008:\u0003\f\u0006\u000096\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u000098\u0001\u0000\u0000\u0000"+
		":=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005\u0000"+
		"\u0000\u0001?\u0001\u0001\u0000\u0000\u0000@A\u0005\u000f\u0000\u0000"+
		"AB\u00058\u0000\u0000BH\u0005\u0001\u0000\u0000CG\u0003\f\u0006\u0000"+
		"DG\u0003\u0004\u0002\u0000EG\u0003\u0010\b\u0000FC\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000FE\u0001\u0000\u0000\u0000GJ\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001\u0000"+
		"\u0000\u0000JH\u0001\u0000\u0000\u0000KL\u0005\u0002\u0000\u0000LM\u0005"+
		"3\u0000\u0000M\u0003\u0001\u0000\u0000\u0000NO\u0003&\u0013\u0000OP\u0005"+
		"8\u0000\u0000PR\u0005\u0003\u0000\u0000QS\u0003\u0006\u0003\u0000RQ\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"TU\u0005\u0004\u0000\u0000UV\u0003\u0014\n\u0000V\u0005\u0001\u0000\u0000"+
		"\u0000W\\\u0003\b\u0004\u0000XY\u00052\u0000\u0000Y[\u0003\b\u0004\u0000"+
		"ZX\u0001\u0000\u0000\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000]\u0007\u0001\u0000\u0000\u0000^\\\u0001"+
		"\u0000\u0000\u0000_`\u0003&\u0013\u0000`a\u0003\u000e\u0007\u0000a\t\u0001"+
		"\u0000\u0000\u0000bd\u0003,\u0016\u0000cb\u0001\u0000\u0000\u0000cd\u0001"+
		"\u0000\u0000\u0000di\u0001\u0000\u0000\u0000ef\u00052\u0000\u0000fh\u0003"+
		",\u0016\u0000ge\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001"+
		"\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\u000b\u0001\u0000\u0000"+
		"\u0000ki\u0001\u0000\u0000\u0000lm\u0003&\u0013\u0000mr\u0003\u000e\u0007"+
		"\u0000no\u00052\u0000\u0000oq\u0003\u000e\u0007\u0000pn\u0001\u0000\u0000"+
		"\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000su\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000uv\u0005"+
		"3\u0000\u0000v\r\u0001\u0000\u0000\u0000wz\u0003.\u0017\u0000xy\u0005"+
		".\u0000\u0000y{\u0003,\u0016\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{\u000f\u0001\u0000\u0000\u0000|}\u00058\u0000\u0000}~\u0005"+
		"\u0003\u0000\u0000~\u007f\u0005\u0004\u0000\u0000\u007f\u0080\u0003\u0014"+
		"\n\u0000\u0080\u0011\u0001\u0000\u0000\u0000\u0081\u008c\u0003\u0016\u000b"+
		"\u0000\u0082\u008c\u0003\u0014\n\u0000\u0083\u008c\u0003\u0018\f\u0000"+
		"\u0084\u008c\u0003\u001a\r\u0000\u0085\u008c\u0003\u001c\u000e\u0000\u0086"+
		"\u008c\u0003\u001e\u000f\u0000\u0087\u008c\u0003 \u0010\u0000\u0088\u008c"+
		"\u0003\"\u0011\u0000\u0089\u008c\u0003\f\u0006\u0000\u008a\u008c\u0005"+
		"3\u0000\u0000\u008b\u0081\u0001\u0000\u0000\u0000\u008b\u0082\u0001\u0000"+
		"\u0000\u0000\u008b\u0083\u0001\u0000\u0000\u0000\u008b\u0084\u0001\u0000"+
		"\u0000\u0000\u008b\u0085\u0001\u0000\u0000\u0000\u008b\u0086\u0001\u0000"+
		"\u0000\u0000\u008b\u0087\u0001\u0000\u0000\u0000\u008b\u0088\u0001\u0000"+
		"\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008a\u0001\u0000"+
		"\u0000\u0000\u008c\u0013\u0001\u0000\u0000\u0000\u008d\u0091\u0005\u0001"+
		"\u0000\u0000\u008e\u0090\u0003\u0012\t\u0000\u008f\u008e\u0001\u0000\u0000"+
		"\u0000\u0090\u0093\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0094\u0001\u0000\u0000"+
		"\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u0002\u0000"+
		"\u0000\u0095\u0015\u0001\u0000\u0000\u0000\u0096\u0097\u0005\u0013\u0000"+
		"\u0000\u0097\u0098\u0005\u0003\u0000\u0000\u0098\u0099\u0003,\u0016\u0000"+
		"\u0099\u009a\u0005\u0004\u0000\u0000\u009a\u009d\u0003\u0012\t\u0000\u009b"+
		"\u009c\u0005\u0014\u0000\u0000\u009c\u009e\u0003\u0012\t\u0000\u009d\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u0017"+
		"\u0001\u0000\u0000\u0000\u009f\u00a0\u0005\u0015\u0000\u0000\u00a0\u00a1"+
		"\u0005\u0003\u0000\u0000\u00a1\u00a3\u0003\u0012\t\u0000\u00a2\u00a4\u0003"+
		",\u0016\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a7\u00053\u0000"+
		"\u0000\u00a6\u00a8\u0003,\u0016\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u0005\u0004\u0000\u0000\u00aa\u00ab\u0003\u0012\t\u0000\u00ab"+
		"\u0019\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005\u0016\u0000\u0000\u00ad"+
		"\u00ae\u0005\u0003\u0000\u0000\u00ae\u00af\u0003,\u0016\u0000\u00af\u00b0"+
		"\u0005\u0004\u0000\u0000\u00b0\u00b1\u0003\u0012\t\u0000\u00b1\u001b\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b4\u0005\u0019\u0000\u0000\u00b3\u00b5\u0003"+
		",\u0016\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u00053\u0000"+
		"\u0000\u00b7\u001d\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\u0017\u0000"+
		"\u0000\u00b9\u00ba\u00053\u0000\u0000\u00ba\u001f\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005\u0018\u0000\u0000\u00bc\u00bd\u00053\u0000\u0000\u00bd"+
		"!\u0001\u0000\u0000\u0000\u00be\u00bf\u0003,\u0016\u0000\u00bf\u00c0\u0005"+
		"3\u0000\u0000\u00c0#\u0001\u0000\u0000\u0000\u00c1\u00c3\u0003&\u0013"+
		"\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c5\u0003\n\u0005\u0000"+
		"\u00c5%\u0001\u0000\u0000\u0000\u00c6\u00ca\u0007\u0000\u0000\u0000\u00c7"+
		"\u00c9\u0003(\u0014\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9\u00cc"+
		"\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb\'\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001"+
		"\u0000\u0000\u0000\u00cd\u00cf\u0005\u0005\u0000\u0000\u00ce\u00d0\u0003"+
		",\u0016\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d2\u0005\u0006"+
		"\u0000\u0000\u00d2)\u0001\u0000\u0000\u0000\u00d3\u00e0\u0005;\u0000\u0000"+
		"\u00d4\u00d5\u0005<\u0000\u0000\u00d5\u00da\u0003,\u0016\u0000\u00d6\u00d7"+
		"\u0005=\u0000\u0000\u00d7\u00d9\u0003,\u0016\u0000\u00d8\u00d6\u0001\u0000"+
		"\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dd\u0001\u0000"+
		"\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00de\u0005>\u0000"+
		"\u0000\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00d3\u0001\u0000\u0000"+
		"\u0000\u00df\u00d4\u0001\u0000\u0000\u0000\u00e0+\u0001\u0000\u0000\u0000"+
		"\u00e1\u00e2\u0006\u0016\uffff\uffff\u0000\u00e2\u00e3\u0005\u0003\u0000"+
		"\u0000\u00e3\u00e4\u0003,\u0016\u0000\u00e4\u00e5\u0005\u0004\u0000\u0000"+
		"\u00e5\u00f9\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005\u000e\u0000\u0000"+
		"\u00e7\u00ef\u0003&\u0013\u0000\u00e8\u00ea\u0005\u0005\u0000\u0000\u00e9"+
		"\u00eb\u0003,\u0016\u0000\u00ea\u00e9\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ee"+
		"\u0005\u0006\u0000\u0000\u00ed\u00e8\u0001\u0000\u0000\u0000\u00ee\u00f1"+
		"\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000\u0000\u0000\u00f1\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f4\u0003.\u0017\u0000\u00f3\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f9\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0007\u0001\u0000\u0000\u00f6\u00f9\u0003"+
		",\u0016\u000e\u00f7\u00f9\u0003.\u0017\u0000\u00f8\u00e1\u0001\u0000\u0000"+
		"\u0000\u00f8\u00e6\u0001\u0000\u0000\u0000\u00f8\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f9\u012d\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\n\r\u0000\u0000\u00fb\u00fc\u0007\u0002\u0000\u0000"+
		"\u00fc\u012c\u0003,\u0016\u000e\u00fd\u00fe\n\f\u0000\u0000\u00fe\u00ff"+
		"\u0007\u0003\u0000\u0000\u00ff\u012c\u0003,\u0016\r\u0100\u0101\n\u000b"+
		"\u0000\u0000\u0101\u0102\u0007\u0004\u0000\u0000\u0102\u012c\u0003,\u0016"+
		"\f\u0103\u0104\n\n\u0000\u0000\u0104\u0105\u0007\u0005\u0000\u0000\u0105"+
		"\u012c\u0003,\u0016\u000b\u0106\u0107\n\t\u0000\u0000\u0107\u0108\u0007"+
		"\u0006\u0000\u0000\u0108\u012c\u0003,\u0016\n\u0109\u010a\n\b\u0000\u0000"+
		"\u010a\u010b\u0005*\u0000\u0000\u010b\u012c\u0003,\u0016\t\u010c\u010d"+
		"\n\u0007\u0000\u0000\u010d\u010e\u0005,\u0000\u0000\u010e\u012c\u0003"+
		",\u0016\b\u010f\u0110\n\u0006\u0000\u0000\u0110\u0111\u0005+\u0000\u0000"+
		"\u0111\u012c\u0003,\u0016\u0007\u0112\u0113\n\u0005\u0000\u0000\u0113"+
		"\u0114\u0005%\u0000\u0000\u0114\u012c\u0003,\u0016\u0006\u0115\u0116\n"+
		"\u0004\u0000\u0000\u0116\u0117\u0005&\u0000\u0000\u0117\u012c\u0003,\u0016"+
		"\u0005\u0118\u0119\n\u0003\u0000\u0000\u0119\u011a\u0005\u0007\u0000\u0000"+
		"\u011a\u011b\u0003,\u0016\u0000\u011b\u011c\u0005\b\u0000\u0000\u011c"+
		"\u011d\u0003,\u0016\u0003\u011d\u012c\u0001\u0000\u0000\u0000\u011e\u011f"+
		"\n\u0002\u0000\u0000\u011f\u0120\u0005.\u0000\u0000\u0120\u012c\u0003"+
		",\u0016\u0002\u0121\u0122\n\u0011\u0000\u0000\u0122\u0123\u00051\u0000"+
		"\u0000\u0123\u012c\u0003.\u0017\u0000\u0124\u0125\n\u0010\u0000\u0000"+
		"\u0125\u0126\u0005\u0003\u0000\u0000\u0126\u0127\u0003\n\u0005\u0000\u0127"+
		"\u0128\u0005\u0004\u0000\u0000\u0128\u012c\u0001\u0000\u0000\u0000\u0129"+
		"\u012a\n\u000f\u0000\u0000\u012a\u012c\u0007\u0007\u0000\u0000\u012b\u00fa"+
		"\u0001\u0000\u0000\u0000\u012b\u00fd\u0001\u0000\u0000\u0000\u012b\u0100"+
		"\u0001\u0000\u0000\u0000\u012b\u0103\u0001\u0000\u0000\u0000\u012b\u0106"+
		"\u0001\u0000\u0000\u0000\u012b\u0109\u0001\u0000\u0000\u0000\u012b\u010c"+
		"\u0001\u0000\u0000\u0000\u012b\u010f\u0001\u0000\u0000\u0000\u012b\u0112"+
		"\u0001\u0000\u0000\u0000\u012b\u0115\u0001\u0000\u0000\u0000\u012b\u0118"+
		"\u0001\u0000\u0000\u0000\u012b\u011e\u0001\u0000\u0000\u0000\u012b\u0121"+
		"\u0001\u0000\u0000\u0000\u012b\u0124\u0001\u0000\u0000\u0000\u012b\u0129"+
		"\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000\u0000\u012d\u012b"+
		"\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e-\u0001"+
		"\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u0130\u0135\u0003"+
		"0\u0018\u0000\u0131\u0135\u00058\u0000\u0000\u0132\u0135\u00034\u001a"+
		"\u0000\u0133\u0135\u0003*\u0015\u0000\u0134\u0130\u0001\u0000\u0000\u0000"+
		"\u0134\u0131\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000"+
		"\u0134\u0133\u0001\u0000\u0000\u0000\u0135/\u0001\u0000\u0000\u0000\u0136"+
		"\u013c\u00058\u0000\u0000\u0137\u0139\u0005\u0005\u0000\u0000\u0138\u013a"+
		"\u0003,\u0016\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u0139\u013a\u0001"+
		"\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u013d\u0005"+
		"\u0006\u0000\u0000\u013c\u0137\u0001\u0000\u0000\u0000\u013d\u013e\u0001"+
		"\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013f\u0001"+
		"\u0000\u0000\u0000\u013f1\u0001\u0000\u0000\u0000\u0140\u0142\u0005\u0001"+
		"\u0000\u0000\u0141\u0143\u00034\u001a\u0000\u0142\u0141\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0148\u0001\u0000\u0000"+
		"\u0000\u0144\u0145\u00052\u0000\u0000\u0145\u0147\u00034\u001a\u0000\u0146"+
		"\u0144\u0001\u0000\u0000\u0000\u0147\u014a\u0001\u0000\u0000\u0000\u0148"+
		"\u0146\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149"+
		"\u014b\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014b"+
		"\u014c\u0005\u0002\u0000\u0000\u014c3\u0001\u0000\u0000\u0000\u014d\u0156"+
		"\u00057\u0000\u0000\u014e\u0156\u00058\u0000\u0000\u014f\u0156\u0005:"+
		"\u0000\u0000\u0150\u0156\u00032\u0019\u0000\u0151\u0156\u0005\u0010\u0000"+
		"\u0000\u0152\u0156\u0005\u0011\u0000\u0000\u0153\u0156\u0005\u0012\u0000"+
		"\u0000\u0154\u0156\u0005\n\u0000\u0000\u0155\u014d\u0001\u0000\u0000\u0000"+
		"\u0155\u014e\u0001\u0000\u0000\u0000\u0155\u014f\u0001\u0000\u0000\u0000"+
		"\u0155\u0150\u0001\u0000\u0000\u0000\u0155\u0151\u0001\u0000\u0000\u0000"+
		"\u0155\u0152\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000\u0000\u0000"+
		"\u0155\u0154\u0001\u0000\u0000\u0000\u01565\u0001\u0000\u0000\u0000!9"+
		";FHR\\cirz\u008b\u0091\u009d\u00a3\u00a7\u00b4\u00c2\u00ca\u00cf\u00da"+
		"\u00df\u00ea\u00ef\u00f3\u00f8\u012b\u012d\u0134\u0139\u013e\u0142\u0148"+
		"\u0155";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}