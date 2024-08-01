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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Void=7, Bool=8, Int=9, 
		String=10, New=11, Class=12, True=13, False=14, This=15, If=16, Else=17, 
		For=18, While=19, Break=20, Continue=21, Return=22, Plus=23, Minus=24, 
		Multiply=25, Divide=26, Mod=27, Greater=28, Less=29, GreaterEqual=30, 
		LessEqual=31, Equal=32, InEqual=33, And=34, Or=35, Not=36, LeftMove=37, 
		RightMove=38, AndBit=39, OrBit=40, XorBit=41, NotBit=42, Assign=43, SelfPlus=44, 
		SelfMinus=45, Member=46, Comma=47, Semicolon=48, Colon=49, QestionMark=50, 
		Comment=51, BlockComment=52, Whitespace=53, Interger=54, Identifier=55, 
		FormatStringChar=56, ConstString=57, FormatStringI=58, FormatStringL=59, 
		FormatStringM=60, FormatStringR=61;
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_functionDeclaration = 2, 
		RULE_parameterList = 3, RULE_parameter = 4, RULE_variableDeclaration = 5, 
		RULE_atomVariableDeclaration = 6, RULE_classConstructor = 7, RULE_statement = 8, 
		RULE_block = 9, RULE_ifStatement = 10, RULE_forStatement = 11, RULE_whileStatement = 12, 
		RULE_returnStatement = 13, RULE_breakStatement = 14, RULE_continueStatement = 15, 
		RULE_expressionStatement = 16, RULE_initalstatement = 17, RULE_type = 18, 
		RULE_arrayLable = 19, RULE_formatStringElement = 20, RULE_expression = 21, 
		RULE_atom = 22, RULE_array = 23, RULE_constArray = 24, RULE_constElement = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classDeclaration", "functionDeclaration", "parameterList", 
			"parameter", "variableDeclaration", "atomVariableDeclaration", "classConstructor", 
			"statement", "block", "ifStatement", "forStatement", "whileStatement", 
			"returnStatement", "breakStatement", "continueStatement", "expressionStatement", 
			"initalstatement", "type", "arrayLable", "formatStringElement", "expression", 
			"atom", "array", "constArray", "constElement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'['", "']'", "'void'", "'bool'", "'int'", 
			"'string'", "'new'", "'class'", "'true'", "'false'", "'this'", "'if'", 
			"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", 
			"'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", "'^'", "'~'", "'='", 
			"'++'", "'--'", "'.'", "','", "';'", "':'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "Void", "Bool", "Int", "String", 
			"New", "Class", "True", "False", "This", "If", "Else", "For", "While", 
			"Break", "Continue", "Return", "Plus", "Minus", "Multiply", "Divide", 
			"Mod", "Greater", "Less", "GreaterEqual", "LessEqual", "Equal", "InEqual", 
			"And", "Or", "Not", "LeftMove", "RightMove", "AndBit", "OrBit", "XorBit", 
			"NotBit", "Assign", "SelfPlus", "SelfMinus", "Member", "Comma", "Semicolon", 
			"Colon", "QestionMark", "Comment", "BlockComment", "Whitespace", "Interger", 
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
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018969984L) != 0)) {
				{
				setState(55);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(52);
					classDeclaration();
					}
					break;
				case 2:
					{
					setState(53);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(54);
					variableDeclaration();
					}
					break;
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
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
			setState(62);
			match(Class);
			setState(63);
			match(Identifier);
			setState(64);
			match(T__0);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018965888L) != 0)) {
				{
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(65);
					variableDeclaration();
					}
					break;
				case 2:
					{
					setState(66);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(67);
					classConstructor();
					}
					break;
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
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
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
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
			setState(75);
			type();
			setState(76);
			match(Identifier);
			setState(77);
			match(T__2);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018965888L) != 0)) {
				{
				setState(78);
				parameterList();
				}
			}

			setState(81);
			match(T__3);
			setState(82);
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
	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
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
		enterRule(_localctx, 6, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			parameter();
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(85);
				match(Comma);
				setState(86);
				parameter();
				}
				}
				setState(91);
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
	public static class ParameterContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public AtomVariableDeclarationContext atomVariableDeclaration() {
			return getRuleContext(AtomVariableDeclarationContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			type();
			setState(93);
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
		enterRule(_localctx, 10, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			type();
			setState(96);
			atomVariableDeclaration();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(97);
				match(Comma);
				setState(98);
				atomVariableDeclaration();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
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
		enterRule(_localctx, 12, RULE_atomVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			atom();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(107);
				match(Assign);
				setState(108);
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
		enterRule(_localctx, 14, RULE_classConstructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(Identifier);
			setState(112);
			match(T__2);
			setState(113);
			match(T__3);
			setState(114);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
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
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				ifStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				forStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				returnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(121);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(122);
				continueStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(123);
				expressionStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(124);
				variableDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(125);
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
		enterRule(_localctx, 18, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(128);
			match(T__0);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064314130292139914L) != 0)) {
				{
				{
				setState(129);
				statement();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(135);
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
		enterRule(_localctx, 20, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(If);
			setState(138);
			match(T__2);
			setState(139);
			expression(0);
			setState(140);
			match(T__3);
			{
			setState(141);
			statement();
			}
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(142);
				match(Else);
				{
				setState(143);
				statement();
				}
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
		public List<TerminalNode> Semicolon() { return getTokens(MxparserParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(MxparserParser.Semicolon, i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public InitalstatementContext initalstatement() {
			return getRuleContext(InitalstatementContext.class,0);
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
		enterRule(_localctx, 22, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(For);
			setState(147);
			match(T__2);
			{
			setState(148);
			initalstatement();
			setState(149);
			match(Semicolon);
			}
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
				{
				setState(151);
				expression(0);
				}
			}

			setState(154);
			match(Semicolon);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
				{
				setState(155);
				expression(0);
				}
			}

			setState(158);
			match(T__3);
			setState(159);
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
		enterRule(_localctx, 24, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(While);
			setState(162);
			match(T__2);
			setState(163);
			expression(0);
			setState(164);
			match(T__3);
			setState(165);
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
		enterRule(_localctx, 26, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(Return);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
				{
				setState(168);
				expression(0);
				}
			}

			setState(171);
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
		enterRule(_localctx, 28, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(Break);
			setState(174);
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
		enterRule(_localctx, 30, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(Continue);
			setState(177);
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
		enterRule(_localctx, 32, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			expression(0);
			setState(180);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 34, RULE_initalstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(182);
				type();
				}
				break;
			}
			setState(185);
			expression(0);
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
		enterRule(_localctx, 36, RULE_type);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018965888L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(188);
					arrayLable();
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 38, RULE_arrayLable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(194);
			match(T__4);
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
				{
				setState(195);
				expression(0);
				}
			}

			setState(198);
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
		enterRule(_localctx, 40, RULE_formatStringElement);
		int _la;
		try {
			setState(212);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FormatStringI:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(FormatStringI);
				}
				break;
			case FormatStringL:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(201);
				match(FormatStringL);
				setState(202);
				expression(0);
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FormatStringM) {
					{
					{
					setState(203);
					match(FormatStringM);
					setState(204);
					expression(0);
					}
					}
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(210);
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
		public TerminalNode QestionMark() { return getToken(MxparserParser.QestionMark, 0); }
		public TerminalNode Colon() { return getToken(MxparserParser.Colon, 0); }
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
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
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
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(215);
				match(T__2);
				setState(216);
				expression(0);
				setState(217);
				match(T__3);
				setState(218);
				match(New);
				setState(219);
				type();
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(220);
						match(T__4);
						setState(222);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
							{
							setState(221);
							expression(0);
							}
						}

						setState(224);
						match(T__5);
						}
						} 
					}
					setState(229);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
				}
				setState(231);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(230);
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
				setState(233);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 57243340898304L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(234);
				expression(14);
				}
				break;
			case QestionMark:
				{
				setState(235);
				match(QestionMark);
				setState(236);
				expression(0);
				setState(237);
				match(Colon);
				setState(238);
				expression(3);
				}
				break;
			case T__0:
			case True:
			case False:
			case This:
			case Interger:
			case Identifier:
			case ConstString:
			case FormatStringI:
			case FormatStringL:
				{
				setState(240);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(283);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(281);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(243);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(244);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 234881024L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(245);
						expression(14);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(246);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(247);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(248);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(249);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(250);
						_la = _input.LA(1);
						if ( !(_la==LeftMove || _la==RightMove) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(251);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(252);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(253);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4026531840L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(254);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(255);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(256);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==InEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(257);
						expression(10);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(258);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						{
						setState(259);
						match(AndBit);
						}
						setState(260);
						expression(9);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(261);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						{
						setState(262);
						match(XorBit);
						}
						setState(263);
						expression(8);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(264);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						{
						setState(265);
						match(OrBit);
						}
						setState(266);
						expression(7);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(267);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(268);
						match(And);
						setState(269);
						expression(6);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(270);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(271);
						match(Or);
						setState(272);
						expression(5);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(273);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(274);
						match(Assign);
						}
						setState(275);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(276);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(277);
						match(Member);
						setState(278);
						match(Identifier);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(279);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(280);
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
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
		enterRule(_localctx, 44, RULE_atom);
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(288);
				constElement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(289);
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
		enterRule(_localctx, 46, RULE_array);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(Identifier);
			setState(298); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(293);
					match(T__4);
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1064032655307235338L) != 0)) {
						{
						setState(294);
						expression(0);
						}
					}

					setState(297);
					match(T__5);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(300); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 48, RULE_constArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(T__0);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 198158383604359170L) != 0)) {
				{
				setState(303);
				constElement();
				}
			}

			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(306);
				match(Comma);
				setState(307);
				constElement();
				}
				}
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(313);
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
		public ConstElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constElement; }
	}

	public final ConstElementContext constElement() throws RecognitionException {
		ConstElementContext _localctx = new ConstElementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_constElement);
		try {
			setState(322);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Interger:
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				match(Interger);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				match(Identifier);
				}
				break;
			case ConstString:
				enterOuterAlt(_localctx, 3);
				{
				setState(317);
				match(ConstString);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(318);
				constArray();
				}
				break;
			case True:
				enterOuterAlt(_localctx, 5);
				{
				setState(319);
				match(True);
				}
				break;
			case False:
				enterOuterAlt(_localctx, 6);
				{
				setState(320);
				match(False);
				}
				break;
			case This:
				enterOuterAlt(_localctx, 7);
				{
				setState(321);
				match(This);
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
		case 21:
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
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 16);
		case 12:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001=\u0145\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"8\b\u0000\n\u0000\f\u0000;\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"E\b\u0001\n\u0001\f\u0001H\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002P\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"X\b\u0003\n\u0003\f\u0003[\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005d\b\u0005"+
		"\n\u0005\f\u0005g\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006n\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u007f\b\b\u0001\t\u0001\t\u0005"+
		"\t\u0083\b\t\n\t\f\t\u0086\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0091\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0099\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u009d\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0003\r\u00aa\b\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0003\u0011\u00b8\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0005\u0012\u00be\b\u0012\n\u0012\f\u0012\u00c1\t\u0012\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u00c5\b\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00ce"+
		"\b\u0014\n\u0014\f\u0014\u00d1\t\u0014\u0001\u0014\u0001\u0014\u0003\u0014"+
		"\u00d5\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00df\b\u0015\u0001\u0015"+
		"\u0005\u0015\u00e2\b\u0015\n\u0015\f\u0015\u00e5\t\u0015\u0001\u0015\u0003"+
		"\u0015\u00e8\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00f2\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0005\u0015\u011a\b\u0015\n\u0015\f\u0015\u011d\t\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0123\b\u0016"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0128\b\u0017\u0001\u0017"+
		"\u0004\u0017\u012b\b\u0017\u000b\u0017\f\u0017\u012c\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u0131\b\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0135"+
		"\b\u0018\n\u0018\f\u0018\u0138\t\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u0143\b\u0019\u0001\u0019\u0000\u0001*\u001a\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02\u0000\b\u0002\u0000\u0007\n77\u0004\u0000\u0018\u0018$$**"+
		",-\u0001\u0000\u0019\u001b\u0001\u0000\u0017\u0018\u0001\u0000%&\u0001"+
		"\u0000\u001c\u001f\u0001\u0000 !\u0001\u0000,-\u0167\u00009\u0001\u0000"+
		"\u0000\u0000\u0002>\u0001\u0000\u0000\u0000\u0004K\u0001\u0000\u0000\u0000"+
		"\u0006T\u0001\u0000\u0000\u0000\b\\\u0001\u0000\u0000\u0000\n_\u0001\u0000"+
		"\u0000\u0000\fj\u0001\u0000\u0000\u0000\u000eo\u0001\u0000\u0000\u0000"+
		"\u0010~\u0001\u0000\u0000\u0000\u0012\u0080\u0001\u0000\u0000\u0000\u0014"+
		"\u0089\u0001\u0000\u0000\u0000\u0016\u0092\u0001\u0000\u0000\u0000\u0018"+
		"\u00a1\u0001\u0000\u0000\u0000\u001a\u00a7\u0001\u0000\u0000\u0000\u001c"+
		"\u00ad\u0001\u0000\u0000\u0000\u001e\u00b0\u0001\u0000\u0000\u0000 \u00b3"+
		"\u0001\u0000\u0000\u0000\"\u00b7\u0001\u0000\u0000\u0000$\u00bb\u0001"+
		"\u0000\u0000\u0000&\u00c2\u0001\u0000\u0000\u0000(\u00d4\u0001\u0000\u0000"+
		"\u0000*\u00f1\u0001\u0000\u0000\u0000,\u0122\u0001\u0000\u0000\u0000."+
		"\u0124\u0001\u0000\u0000\u00000\u012e\u0001\u0000\u0000\u00002\u0142\u0001"+
		"\u0000\u0000\u000048\u0003\u0002\u0001\u000058\u0003\u0004\u0002\u0000"+
		"68\u0003\n\u0005\u000074\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u0000"+
		"76\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;9\u0001\u0000"+
		"\u0000\u0000<=\u0005\u0000\u0000\u0001=\u0001\u0001\u0000\u0000\u0000"+
		">?\u0005\f\u0000\u0000?@\u00057\u0000\u0000@F\u0005\u0001\u0000\u0000"+
		"AE\u0003\n\u0005\u0000BE\u0003\u0004\u0002\u0000CE\u0003\u000e\u0007\u0000"+
		"DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DC\u0001\u0000\u0000"+
		"\u0000EH\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000GI\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000IJ\u0005"+
		"\u0002\u0000\u0000J\u0003\u0001\u0000\u0000\u0000KL\u0003$\u0012\u0000"+
		"LM\u00057\u0000\u0000MO\u0005\u0003\u0000\u0000NP\u0003\u0006\u0003\u0000"+
		"ON\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000QR\u0005\u0004\u0000\u0000RS\u0003\u0012\t\u0000S\u0005\u0001\u0000"+
		"\u0000\u0000TY\u0003\b\u0004\u0000UV\u0005/\u0000\u0000VX\u0003\b\u0004"+
		"\u0000WU\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\u0007\u0001\u0000\u0000\u0000"+
		"[Y\u0001\u0000\u0000\u0000\\]\u0003$\u0012\u0000]^\u0003\f\u0006\u0000"+
		"^\t\u0001\u0000\u0000\u0000_`\u0003$\u0012\u0000`e\u0003\f\u0006\u0000"+
		"ab\u0005/\u0000\u0000bd\u0003\f\u0006\u0000ca\u0001\u0000\u0000\u0000"+
		"dg\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fh\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hi\u00050\u0000"+
		"\u0000i\u000b\u0001\u0000\u0000\u0000jm\u0003,\u0016\u0000kl\u0005+\u0000"+
		"\u0000ln\u0003*\u0015\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000"+
		"\u0000n\r\u0001\u0000\u0000\u0000op\u00057\u0000\u0000pq\u0005\u0003\u0000"+
		"\u0000qr\u0005\u0004\u0000\u0000rs\u0003\u0012\t\u0000s\u000f\u0001\u0000"+
		"\u0000\u0000t\u007f\u0003\u0012\t\u0000u\u007f\u0003\u0014\n\u0000v\u007f"+
		"\u0003\u0016\u000b\u0000w\u007f\u0003\u0018\f\u0000x\u007f\u0003\u001a"+
		"\r\u0000y\u007f\u0003\u001c\u000e\u0000z\u007f\u0003\u001e\u000f\u0000"+
		"{\u007f\u0003 \u0010\u0000|\u007f\u0003\n\u0005\u0000}\u007f\u00050\u0000"+
		"\u0000~t\u0001\u0000\u0000\u0000~u\u0001\u0000\u0000\u0000~v\u0001\u0000"+
		"\u0000\u0000~w\u0001\u0000\u0000\u0000~x\u0001\u0000\u0000\u0000~y\u0001"+
		"\u0000\u0000\u0000~z\u0001\u0000\u0000\u0000~{\u0001\u0000\u0000\u0000"+
		"~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0011\u0001"+
		"\u0000\u0000\u0000\u0080\u0084\u0005\u0001\u0000\u0000\u0081\u0083\u0003"+
		"\u0010\b\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000"+
		"\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0005\u0002\u0000\u0000\u0088\u0013\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005\u0010\u0000\u0000\u008a\u008b\u0005\u0003"+
		"\u0000\u0000\u008b\u008c\u0003*\u0015\u0000\u008c\u008d\u0005\u0004\u0000"+
		"\u0000\u008d\u0090\u0003\u0010\b\u0000\u008e\u008f\u0005\u0011\u0000\u0000"+
		"\u008f\u0091\u0003\u0010\b\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090"+
		"\u0091\u0001\u0000\u0000\u0000\u0091\u0015\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005\u0012\u0000\u0000\u0093\u0094\u0005\u0003\u0000\u0000\u0094"+
		"\u0095\u0003\"\u0011\u0000\u0095\u0096\u00050\u0000\u0000\u0096\u0098"+
		"\u0001\u0000\u0000\u0000\u0097\u0099\u0003*\u0015\u0000\u0098\u0097\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009a\u0001"+
		"\u0000\u0000\u0000\u009a\u009c\u00050\u0000\u0000\u009b\u009d\u0003*\u0015"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0004\u0000"+
		"\u0000\u009f\u00a0\u0003\u0010\b\u0000\u00a0\u0017\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0005\u0013\u0000\u0000\u00a2\u00a3\u0005\u0003\u0000\u0000"+
		"\u00a3\u00a4\u0003*\u0015\u0000\u00a4\u00a5\u0005\u0004\u0000\u0000\u00a5"+
		"\u00a6\u0003\u0010\b\u0000\u00a6\u0019\u0001\u0000\u0000\u0000\u00a7\u00a9"+
		"\u0005\u0016\u0000\u0000\u00a8\u00aa\u0003*\u0015\u0000\u00a9\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ac\u00050\u0000\u0000\u00ac\u001b\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ae\u0005\u0014\u0000\u0000\u00ae\u00af\u00050\u0000"+
		"\u0000\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0015\u0000"+
		"\u0000\u00b1\u00b2\u00050\u0000\u0000\u00b2\u001f\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0003*\u0015\u0000\u00b4\u00b5\u00050\u0000\u0000\u00b5!"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b8\u0003$\u0012\u0000\u00b7\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0003*\u0015\u0000\u00ba#\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bf\u0007\u0000\u0000\u0000\u00bc\u00be\u0003&\u0013\u0000"+
		"\u00bd\u00bc\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0%\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c4\u0005\u0005\u0000\u0000\u00c3\u00c5\u0003*\u0015\u0000\u00c4\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\u0006\u0000\u0000\u00c7\'\u0001"+
		"\u0000\u0000\u0000\u00c8\u00d5\u0005:\u0000\u0000\u00c9\u00ca\u0005;\u0000"+
		"\u0000\u00ca\u00cf\u0003*\u0015\u0000\u00cb\u00cc\u0005<\u0000\u0000\u00cc"+
		"\u00ce\u0003*\u0015\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00d1"+
		"\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d2\u0001\u0000\u0000\u0000\u00d1\u00cf"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005=\u0000\u0000\u00d3\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d4\u00c8\u0001\u0000\u0000\u0000\u00d4\u00c9\u0001"+
		"\u0000\u0000\u0000\u00d5)\u0001\u0000\u0000\u0000\u00d6\u00d7\u0006\u0015"+
		"\uffff\uffff\u0000\u00d7\u00d8\u0005\u0003\u0000\u0000\u00d8\u00d9\u0003"+
		"*\u0015\u0000\u00d9\u00da\u0005\u0004\u0000\u0000\u00da\u00db\u0005\u000b"+
		"\u0000\u0000\u00db\u00e3\u0003$\u0012\u0000\u00dc\u00de\u0005\u0005\u0000"+
		"\u0000\u00dd\u00df\u0003*\u0015\u0000\u00de\u00dd\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e2\u0005\u0006\u0000\u0000\u00e1\u00dc\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e5\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e6\u00e8\u0003,\u0016\u0000\u00e7"+
		"\u00e6\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8"+
		"\u00f2\u0001\u0000\u0000\u0000\u00e9\u00ea\u0007\u0001\u0000\u0000\u00ea"+
		"\u00f2\u0003*\u0015\u000e\u00eb\u00ec\u00052\u0000\u0000\u00ec\u00ed\u0003"+
		"*\u0015\u0000\u00ed\u00ee\u00051\u0000\u0000\u00ee\u00ef\u0003*\u0015"+
		"\u0003\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0\u00f2\u0003,\u0016\u0000"+
		"\u00f1\u00d6\u0001\u0000\u0000\u0000\u00f1\u00e9\u0001\u0000\u0000\u0000"+
		"\u00f1\u00eb\u0001\u0000\u0000\u0000\u00f1\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f2\u011b\u0001\u0000\u0000\u0000\u00f3\u00f4\n\r\u0000\u0000\u00f4"+
		"\u00f5\u0007\u0002\u0000\u0000\u00f5\u011a\u0003*\u0015\u000e\u00f6\u00f7"+
		"\n\f\u0000\u0000\u00f7\u00f8\u0007\u0003\u0000\u0000\u00f8\u011a\u0003"+
		"*\u0015\r\u00f9\u00fa\n\u000b\u0000\u0000\u00fa\u00fb\u0007\u0004\u0000"+
		"\u0000\u00fb\u011a\u0003*\u0015\f\u00fc\u00fd\n\n\u0000\u0000\u00fd\u00fe"+
		"\u0007\u0005\u0000\u0000\u00fe\u011a\u0003*\u0015\u000b\u00ff\u0100\n"+
		"\t\u0000\u0000\u0100\u0101\u0007\u0006\u0000\u0000\u0101\u011a\u0003*"+
		"\u0015\n\u0102\u0103\n\b\u0000\u0000\u0103\u0104\u0005\'\u0000\u0000\u0104"+
		"\u011a\u0003*\u0015\t\u0105\u0106\n\u0007\u0000\u0000\u0106\u0107\u0005"+
		")\u0000\u0000\u0107\u011a\u0003*\u0015\b\u0108\u0109\n\u0006\u0000\u0000"+
		"\u0109\u010a\u0005(\u0000\u0000\u010a\u011a\u0003*\u0015\u0007\u010b\u010c"+
		"\n\u0005\u0000\u0000\u010c\u010d\u0005\"\u0000\u0000\u010d\u011a\u0003"+
		"*\u0015\u0006\u010e\u010f\n\u0004\u0000\u0000\u010f\u0110\u0005#\u0000"+
		"\u0000\u0110\u011a\u0003*\u0015\u0005\u0111\u0112\n\u0002\u0000\u0000"+
		"\u0112\u0113\u0005+\u0000\u0000\u0113\u011a\u0003*\u0015\u0002\u0114\u0115"+
		"\n\u0010\u0000\u0000\u0115\u0116\u0005.\u0000\u0000\u0116\u011a\u0005"+
		"7\u0000\u0000\u0117\u0118\n\u000f\u0000\u0000\u0118\u011a\u0007\u0007"+
		"\u0000\u0000\u0119\u00f3\u0001\u0000\u0000\u0000\u0119\u00f6\u0001\u0000"+
		"\u0000\u0000\u0119\u00f9\u0001\u0000\u0000\u0000\u0119\u00fc\u0001\u0000"+
		"\u0000\u0000\u0119\u00ff\u0001\u0000\u0000\u0000\u0119\u0102\u0001\u0000"+
		"\u0000\u0000\u0119\u0105\u0001\u0000\u0000\u0000\u0119\u0108\u0001\u0000"+
		"\u0000\u0000\u0119\u010b\u0001\u0000\u0000\u0000\u0119\u010e\u0001\u0000"+
		"\u0000\u0000\u0119\u0111\u0001\u0000\u0000\u0000\u0119\u0114\u0001\u0000"+
		"\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a\u011d\u0001\u0000"+
		"\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000"+
		"\u0000\u0000\u011c+\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000"+
		"\u0000\u011e\u0123\u0003.\u0017\u0000\u011f\u0123\u00057\u0000\u0000\u0120"+
		"\u0123\u00032\u0019\u0000\u0121\u0123\u0003(\u0014\u0000\u0122\u011e\u0001"+
		"\u0000\u0000\u0000\u0122\u011f\u0001\u0000\u0000\u0000\u0122\u0120\u0001"+
		"\u0000\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0123-\u0001\u0000"+
		"\u0000\u0000\u0124\u012a\u00057\u0000\u0000\u0125\u0127\u0005\u0005\u0000"+
		"\u0000\u0126\u0128\u0003*\u0015\u0000\u0127\u0126\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000"+
		"\u0129\u012b\u0005\u0006\u0000\u0000\u012a\u0125\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012a\u0001\u0000\u0000\u0000"+
		"\u012c\u012d\u0001\u0000\u0000\u0000\u012d/\u0001\u0000\u0000\u0000\u012e"+
		"\u0130\u0005\u0001\u0000\u0000\u012f\u0131\u00032\u0019\u0000\u0130\u012f"+
		"\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u0136"+
		"\u0001\u0000\u0000\u0000\u0132\u0133\u0005/\u0000\u0000\u0133\u0135\u0003"+
		"2\u0019\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0138\u0001\u0000"+
		"\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000"+
		"\u0000\u0000\u0137\u0139\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000"+
		"\u0000\u0000\u0139\u013a\u0005\u0002\u0000\u0000\u013a1\u0001\u0000\u0000"+
		"\u0000\u013b\u0143\u00056\u0000\u0000\u013c\u0143\u00057\u0000\u0000\u013d"+
		"\u0143\u00059\u0000\u0000\u013e\u0143\u00030\u0018\u0000\u013f\u0143\u0005"+
		"\r\u0000\u0000\u0140\u0143\u0005\u000e\u0000\u0000\u0141\u0143\u0005\u000f"+
		"\u0000\u0000\u0142\u013b\u0001\u0000\u0000\u0000\u0142\u013c\u0001\u0000"+
		"\u0000\u0000\u0142\u013d\u0001\u0000\u0000\u0000\u0142\u013e\u0001\u0000"+
		"\u0000\u0000\u0142\u013f\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000"+
		"\u0000\u0000\u0142\u0141\u0001\u0000\u0000\u0000\u01433\u0001\u0000\u0000"+
		"\u0000\u001f79DFOYem~\u0084\u0090\u0098\u009c\u00a9\u00b7\u00bf\u00c4"+
		"\u00cf\u00d4\u00de\u00e3\u00e7\u00f1\u0119\u011b\u0122\u0127\u012c\u0130"+
		"\u0136\u0142";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}