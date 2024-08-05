// Generated from /mnt/d/周宸源/大学/学习/程序/MxCompiler/src/Grammar/Mxparser.g4 by ANTLR 4.13.1
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		Void=10, Null=11, Bool=12, Int=13, String=14, New=15, Class=16, True=17, 
		False=18, This=19, If=20, Else=21, For=22, While=23, Break=24, Continue=25, 
		Return=26, Plus=27, Minus=28, Multiply=29, Divide=30, Mod=31, Greater=32, 
		Less=33, GreaterEqual=34, LessEqual=35, Equal=36, InEqual=37, And=38, 
		Or=39, Not=40, LeftMove=41, RightMove=42, AndBit=43, OrBit=44, XorBit=45, 
		NotBit=46, Assign=47, SelfPlus=48, SelfMinus=49, Member=50, Comma=51, 
		Semicolon=52, Comment=53, BlockComment=54, Whitespace=55, Interger=56, 
		Identifier=57, ConstString=58, FormatStringI=59, FormatStringL=60, FormatStringM=61, 
		FormatStringR=62;
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
			null, "'{'", "'}'", "'('", "')'", "'()'", "'['", "']'", "'?'", "':'", 
			"'void'", "'null'", "'bool'", "'int'", "'string'", "'new'", "'class'", 
			"'true'", "'false'", "'this'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", 
			"'<<'", "'>>'", "'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "'.'", 
			"','", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "Void", "Null", 
			"Bool", "Int", "String", "New", "Class", "True", "False", "This", "If", 
			"Else", "For", "While", "Break", "Continue", "Return", "Plus", "Minus", 
			"Multiply", "Divide", "Mod", "Greater", "Less", "GreaterEqual", "LessEqual", 
			"Equal", "InEqual", "And", "Or", "Not", "LeftMove", "RightMove", "AndBit", 
			"OrBit", "XorBit", "NotBit", "Assign", "SelfPlus", "SelfMinus", "Member", 
			"Comma", "Semicolon", "Comment", "BlockComment", "Whitespace", "Interger", 
			"Identifier", "ConstString", "FormatStringI", "FormatStringL", "FormatStringM", 
			"FormatStringR"
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 144115188075951104L) != 0)) {
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 144115188075885568L) != 0)) {
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
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(80);
				match(T__2);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 144115188075885568L) != 0)) {
					{
					setState(81);
					parameterDeclarationList();
					}
				}

				setState(84);
				match(T__3);
				}
				break;
			case T__4:
				{
				setState(85);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(88);
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
			setState(90);
			parameterDeclaration();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(91);
				match(Comma);
				setState(92);
				parameterDeclaration();
				}
				}
				setState(97);
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
			setState(98);
			type();
			setState(99);
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
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
				{
				setState(101);
				expression(0);
				}
			}

			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(104);
				match(Comma);
				setState(105);
				expression(0);
				}
				}
				setState(110);
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
			setState(111);
			type();
			setState(112);
			atomVariableDeclaration();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(113);
				match(Comma);
				setState(114);
				atomVariableDeclaration();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(120);
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
			setState(122);
			atom();
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(123);
				match(Assign);
				setState(124);
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
			setState(127);
			match(Identifier);
			setState(128);
			match(T__4);
			setState(129);
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
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends StatementContext {
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends StatementContext {
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends StatementContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends StatementContext {
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionStmtContext extends StatementContext {
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public ExpressionStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semicolon() { return getToken(MxparserParser.Semicolon, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends StatementContext {
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends StatementContext {
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationStmtContext extends StatementContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public VariableDeclarationStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				ifStatement();
				}
				break;
			case 2:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				block();
				}
				break;
			case 3:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				forStatement();
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(134);
				whileStatement();
				}
				break;
			case 5:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(135);
				returnStatement();
				}
				break;
			case 6:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(136);
				breakStatement();
				}
				break;
			case 7:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(137);
				continueStatement();
				}
				break;
			case 8:
				_localctx = new ExpressionStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(138);
				expressionStatement();
				}
				break;
			case 9:
				_localctx = new VariableDeclarationStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(139);
				variableDeclaration();
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(140);
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
			setState(143);
			match(T__0);
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2239204908389563402L) != 0)) {
				{
				{
				setState(144);
				statement();
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(150);
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
			setState(152);
			match(If);
			setState(153);
			match(T__2);
			setState(154);
			expression(0);
			setState(155);
			match(T__3);
			{
			setState(156);
			statement();
			}
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(157);
				match(Else);
				setState(158);
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
			setState(161);
			match(For);
			setState(162);
			match(T__2);
			{
			setState(163);
			statement();
			}
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
				{
				setState(164);
				expression(0);
				}
			}

			setState(167);
			match(Semicolon);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
				{
				setState(168);
				expression(0);
				}
			}

			setState(171);
			match(T__3);
			setState(172);
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
			setState(174);
			match(While);
			setState(175);
			match(T__2);
			setState(176);
			expression(0);
			setState(177);
			match(T__3);
			setState(178);
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
			setState(180);
			match(Return);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
				{
				setState(181);
				expression(0);
				}
			}

			setState(184);
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
			setState(186);
			match(Break);
			setState(187);
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
			setState(189);
			match(Continue);
			setState(190);
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
			setState(192);
			expression(0);
			setState(193);
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
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(195);
				type();
				}
				break;
			}
			setState(198);
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
		public Token typ;
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
			setState(200);
			((TypeContext)_localctx).typ = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 144115188075885568L) != 0)) ) {
				((TypeContext)_localctx).typ = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(204);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(201);
					arrayLable();
					}
					} 
				}
				setState(206);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
			setState(207);
			match(T__5);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
				{
				setState(208);
				expression(0);
				}
			}

			setState(211);
			match(T__6);
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
		public TerminalNode FormatStringR() { return getToken(MxparserParser.FormatStringR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
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
			setState(228);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FormatStringI:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				match(FormatStringI);
				}
				break;
			case FormatStringL:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(214);
				match(FormatStringL);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
					{
					setState(215);
					expression(0);
					}
				}

				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FormatStringM) {
					{
					{
					setState(218);
					match(FormatStringM);
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
						{
						setState(219);
						expression(0);
						}
					}

					}
					}
					setState(226);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(227);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxparserParser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SelfOpExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxparserParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxparserParser.SelfMinus, 0); }
		public SelfOpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TernaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArrayExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Member() { return getToken(MxparserParser.Member, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public MemberExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExprContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Multiply() { return getToken(MxparserParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(MxparserParser.Divide, 0); }
		public TerminalNode Mod() { return getToken(MxparserParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(MxparserParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxparserParser.Minus, 0); }
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
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FormatStringExprContext extends ExpressionContext {
		public FormatStringElementContext formatStringElement() {
			return getRuleContext(FormatStringElementContext.class,0);
		}
		public FormatStringExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ChildExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ChildExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public CallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxparserParser.Assign, 0); }
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreOpExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxparserParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxparserParser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(MxparserParser.Not, 0); }
		public TerminalNode Minus() { return getToken(MxparserParser.Minus, 0); }
		public TerminalNode NotBit() { return getToken(MxparserParser.NotBit, 0); }
		public PreOpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
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
			setState(254);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				_localctx = new ChildExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(231);
				match(T__2);
				setState(232);
				expression(0);
				setState(233);
				match(T__3);
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235);
				match(New);
				setState(236);
				type();
				setState(244);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(237);
						match(T__5);
						setState(239);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
							{
							setState(238);
							expression(0);
							}
						}

						setState(241);
						match(T__6);
						}
						} 
					}
					setState(246);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				setState(248);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(247);
					match(T__4);
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
				_localctx = new PreOpExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(250);
				((PreOpExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 915893454372864L) != 0)) ) {
					((PreOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(251);
				expression(15);
				}
				break;
			case FormatStringI:
			case FormatStringL:
				{
				_localctx = new FormatStringExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(252);
				formatStringElement();
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
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(253);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(317);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(257);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3758096384L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(258);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(260);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(261);
						expression(14);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(262);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(263);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftMove || _la==RightMove) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(264);
						expression(13);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(265);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(266);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 64424509440L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(267);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(268);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(269);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==InEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(270);
						expression(11);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(272);
						((BinaryExprContext)_localctx).op = match(AndBit);
						setState(273);
						expression(10);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(275);
						((BinaryExprContext)_localctx).op = match(XorBit);
						setState(276);
						expression(9);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(278);
						((BinaryExprContext)_localctx).op = match(OrBit);
						setState(279);
						expression(8);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(280);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(281);
						((BinaryExprContext)_localctx).op = match(And);
						setState(282);
						expression(7);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(283);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(284);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(285);
						expression(6);
						}
						break;
					case 11:
						{
						_localctx = new TernaryExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(286);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(287);
						match(T__7);
						setState(288);
						expression(0);
						setState(289);
						match(T__8);
						setState(290);
						expression(4);
						}
						break;
					case 12:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(292);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(293);
						match(Assign);
						}
						setState(294);
						expression(3);
						}
						break;
					case 13:
						{
						_localctx = new MemberExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(295);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(296);
						match(Member);
						setState(297);
						atom();
						}
						break;
					case 14:
						{
						_localctx = new CallExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(298);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(304);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__2:
							{
							{
							setState(299);
							match(T__2);
							setState(300);
							parameterList();
							setState(301);
							match(T__3);
							}
							}
							break;
						case T__4:
							{
							setState(303);
							match(T__4);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 15:
						{
						_localctx = new ArrayExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(306);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						{
						setState(311); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(307);
								match(T__5);
								setState(308);
								expression(0);
								setState(309);
								match(T__6);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(313); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						}
						break;
					case 16:
						{
						_localctx = new SelfOpExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(315);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(316);
						((SelfOpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
							((SelfOpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
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
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	 
		public AtomContext() { }
		public void copyFrom(AtomContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdAtomContext extends AtomContext {
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public IdAtomContext(AtomContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAtomContext extends AtomContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayAtomContext(AtomContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstAtomContext extends AtomContext {
		public ConstElementContext constElement() {
			return getRuleContext(ConstElementContext.class,0);
		}
		public ConstAtomContext(AtomContext ctx) { copyFrom(ctx); }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_atom);
		try {
			setState(325);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new ArrayAtomContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(322);
				array();
				}
				break;
			case 2:
				_localctx = new IdAtomContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new ConstAtomContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(324);
				constElement();
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
			setState(327);
			match(Identifier);
			setState(333); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(328);
					match(T__5);
					setState(330);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2234701308631091210L) != 0)) {
						{
						setState(329);
						expression(0);
						}
					}

					setState(332);
					match(T__6);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(335); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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
			setState(337);
			match(T__0);
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 504403158266415106L) != 0)) {
				{
				setState(338);
				constElement();
				}
			}

			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(341);
				match(Comma);
				setState(342);
				constElement();
				}
				}
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(348);
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
		public ConstElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constElement; }
	 
		public ConstElementContext() { }
		public void copyFrom(ConstElementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstArrayElementContext extends ConstElementContext {
		public ConstArrayContext constArray() {
			return getRuleContext(ConstArrayContext.class,0);
		}
		public ConstArrayElementContext(ConstElementContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstAtomElementContext extends ConstElementContext {
		public Token atomElement;
		public TerminalNode Interger() { return getToken(MxparserParser.Interger, 0); }
		public TerminalNode Identifier() { return getToken(MxparserParser.Identifier, 0); }
		public TerminalNode ConstString() { return getToken(MxparserParser.ConstString, 0); }
		public TerminalNode True() { return getToken(MxparserParser.True, 0); }
		public TerminalNode False() { return getToken(MxparserParser.False, 0); }
		public TerminalNode This() { return getToken(MxparserParser.This, 0); }
		public TerminalNode Null() { return getToken(MxparserParser.Null, 0); }
		public ConstAtomElementContext(ConstElementContext ctx) { copyFrom(ctx); }
	}

	public final ConstElementContext constElement() throws RecognitionException {
		ConstElementContext _localctx = new ConstElementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constElement);
		int _la;
		try {
			setState(352);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case True:
			case False:
			case This:
			case Interger:
			case Identifier:
			case ConstString:
				_localctx = new ConstAtomElementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				((ConstAtomElementContext)_localctx).atomElement = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 504403158266415104L) != 0)) ) {
					((ConstAtomElementContext)_localctx).atomElement = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__0:
				_localctx = new ConstArrayElementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				constArray();
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
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 7);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 17);
		case 15:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u0163\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\u0003\u0002S\b\u0002\u0001\u0002\u0001\u0002\u0003\u0002W\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003^\b"+
		"\u0003\n\u0003\f\u0003a\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0003\u0005g\b\u0005\u0001\u0005\u0001\u0005\u0005\u0005k\b\u0005"+
		"\n\u0005\f\u0005n\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006t\b\u0006\n\u0006\f\u0006w\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007~\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u008e\b\t\u0001\n\u0001\n\u0005\n\u0092"+
		"\b\n\n\n\f\n\u0095\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00a6\b\f\u0001\f\u0001\f\u0003"+
		"\f\u00aa\b\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000e\u00b7\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0003\u0012"+
		"\u00c5\b\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u00cb\b\u0013\n\u0013\f\u0013\u00ce\t\u0013\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u00d2\b\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u00d9\b\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00dd"+
		"\b\u0015\u0005\u0015\u00df\b\u0015\n\u0015\f\u0015\u00e2\t\u0015\u0001"+
		"\u0015\u0003\u0015\u00e5\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u00f0\b\u0016\u0001\u0016\u0005\u0016\u00f3\b\u0016\n\u0016\f\u0016"+
		"\u00f6\t\u0016\u0001\u0016\u0003\u0016\u00f9\b\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u00ff\b\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0131"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0004"+
		"\u0016\u0138\b\u0016\u000b\u0016\f\u0016\u0139\u0001\u0016\u0001\u0016"+
		"\u0005\u0016\u013e\b\u0016\n\u0016\f\u0016\u0141\t\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u0146\b\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u014b\b\u0018\u0001\u0018\u0004\u0018\u014e\b\u0018"+
		"\u000b\u0018\f\u0018\u014f\u0001\u0019\u0001\u0019\u0003\u0019\u0154\b"+
		"\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0158\b\u0019\n\u0019\f\u0019"+
		"\u015b\t\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0003\u001a"+
		"\u0161\b\u001a\u0001\u001a\u0000\u0001,\u001b\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,."+
		"024\u0000\t\u0003\u0000\n\n\f\u000e99\u0004\u0000\u001c\u001c((..01\u0001"+
		"\u0000\u001d\u001f\u0001\u0000\u001b\u001c\u0001\u0000)*\u0001\u0000 "+
		"#\u0001\u0000$%\u0001\u000001\u0003\u0000\u000b\u000b\u0011\u00138:\u0189"+
		"\u0000;\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004N"+
		"\u0001\u0000\u0000\u0000\u0006Z\u0001\u0000\u0000\u0000\bb\u0001\u0000"+
		"\u0000\u0000\nf\u0001\u0000\u0000\u0000\fo\u0001\u0000\u0000\u0000\u000e"+
		"z\u0001\u0000\u0000\u0000\u0010\u007f\u0001\u0000\u0000\u0000\u0012\u008d"+
		"\u0001\u0000\u0000\u0000\u0014\u008f\u0001\u0000\u0000\u0000\u0016\u0098"+
		"\u0001\u0000\u0000\u0000\u0018\u00a1\u0001\u0000\u0000\u0000\u001a\u00ae"+
		"\u0001\u0000\u0000\u0000\u001c\u00b4\u0001\u0000\u0000\u0000\u001e\u00ba"+
		"\u0001\u0000\u0000\u0000 \u00bd\u0001\u0000\u0000\u0000\"\u00c0\u0001"+
		"\u0000\u0000\u0000$\u00c4\u0001\u0000\u0000\u0000&\u00c8\u0001\u0000\u0000"+
		"\u0000(\u00cf\u0001\u0000\u0000\u0000*\u00e4\u0001\u0000\u0000\u0000,"+
		"\u00fe\u0001\u0000\u0000\u0000.\u0145\u0001\u0000\u0000\u00000\u0147\u0001"+
		"\u0000\u0000\u00002\u0151\u0001\u0000\u0000\u00004\u0160\u0001\u0000\u0000"+
		"\u00006:\u0003\u0002\u0001\u00007:\u0003\u0004\u0002\u00008:\u0003\f\u0006"+
		"\u000096\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u000098\u0001\u0000"+
		"\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001"+
		"\u0000\u0000\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000"+
		">?\u0005\u0000\u0000\u0001?\u0001\u0001\u0000\u0000\u0000@A\u0005\u0010"+
		"\u0000\u0000AB\u00059\u0000\u0000BH\u0005\u0001\u0000\u0000CG\u0003\f"+
		"\u0006\u0000DG\u0003\u0004\u0002\u0000EG\u0003\u0010\b\u0000FC\u0001\u0000"+
		"\u0000\u0000FD\u0001\u0000\u0000\u0000FE\u0001\u0000\u0000\u0000GJ\u0001"+
		"\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000"+
		"IK\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KL\u0005\u0002\u0000"+
		"\u0000LM\u00054\u0000\u0000M\u0003\u0001\u0000\u0000\u0000NO\u0003&\u0013"+
		"\u0000OV\u00059\u0000\u0000PR\u0005\u0003\u0000\u0000QS\u0003\u0006\u0003"+
		"\u0000RQ\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000"+
		"\u0000\u0000TW\u0005\u0004\u0000\u0000UW\u0005\u0005\u0000\u0000VP\u0001"+
		"\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XY\u0003\u0014\n\u0000Y\u0005\u0001\u0000\u0000\u0000Z_\u0003\b\u0004"+
		"\u0000[\\\u00053\u0000\u0000\\^\u0003\b\u0004\u0000][\u0001\u0000\u0000"+
		"\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`\u0007\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000"+
		"bc\u0003&\u0013\u0000cd\u0003\u000e\u0007\u0000d\t\u0001\u0000\u0000\u0000"+
		"eg\u0003,\u0016\u0000fe\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"gl\u0001\u0000\u0000\u0000hi\u00053\u0000\u0000ik\u0003,\u0016\u0000j"+
		"h\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000m\u000b\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000op\u0003&\u0013\u0000pu\u0003\u000e\u0007\u0000qr\u0005"+
		"3\u0000\u0000rt\u0003\u000e\u0007\u0000sq\u0001\u0000\u0000\u0000tw\u0001"+
		"\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"vx\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000xy\u00054\u0000\u0000"+
		"y\r\u0001\u0000\u0000\u0000z}\u0003.\u0017\u0000{|\u0005/\u0000\u0000"+
		"|~\u0003,\u0016\u0000}{\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000"+
		"~\u000f\u0001\u0000\u0000\u0000\u007f\u0080\u00059\u0000\u0000\u0080\u0081"+
		"\u0005\u0005\u0000\u0000\u0081\u0082\u0003\u0014\n\u0000\u0082\u0011\u0001"+
		"\u0000\u0000\u0000\u0083\u008e\u0003\u0016\u000b\u0000\u0084\u008e\u0003"+
		"\u0014\n\u0000\u0085\u008e\u0003\u0018\f\u0000\u0086\u008e\u0003\u001a"+
		"\r\u0000\u0087\u008e\u0003\u001c\u000e\u0000\u0088\u008e\u0003\u001e\u000f"+
		"\u0000\u0089\u008e\u0003 \u0010\u0000\u008a\u008e\u0003\"\u0011\u0000"+
		"\u008b\u008e\u0003\f\u0006\u0000\u008c\u008e\u00054\u0000\u0000\u008d"+
		"\u0083\u0001\u0000\u0000\u0000\u008d\u0084\u0001\u0000\u0000\u0000\u008d"+
		"\u0085\u0001\u0000\u0000\u0000\u008d\u0086\u0001\u0000\u0000\u0000\u008d"+
		"\u0087\u0001\u0000\u0000\u0000\u008d\u0088\u0001\u0000\u0000\u0000\u008d"+
		"\u0089\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008d"+
		"\u008b\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e"+
		"\u0013\u0001\u0000\u0000\u0000\u008f\u0093\u0005\u0001\u0000\u0000\u0090"+
		"\u0092\u0003\u0012\t\u0000\u0091\u0090\u0001\u0000\u0000\u0000\u0092\u0095"+
		"\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0001\u0000\u0000\u0000\u0094\u0096\u0001\u0000\u0000\u0000\u0095\u0093"+
		"\u0001\u0000\u0000\u0000\u0096\u0097\u0005\u0002\u0000\u0000\u0097\u0015"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0014\u0000\u0000\u0099\u009a"+
		"\u0005\u0003\u0000\u0000\u009a\u009b\u0003,\u0016\u0000\u009b\u009c\u0005"+
		"\u0004\u0000\u0000\u009c\u009f\u0003\u0012\t\u0000\u009d\u009e\u0005\u0015"+
		"\u0000\u0000\u009e\u00a0\u0003\u0012\t\u0000\u009f\u009d\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u0017\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0005\u0016\u0000\u0000\u00a2\u00a3\u0005\u0003\u0000"+
		"\u0000\u00a3\u00a5\u0003\u0012\t\u0000\u00a4\u00a6\u0003,\u0016\u0000"+
		"\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a9\u00054\u0000\u0000\u00a8"+
		"\u00aa\u0003,\u0016\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0005\u0004\u0000\u0000\u00ac\u00ad\u0003\u0012\t\u0000\u00ad\u0019\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0005\u0017\u0000\u0000\u00af\u00b0\u0005"+
		"\u0003\u0000\u0000\u00b0\u00b1\u0003,\u0016\u0000\u00b1\u00b2\u0005\u0004"+
		"\u0000\u0000\u00b2\u00b3\u0003\u0012\t\u0000\u00b3\u001b\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b6\u0005\u001a\u0000\u0000\u00b5\u00b7\u0003,\u0016\u0000"+
		"\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u00054\u0000\u0000\u00b9"+
		"\u001d\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0018\u0000\u0000\u00bb"+
		"\u00bc\u00054\u0000\u0000\u00bc\u001f\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0005\u0019\u0000\u0000\u00be\u00bf\u00054\u0000\u0000\u00bf!\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0003,\u0016\u0000\u00c1\u00c2\u00054\u0000\u0000"+
		"\u00c2#\u0001\u0000\u0000\u0000\u00c3\u00c5\u0003&\u0013\u0000\u00c4\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c7\u0003\n\u0005\u0000\u00c7%\u0001"+
		"\u0000\u0000\u0000\u00c8\u00cc\u0007\u0000\u0000\u0000\u00c9\u00cb\u0003"+
		"(\u0014\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\'\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cf\u00d1\u0005\u0006\u0000\u0000\u00d0\u00d2\u0003,\u0016\u0000"+
		"\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005\u0007\u0000\u0000"+
		"\u00d4)\u0001\u0000\u0000\u0000\u00d5\u00e5\u0005;\u0000\u0000\u00d6\u00d8"+
		"\u0005<\u0000\u0000\u00d7\u00d9\u0003,\u0016\u0000\u00d8\u00d7\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00e0\u0001\u0000"+
		"\u0000\u0000\u00da\u00dc\u0005=\u0000\u0000\u00db\u00dd\u0003,\u0016\u0000"+
		"\u00dc\u00db\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dd\u00df\u0001\u0000\u0000\u0000\u00de\u00da\u0001\u0000\u0000\u0000"+
		"\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e5\u0005>\u0000\u0000\u00e4"+
		"\u00d5\u0001\u0000\u0000\u0000\u00e4\u00d6\u0001\u0000\u0000\u0000\u00e5"+
		"+\u0001\u0000\u0000\u0000\u00e6\u00e7\u0006\u0016\uffff\uffff\u0000\u00e7"+
		"\u00e8\u0005\u0003\u0000\u0000\u00e8\u00e9\u0003,\u0016\u0000\u00e9\u00ea"+
		"\u0005\u0004\u0000\u0000\u00ea\u00ff\u0001\u0000\u0000\u0000\u00eb\u00ec"+
		"\u0005\u000f\u0000\u0000\u00ec\u00f4\u0003&\u0013\u0000\u00ed\u00ef\u0005"+
		"\u0006\u0000\u0000\u00ee\u00f0\u0003,\u0016\u0000\u00ef\u00ee\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f3\u0005\u0007\u0000\u0000\u00f2\u00ed\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f8\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f9\u0005\u0005"+
		"\u0000\u0000\u00f8\u00f7\u0001\u0000\u0000\u0000\u00f8\u00f9\u0001\u0000"+
		"\u0000\u0000\u00f9\u00ff\u0001\u0000\u0000\u0000\u00fa\u00fb\u0007\u0001"+
		"\u0000\u0000\u00fb\u00ff\u0003,\u0016\u000f\u00fc\u00ff\u0003*\u0015\u0000"+
		"\u00fd\u00ff\u0003.\u0017\u0000\u00fe\u00e6\u0001\u0000\u0000\u0000\u00fe"+
		"\u00eb\u0001\u0000\u0000\u0000\u00fe\u00fa\u0001\u0000\u0000\u0000\u00fe"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fe\u00fd\u0001\u0000\u0000\u0000\u00ff"+
		"\u013f\u0001\u0000\u0000\u0000\u0100\u0101\n\u000e\u0000\u0000\u0101\u0102"+
		"\u0007\u0002\u0000\u0000\u0102\u013e\u0003,\u0016\u000f\u0103\u0104\n"+
		"\r\u0000\u0000\u0104\u0105\u0007\u0003\u0000\u0000\u0105\u013e\u0003,"+
		"\u0016\u000e\u0106\u0107\n\f\u0000\u0000\u0107\u0108\u0007\u0004\u0000"+
		"\u0000\u0108\u013e\u0003,\u0016\r\u0109\u010a\n\u000b\u0000\u0000\u010a"+
		"\u010b\u0007\u0005\u0000\u0000\u010b\u013e\u0003,\u0016\f\u010c\u010d"+
		"\n\n\u0000\u0000\u010d\u010e\u0007\u0006\u0000\u0000\u010e\u013e\u0003"+
		",\u0016\u000b\u010f\u0110\n\t\u0000\u0000\u0110\u0111\u0005+\u0000\u0000"+
		"\u0111\u013e\u0003,\u0016\n\u0112\u0113\n\b\u0000\u0000\u0113\u0114\u0005"+
		"-\u0000\u0000\u0114\u013e\u0003,\u0016\t\u0115\u0116\n\u0007\u0000\u0000"+
		"\u0116\u0117\u0005,\u0000\u0000\u0117\u013e\u0003,\u0016\b\u0118\u0119"+
		"\n\u0006\u0000\u0000\u0119\u011a\u0005&\u0000\u0000\u011a\u013e\u0003"+
		",\u0016\u0007\u011b\u011c\n\u0005\u0000\u0000\u011c\u011d\u0005\'\u0000"+
		"\u0000\u011d\u013e\u0003,\u0016\u0006\u011e\u011f\n\u0004\u0000\u0000"+
		"\u011f\u0120\u0005\b\u0000\u0000\u0120\u0121\u0003,\u0016\u0000\u0121"+
		"\u0122\u0005\t\u0000\u0000\u0122\u0123\u0003,\u0016\u0004\u0123\u013e"+
		"\u0001\u0000\u0000\u0000\u0124\u0125\n\u0003\u0000\u0000\u0125\u0126\u0005"+
		"/\u0000\u0000\u0126\u013e\u0003,\u0016\u0003\u0127\u0128\n\u0013\u0000"+
		"\u0000\u0128\u0129\u00052\u0000\u0000\u0129\u013e\u0003.\u0017\u0000\u012a"+
		"\u0130\n\u0012\u0000\u0000\u012b\u012c\u0005\u0003\u0000\u0000\u012c\u012d"+
		"\u0003\n\u0005\u0000\u012d\u012e\u0005\u0004\u0000\u0000\u012e\u0131\u0001"+
		"\u0000\u0000\u0000\u012f\u0131\u0005\u0005\u0000\u0000\u0130\u012b\u0001"+
		"\u0000\u0000\u0000\u0130\u012f\u0001\u0000\u0000\u0000\u0131\u013e\u0001"+
		"\u0000\u0000\u0000\u0132\u0137\n\u0011\u0000\u0000\u0133\u0134\u0005\u0006"+
		"\u0000\u0000\u0134\u0135\u0003,\u0016\u0000\u0135\u0136\u0005\u0007\u0000"+
		"\u0000\u0136\u0138\u0001\u0000\u0000\u0000\u0137\u0133\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000"+
		"\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a\u013e\u0001\u0000\u0000"+
		"\u0000\u013b\u013c\n\u0010\u0000\u0000\u013c\u013e\u0007\u0007\u0000\u0000"+
		"\u013d\u0100\u0001\u0000\u0000\u0000\u013d\u0103\u0001\u0000\u0000\u0000"+
		"\u013d\u0106\u0001\u0000\u0000\u0000\u013d\u0109\u0001\u0000\u0000\u0000"+
		"\u013d\u010c\u0001\u0000\u0000\u0000\u013d\u010f\u0001\u0000\u0000\u0000"+
		"\u013d\u0112\u0001\u0000\u0000\u0000\u013d\u0115\u0001\u0000\u0000\u0000"+
		"\u013d\u0118\u0001\u0000\u0000\u0000\u013d\u011b\u0001\u0000\u0000\u0000"+
		"\u013d\u011e\u0001\u0000\u0000\u0000\u013d\u0124\u0001\u0000\u0000\u0000"+
		"\u013d\u0127\u0001\u0000\u0000\u0000\u013d\u012a\u0001\u0000\u0000\u0000"+
		"\u013d\u0132\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000\u0000\u0000"+
		"\u013e\u0141\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000"+
		"\u013f\u0140\u0001\u0000\u0000\u0000\u0140-\u0001\u0000\u0000\u0000\u0141"+
		"\u013f\u0001\u0000\u0000\u0000\u0142\u0146\u00030\u0018\u0000\u0143\u0146"+
		"\u00059\u0000\u0000\u0144\u0146\u00034\u001a\u0000\u0145\u0142\u0001\u0000"+
		"\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0145\u0144\u0001\u0000"+
		"\u0000\u0000\u0146/\u0001\u0000\u0000\u0000\u0147\u014d\u00059\u0000\u0000"+
		"\u0148\u014a\u0005\u0006\u0000\u0000\u0149\u014b\u0003,\u0016\u0000\u014a"+
		"\u0149\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b"+
		"\u014c\u0001\u0000\u0000\u0000\u014c\u014e\u0005\u0007\u0000\u0000\u014d"+
		"\u0148\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f"+
		"\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150"+
		"1\u0001\u0000\u0000\u0000\u0151\u0153\u0005\u0001\u0000\u0000\u0152\u0154"+
		"\u00034\u001a\u0000\u0153\u0152\u0001\u0000\u0000\u0000\u0153\u0154\u0001"+
		"\u0000\u0000\u0000\u0154\u0159\u0001\u0000\u0000\u0000\u0155\u0156\u0005"+
		"3\u0000\u0000\u0156\u0158\u00034\u001a\u0000\u0157\u0155\u0001\u0000\u0000"+
		"\u0000\u0158\u015b\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000\u0000"+
		"\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015c\u0001\u0000\u0000"+
		"\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015c\u015d\u0005\u0002\u0000"+
		"\u0000\u015d3\u0001\u0000\u0000\u0000\u015e\u0161\u0007\b\u0000\u0000"+
		"\u015f\u0161\u00032\u0019\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0160"+
		"\u015f\u0001\u0000\u0000\u0000\u01615\u0001\u0000\u0000\u0000&9;FHRV_"+
		"flu}\u008d\u0093\u009f\u00a5\u00a9\u00b6\u00c4\u00cc\u00d1\u00d8\u00dc"+
		"\u00e0\u00e4\u00ef\u00f4\u00f8\u00fe\u0130\u0139\u013d\u013f\u0145\u014a"+
		"\u014f\u0153\u0159\u0160";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}