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
public class Mxparser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Void=1, Bool=2, Int=3, String=4, New=5, Class=6, True=7, False=8, This=9, 
		If=10, Else=11, For=12, While=13, Break=14, Continue=15, Return=16, SmallLetter=17, 
		CapitalLetter=18, Number=19, Interger=20, Identifier=21, ConstString=22, 
		F_PREFIX=23, QUOTE=24, FormatChar=25, ConstArray=26, ConstElement=27, 
		Comment=28, BlockComment=29, Whitespace=30, Plus=31, Minus=32, Multiply=33, 
		Divide=34, Mod=35, Greater=36, Less=37, GreaterEqual=38, LessEqual=39, 
		Equal=40, InEqual=41, And=42, Or=43, Not=44, LeftMove=45, RightMove=46, 
		AndBit=47, OrBit=48, XorBit=49, NotBit=50, Assign=51, AddAssign=52, SubAssign=53, 
		MulAssign=54, DivAssign=55, ModAssign=56, LeftMoveAssign=57, RightMoveAssign=58, 
		AndAssign=59, OrAssign=60, XorAssign=61, AssignType=62, SelfPlus=63, SelfMinus=64, 
		Member=65, LeftBracket=66, RightBracket=67, LeftSquareBracket=68, RightSquareBracket=69, 
		LeftBrace=70, RightBrace=71, Comma=72, Semicolon=73, Colon=74, QestionMark=75, 
		DoubleLeftBrace=76, DoubleRightBrace=77;
	public static final int
		RULE_program = 0, RULE_classDeclaration = 1, RULE_functionDeclaration = 2, 
		RULE_parameterList = 3, RULE_parameter = 4, RULE_variableDeclaration = 5, 
		RULE_atomVariableDeclaration = 6, RULE_classConstructor = 7, RULE_statement = 8, 
		RULE_block = 9, RULE_ifStatement = 10, RULE_forStatement = 11, RULE_whileStatement = 12, 
		RULE_returnStatement = 13, RULE_breakStatement = 14, RULE_continueStatement = 15, 
		RULE_expressionStatement = 16, RULE_initalstatement = 17, RULE_type = 18, 
		RULE_formattedStringExpression = 19, RULE_expression = 20, RULE_atom = 21, 
		RULE_array = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "classDeclaration", "functionDeclaration", "parameterList", 
			"parameter", "variableDeclaration", "atomVariableDeclaration", "classConstructor", 
			"statement", "block", "ifStatement", "forStatement", "whileStatement", 
			"returnStatement", "breakStatement", "continueStatement", "expressionStatement", 
			"initalstatement", "type", "formattedStringExpression", "expression", 
			"atom", "array"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'void'", "'bool'", "'int'", "'string'", "'new'", "'class'", "'true'", 
			"'false'", "'this'", "'if'", "'else'", "'for'", "'while'", "'break'", 
			"'continue'", "'return'", null, null, null, null, null, null, "'f'", 
			"'\"'", null, null, null, null, null, null, "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'&&'", "'||'", 
			"'!'", "'<<'", "'>>'", "'&'", "'|'", "'^'", "'~'", "'='", "'+='", "'-='", 
			"'*='", "'/='", "'%='", "'<<='", "'>>='", "'&='", "'|='", "'^='", null, 
			"'++'", "'--'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", "','", 
			"';'", "':'", "'?'", "'{{'", "'}}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Void", "Bool", "Int", "String", "New", "Class", "True", "False", 
			"This", "If", "Else", "For", "While", "Break", "Continue", "Return", 
			"SmallLetter", "CapitalLetter", "Number", "Interger", "Identifier", "ConstString", 
			"F_PREFIX", "QUOTE", "FormatChar", "ConstArray", "ConstElement", "Comment", 
			"BlockComment", "Whitespace", "Plus", "Minus", "Multiply", "Divide", 
			"Mod", "Greater", "Less", "GreaterEqual", "LessEqual", "Equal", "InEqual", 
			"And", "Or", "Not", "LeftMove", "RightMove", "AndBit", "OrBit", "XorBit", 
			"NotBit", "Assign", "AddAssign", "SubAssign", "MulAssign", "DivAssign", 
			"ModAssign", "LeftMoveAssign", "RightMoveAssign", "AndAssign", "OrAssign", 
			"XorAssign", "AssignType", "SelfPlus", "SelfMinus", "Member", "LeftBracket", 
			"RightBracket", "LeftSquareBracket", "RightSquareBracket", "LeftBrace", 
			"RightBrace", "Comma", "Semicolon", "Colon", "QestionMark", "DoubleLeftBrace", 
			"DoubleRightBrace"
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

	public Mxparser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(Mxparser.EOF, 0); }
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
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097246L) != 0)) {
				{
				setState(49);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(46);
					classDeclaration();
					}
					break;
				case 2:
					{
					setState(47);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(48);
					variableDeclaration();
					}
					break;
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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
		public TerminalNode Class() { return getToken(Mxparser.Class, 0); }
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(Mxparser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(Mxparser.RightBrace, 0); }
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
			setState(56);
			match(Class);
			setState(57);
			match(Identifier);
			setState(58);
			match(LeftBrace);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097182L) != 0)) {
				{
				setState(62);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(59);
					variableDeclaration();
					}
					break;
				case 2:
					{
					setState(60);
					functionDeclaration();
					}
					break;
				case 3:
					{
					setState(61);
					classConstructor();
					}
					break;
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(RightBrace);
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
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
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
			setState(69);
			type();
			setState(70);
			match(Identifier);
			setState(71);
			match(LeftBracket);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097182L) != 0)) {
				{
				setState(72);
				parameterList();
				}
			}

			setState(75);
			match(RightBracket);
			setState(76);
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
		public List<TerminalNode> Comma() { return getTokens(Mxparser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(Mxparser.Comma, i);
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
			setState(78);
			parameter();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(79);
				match(Comma);
				setState(80);
				parameter();
				}
				}
				setState(85);
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
			setState(86);
			type();
			setState(87);
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
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
		public List<TerminalNode> Comma() { return getTokens(Mxparser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(Mxparser.Comma, i);
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
			setState(89);
			type();
			setState(90);
			atomVariableDeclaration();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(91);
				match(Comma);
				setState(92);
				atomVariableDeclaration();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
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
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(Mxparser.Assign, 0); }
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
			setState(100);
			match(Identifier);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(101);
				match(Assign);
				setState(102);
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
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
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
			setState(105);
			match(Identifier);
			setState(106);
			match(LeftBracket);
			setState(107);
			match(RightBracket);
			setState(108);
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
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				ifStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				forStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				returnStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(115);
				breakStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(116);
				continueStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(117);
				expressionStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(118);
				variableDeclaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(119);
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
		public TerminalNode LeftBrace() { return getToken(Mxparser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(Mxparser.RightBrace, 0); }
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
			setState(122);
			match(LeftBrace);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9222228540322089954L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2629L) != 0)) {
				{
				{
				setState(123);
				statement();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(RightBrace);
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
		public TerminalNode If() { return getToken(Mxparser.If, 0); }
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(Mxparser.Else, 0); }
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
			setState(131);
			match(If);
			setState(132);
			match(LeftBracket);
			setState(133);
			expression(0);
			setState(134);
			match(RightBracket);
			{
			setState(135);
			statement();
			}
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(136);
				match(Else);
				{
				setState(137);
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
		public TerminalNode For() { return getToken(Mxparser.For, 0); }
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public List<TerminalNode> Semicolon() { return getTokens(Mxparser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(Mxparser.Semicolon, i);
		}
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
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
			setState(140);
			match(For);
			setState(141);
			match(LeftBracket);
			{
			setState(142);
			initalstatement();
			setState(143);
			match(Semicolon);
			}
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 18062777566365765L) != 0)) {
				{
				setState(145);
				expression(0);
				}
			}

			setState(148);
			match(Semicolon);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 18062777566365765L) != 0)) {
				{
				setState(149);
				expression(0);
				}
			}

			setState(152);
			match(RightBracket);
			setState(153);
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
		public TerminalNode While() { return getToken(Mxparser.While, 0); }
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
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
			setState(155);
			match(While);
			setState(156);
			match(LeftBracket);
			setState(157);
			expression(0);
			setState(158);
			match(RightBracket);
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
	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(Mxparser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
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
			setState(161);
			match(Return);
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 18062777566365765L) != 0)) {
				{
				setState(162);
				expression(0);
				}
			}

			setState(165);
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
		public TerminalNode Break() { return getToken(Mxparser.Break, 0); }
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
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
			setState(167);
			match(Break);
			setState(168);
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
		public TerminalNode Continue() { return getToken(Mxparser.Continue, 0); }
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
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
			setState(170);
			match(Continue);
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
	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(Mxparser.Semicolon, 0); }
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
			setState(173);
			expression(0);
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
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(176);
				type();
				}
				break;
			}
			setState(179);
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
		public TerminalNode Int() { return getToken(Mxparser.Int, 0); }
		public TerminalNode Bool() { return getToken(Mxparser.Bool, 0); }
		public TerminalNode String() { return getToken(Mxparser.String, 0); }
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode Void() { return getToken(Mxparser.Void, 0); }
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
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2097182L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class FormattedStringExpressionContext extends ParserRuleContext {
		public TerminalNode F_PREFIX() { return getToken(Mxparser.F_PREFIX, 0); }
		public List<TerminalNode> QUOTE() { return getTokens(Mxparser.QUOTE); }
		public TerminalNode QUOTE(int i) {
			return getToken(Mxparser.QUOTE, i);
		}
		public List<TerminalNode> FormatChar() { return getTokens(Mxparser.FormatChar); }
		public TerminalNode FormatChar(int i) {
			return getToken(Mxparser.FormatChar, i);
		}
		public List<TerminalNode> LeftBrace() { return getTokens(Mxparser.LeftBrace); }
		public TerminalNode LeftBrace(int i) {
			return getToken(Mxparser.LeftBrace, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBrace() { return getTokens(Mxparser.RightBrace); }
		public TerminalNode RightBrace(int i) {
			return getToken(Mxparser.RightBrace, i);
		}
		public FormattedStringExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formattedStringExpression; }
	}

	public final FormattedStringExpressionContext formattedStringExpression() throws RecognitionException {
		FormattedStringExpressionContext _localctx = new FormattedStringExpressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_formattedStringExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(F_PREFIX);
			setState(184);
			match(QUOTE);
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FormatChar || _la==LeftBrace) {
				{
				setState(190);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case FormatChar:
					{
					setState(185);
					match(FormatChar);
					}
					break;
				case LeftBrace:
					{
					setState(186);
					match(LeftBrace);
					setState(187);
					expression(0);
					setState(188);
					match(RightBrace);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(195);
			match(QUOTE);
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
		public TerminalNode LeftBracket() { return getToken(Mxparser.LeftBracket, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightBracket() { return getToken(Mxparser.RightBracket, 0); }
		public TerminalNode New() { return getToken(Mxparser.New, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode LeftSquareBracket() { return getToken(Mxparser.LeftSquareBracket, 0); }
		public TerminalNode RightSquareBracket() { return getToken(Mxparser.RightSquareBracket, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(Mxparser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(Mxparser.SelfMinus, 0); }
		public TerminalNode Not() { return getToken(Mxparser.Not, 0); }
		public TerminalNode Minus() { return getToken(Mxparser.Minus, 0); }
		public TerminalNode NotBit() { return getToken(Mxparser.NotBit, 0); }
		public TerminalNode QestionMark() { return getToken(Mxparser.QestionMark, 0); }
		public TerminalNode Colon() { return getToken(Mxparser.Colon, 0); }
		public TerminalNode Multiply() { return getToken(Mxparser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(Mxparser.Divide, 0); }
		public TerminalNode Mod() { return getToken(Mxparser.Mod, 0); }
		public TerminalNode Plus() { return getToken(Mxparser.Plus, 0); }
		public TerminalNode LeftMove() { return getToken(Mxparser.LeftMove, 0); }
		public TerminalNode RightMove() { return getToken(Mxparser.RightMove, 0); }
		public TerminalNode Greater() { return getToken(Mxparser.Greater, 0); }
		public TerminalNode Less() { return getToken(Mxparser.Less, 0); }
		public TerminalNode GreaterEqual() { return getToken(Mxparser.GreaterEqual, 0); }
		public TerminalNode LessEqual() { return getToken(Mxparser.LessEqual, 0); }
		public TerminalNode Equal() { return getToken(Mxparser.Equal, 0); }
		public TerminalNode InEqual() { return getToken(Mxparser.InEqual, 0); }
		public TerminalNode AndBit() { return getToken(Mxparser.AndBit, 0); }
		public TerminalNode XorBit() { return getToken(Mxparser.XorBit, 0); }
		public TerminalNode OrBit() { return getToken(Mxparser.OrBit, 0); }
		public TerminalNode And() { return getToken(Mxparser.And, 0); }
		public TerminalNode Or() { return getToken(Mxparser.Or, 0); }
		public TerminalNode AssignType() { return getToken(Mxparser.AssignType, 0); }
		public TerminalNode Comma() { return getToken(Mxparser.Comma, 0); }
		public TerminalNode Member() { return getToken(Mxparser.Member, 0); }
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBracket:
				{
				setState(198);
				match(LeftBracket);
				setState(199);
				expression(0);
				setState(200);
				match(RightBracket);
				setState(201);
				match(New);
				setState(202);
				type();
				setState(208);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(203);
					match(LeftSquareBracket);
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 18062777566365765L) != 0)) {
						{
						setState(204);
						expression(0);
						}
					}

					setState(207);
					match(RightSquareBracket);
					}
					break;
				}
				setState(211);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(210);
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
				setState(213);
				_la = _input.LA(1);
				if ( !(((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 6442717185L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(214);
				expression(15);
				}
				break;
			case QestionMark:
				{
				setState(215);
				match(QestionMark);
				setState(216);
				expression(0);
				setState(217);
				match(Colon);
				setState(218);
				expression(4);
				}
				break;
			case Identifier:
			case F_PREFIX:
			case ConstElement:
				{
				setState(220);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(264);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(224);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 60129542144L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(225);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(227);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(228);
						expression(14);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(230);
						_la = _input.LA(1);
						if ( !(_la==LeftMove || _la==RightMove) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(231);
						expression(13);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(232);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(233);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1030792151040L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(234);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(236);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==InEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(237);
						expression(11);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(238);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						{
						setState(239);
						match(AndBit);
						}
						setState(240);
						expression(10);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(241);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						{
						setState(242);
						match(XorBit);
						}
						setState(243);
						expression(9);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						{
						setState(245);
						match(OrBit);
						}
						setState(246);
						expression(8);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(247);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(248);
						match(And);
						setState(249);
						expression(7);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(250);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(251);
						match(Or);
						setState(252);
						expression(6);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						{
						setState(254);
						match(AssignType);
						}
						setState(255);
						expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(257);
						match(Comma);
						setState(258);
						expression(3);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(260);
						match(Member);
						setState(261);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(262);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(263);
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
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public TerminalNode ConstElement() { return getToken(Mxparser.ConstElement, 0); }
		public FormattedStringExpressionContext formattedStringExpression() {
			return getRuleContext(FormattedStringExpressionContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_atom);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				match(Identifier);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(271);
				match(ConstElement);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(272);
				formattedStringExpression();
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
		public TerminalNode Identifier() { return getToken(Mxparser.Identifier, 0); }
		public List<TerminalNode> LeftSquareBracket() { return getTokens(Mxparser.LeftSquareBracket); }
		public TerminalNode LeftSquareBracket(int i) {
			return getToken(Mxparser.LeftSquareBracket, i);
		}
		public List<TerminalNode> RightSquareBracket() { return getTokens(Mxparser.RightSquareBracket); }
		public TerminalNode RightSquareBracket(int i) {
			return getToken(Mxparser.RightSquareBracket, i);
		}
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
		enterRule(_localctx, 44, RULE_array);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(Identifier);
			setState(281); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(276);
					match(LeftSquareBracket);
					setState(278);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & 18062777566365765L) != 0)) {
						{
						setState(277);
						expression(0);
						}
					}

					setState(280);
					match(RightSquareBracket);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(283); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
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
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 17);
		case 13:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001M\u011e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000"+
		"2\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"?\b\u0001\n\u0001\f\u0001B\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002J\b\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"R\b\u0003\n\u0003\f\u0003U\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005^\b\u0005"+
		"\n\u0005\f\u0005a\t\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006h\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\by\b\b\u0001\t\u0001\t\u0005"+
		"\t}\b\t\n\t\f\t\u0080\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u008b\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0093\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u0097\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0003\r\u00a4\b\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0011\u0003\u0011\u00b2\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u00bf\b\u0013\n\u0013\f\u0013\u00c2"+
		"\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00ce"+
		"\b\u0014\u0001\u0014\u0003\u0014\u00d1\b\u0014\u0001\u0014\u0003\u0014"+
		"\u00d4\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00de\b\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0109\b\u0014"+
		"\n\u0014\f\u0014\u010c\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u0112\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u0117\b\u0016\u0001\u0016\u0004\u0016\u011a\b\u0016\u000b\u0016"+
		"\f\u0016\u011b\u0001\u0016\u0000\u0001(\u0017\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000"+
		"\b\u0002\u0000\u0001\u0004\u0015\u0015\u0004\u0000  ,,22?@\u0001\u0000"+
		"!#\u0001\u0000\u001f \u0001\u0000-.\u0001\u0000$\'\u0001\u0000()\u0001"+
		"\u0000?@\u013a\u00003\u0001\u0000\u0000\u0000\u00028\u0001\u0000\u0000"+
		"\u0000\u0004E\u0001\u0000\u0000\u0000\u0006N\u0001\u0000\u0000\u0000\b"+
		"V\u0001\u0000\u0000\u0000\nY\u0001\u0000\u0000\u0000\fd\u0001\u0000\u0000"+
		"\u0000\u000ei\u0001\u0000\u0000\u0000\u0010x\u0001\u0000\u0000\u0000\u0012"+
		"z\u0001\u0000\u0000\u0000\u0014\u0083\u0001\u0000\u0000\u0000\u0016\u008c"+
		"\u0001\u0000\u0000\u0000\u0018\u009b\u0001\u0000\u0000\u0000\u001a\u00a1"+
		"\u0001\u0000\u0000\u0000\u001c\u00a7\u0001\u0000\u0000\u0000\u001e\u00aa"+
		"\u0001\u0000\u0000\u0000 \u00ad\u0001\u0000\u0000\u0000\"\u00b1\u0001"+
		"\u0000\u0000\u0000$\u00b5\u0001\u0000\u0000\u0000&\u00b7\u0001\u0000\u0000"+
		"\u0000(\u00dd\u0001\u0000\u0000\u0000*\u0111\u0001\u0000\u0000\u0000,"+
		"\u0113\u0001\u0000\u0000\u0000.2\u0003\u0002\u0001\u0000/2\u0003\u0004"+
		"\u0002\u000002\u0003\n\u0005\u00001.\u0001\u0000\u0000\u00001/\u0001\u0000"+
		"\u0000\u000010\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u000031\u0001"+
		"\u0000\u0000\u000034\u0001\u0000\u0000\u000046\u0001\u0000\u0000\u0000"+
		"53\u0001\u0000\u0000\u000067\u0005\u0000\u0000\u00017\u0001\u0001\u0000"+
		"\u0000\u000089\u0005\u0006\u0000\u00009:\u0005\u0015\u0000\u0000:@\u0005"+
		"F\u0000\u0000;?\u0003\n\u0005\u0000<?\u0003\u0004\u0002\u0000=?\u0003"+
		"\u000e\u0007\u0000>;\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000"+
		">=\u0001\u0000\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000"+
		"\u0000@A\u0001\u0000\u0000\u0000AC\u0001\u0000\u0000\u0000B@\u0001\u0000"+
		"\u0000\u0000CD\u0005G\u0000\u0000D\u0003\u0001\u0000\u0000\u0000EF\u0003"+
		"$\u0012\u0000FG\u0005\u0015\u0000\u0000GI\u0005B\u0000\u0000HJ\u0003\u0006"+
		"\u0003\u0000IH\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JK\u0001"+
		"\u0000\u0000\u0000KL\u0005C\u0000\u0000LM\u0003\u0012\t\u0000M\u0005\u0001"+
		"\u0000\u0000\u0000NS\u0003\b\u0004\u0000OP\u0005H\u0000\u0000PR\u0003"+
		"\b\u0004\u0000QO\u0001\u0000\u0000\u0000RU\u0001\u0000\u0000\u0000SQ\u0001"+
		"\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000T\u0007\u0001\u0000\u0000"+
		"\u0000US\u0001\u0000\u0000\u0000VW\u0003$\u0012\u0000WX\u0003\f\u0006"+
		"\u0000X\t\u0001\u0000\u0000\u0000YZ\u0003$\u0012\u0000Z_\u0003\f\u0006"+
		"\u0000[\\\u0005H\u0000\u0000\\^\u0003\f\u0006\u0000][\u0001\u0000\u0000"+
		"\u0000^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`b\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0005"+
		"I\u0000\u0000c\u000b\u0001\u0000\u0000\u0000dg\u0005\u0015\u0000\u0000"+
		"ef\u00053\u0000\u0000fh\u0003(\u0014\u0000ge\u0001\u0000\u0000\u0000g"+
		"h\u0001\u0000\u0000\u0000h\r\u0001\u0000\u0000\u0000ij\u0005\u0015\u0000"+
		"\u0000jk\u0005B\u0000\u0000kl\u0005C\u0000\u0000lm\u0003\u0012\t\u0000"+
		"m\u000f\u0001\u0000\u0000\u0000ny\u0003\u0012\t\u0000oy\u0003\u0014\n"+
		"\u0000py\u0003\u0016\u000b\u0000qy\u0003\u0018\f\u0000ry\u0003\u001a\r"+
		"\u0000sy\u0003\u001c\u000e\u0000ty\u0003\u001e\u000f\u0000uy\u0003 \u0010"+
		"\u0000vy\u0003\n\u0005\u0000wy\u0005I\u0000\u0000xn\u0001\u0000\u0000"+
		"\u0000xo\u0001\u0000\u0000\u0000xp\u0001\u0000\u0000\u0000xq\u0001\u0000"+
		"\u0000\u0000xr\u0001\u0000\u0000\u0000xs\u0001\u0000\u0000\u0000xt\u0001"+
		"\u0000\u0000\u0000xu\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000"+
		"xw\u0001\u0000\u0000\u0000y\u0011\u0001\u0000\u0000\u0000z~\u0005F\u0000"+
		"\u0000{}\u0003\u0010\b\u0000|{\u0001\u0000\u0000\u0000}\u0080\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000"+
		"\u007f\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0005G\u0000\u0000\u0082\u0013\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005\n\u0000\u0000\u0084\u0085\u0005B\u0000\u0000\u0085\u0086\u0003"+
		"(\u0014\u0000\u0086\u0087\u0005C\u0000\u0000\u0087\u008a\u0003\u0010\b"+
		"\u0000\u0088\u0089\u0005\u000b\u0000\u0000\u0089\u008b\u0003\u0010\b\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000"+
		"\u008b\u0015\u0001\u0000\u0000\u0000\u008c\u008d\u0005\f\u0000\u0000\u008d"+
		"\u008e\u0005B\u0000\u0000\u008e\u008f\u0003\"\u0011\u0000\u008f\u0090"+
		"\u0005I\u0000\u0000\u0090\u0092\u0001\u0000\u0000\u0000\u0091\u0093\u0003"+
		"(\u0014\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000"+
		"\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0096\u0005I\u0000"+
		"\u0000\u0095\u0097\u0003(\u0014\u0000\u0096\u0095\u0001\u0000\u0000\u0000"+
		"\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000"+
		"\u0098\u0099\u0005C\u0000\u0000\u0099\u009a\u0003\u0010\b\u0000\u009a"+
		"\u0017\u0001\u0000\u0000\u0000\u009b\u009c\u0005\r\u0000\u0000\u009c\u009d"+
		"\u0005B\u0000\u0000\u009d\u009e\u0003(\u0014\u0000\u009e\u009f\u0005C"+
		"\u0000\u0000\u009f\u00a0\u0003\u0010\b\u0000\u00a0\u0019\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a3\u0005\u0010\u0000\u0000\u00a2\u00a4\u0003(\u0014\u0000"+
		"\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0005I\u0000\u0000\u00a6"+
		"\u001b\u0001\u0000\u0000\u0000\u00a7\u00a8\u0005\u000e\u0000\u0000\u00a8"+
		"\u00a9\u0005I\u0000\u0000\u00a9\u001d\u0001\u0000\u0000\u0000\u00aa\u00ab"+
		"\u0005\u000f\u0000\u0000\u00ab\u00ac\u0005I\u0000\u0000\u00ac\u001f\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0003(\u0014\u0000\u00ae\u00af\u0005I\u0000"+
		"\u0000\u00af!\u0001\u0000\u0000\u0000\u00b0\u00b2\u0003$\u0012\u0000\u00b1"+
		"\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0003(\u0014\u0000\u00b4#\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0007\u0000\u0000\u0000\u00b6%\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b8\u0005\u0017\u0000\u0000\u00b8\u00c0\u0005\u0018"+
		"\u0000\u0000\u00b9\u00bf\u0005\u0019\u0000\u0000\u00ba\u00bb\u0005F\u0000"+
		"\u0000\u00bb\u00bc\u0003(\u0014\u0000\u00bc\u00bd\u0005G\u0000\u0000\u00bd"+
		"\u00bf\u0001\u0000\u0000\u0000\u00be\u00b9\u0001\u0000\u0000\u0000\u00be"+
		"\u00ba\u0001\u0000\u0000\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0"+
		"\u00be\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c4\u0005\u0018\u0000\u0000\u00c4\'\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0006\u0014\uffff\uffff\u0000\u00c6\u00c7\u0005B\u0000\u0000\u00c7\u00c8"+
		"\u0003(\u0014\u0000\u00c8\u00c9\u0005C\u0000\u0000\u00c9\u00ca\u0005\u0005"+
		"\u0000\u0000\u00ca\u00d0\u0003$\u0012\u0000\u00cb\u00cd\u0005D\u0000\u0000"+
		"\u00cc\u00ce\u0003(\u0014\u0000\u00cd\u00cc\u0001\u0000\u0000\u0000\u00cd"+
		"\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d1\u0005E\u0000\u0000\u00d0\u00cb\u0001\u0000\u0000\u0000\u00d0\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d3\u0001\u0000\u0000\u0000\u00d2\u00d4"+
		"\u0003*\u0015\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d4\u00de\u0001\u0000\u0000\u0000\u00d5\u00d6\u0007"+
		"\u0001\u0000\u0000\u00d6\u00de\u0003(\u0014\u000f\u00d7\u00d8\u0005K\u0000"+
		"\u0000\u00d8\u00d9\u0003(\u0014\u0000\u00d9\u00da\u0005J\u0000\u0000\u00da"+
		"\u00db\u0003(\u0014\u0004\u00db\u00de\u0001\u0000\u0000\u0000\u00dc\u00de"+
		"\u0003*\u0015\u0000\u00dd\u00c5\u0001\u0000\u0000\u0000\u00dd\u00d5\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d7\u0001\u0000\u0000\u0000\u00dd\u00dc\u0001"+
		"\u0000\u0000\u0000\u00de\u010a\u0001\u0000\u0000\u0000\u00df\u00e0\n\u000e"+
		"\u0000\u0000\u00e0\u00e1\u0007\u0002\u0000\u0000\u00e1\u0109\u0003(\u0014"+
		"\u000f\u00e2\u00e3\n\r\u0000\u0000\u00e3\u00e4\u0007\u0003\u0000\u0000"+
		"\u00e4\u0109\u0003(\u0014\u000e\u00e5\u00e6\n\f\u0000\u0000\u00e6\u00e7"+
		"\u0007\u0004\u0000\u0000\u00e7\u0109\u0003(\u0014\r\u00e8\u00e9\n\u000b"+
		"\u0000\u0000\u00e9\u00ea\u0007\u0005\u0000\u0000\u00ea\u0109\u0003(\u0014"+
		"\f\u00eb\u00ec\n\n\u0000\u0000\u00ec\u00ed\u0007\u0006\u0000\u0000\u00ed"+
		"\u0109\u0003(\u0014\u000b\u00ee\u00ef\n\t\u0000\u0000\u00ef\u00f0\u0005"+
		"/\u0000\u0000\u00f0\u0109\u0003(\u0014\n\u00f1\u00f2\n\b\u0000\u0000\u00f2"+
		"\u00f3\u00051\u0000\u0000\u00f3\u0109\u0003(\u0014\t\u00f4\u00f5\n\u0007"+
		"\u0000\u0000\u00f5\u00f6\u00050\u0000\u0000\u00f6\u0109\u0003(\u0014\b"+
		"\u00f7\u00f8\n\u0006\u0000\u0000\u00f8\u00f9\u0005*\u0000\u0000\u00f9"+
		"\u0109\u0003(\u0014\u0007\u00fa\u00fb\n\u0005\u0000\u0000\u00fb\u00fc"+
		"\u0005+\u0000\u0000\u00fc\u0109\u0003(\u0014\u0006\u00fd\u00fe\n\u0003"+
		"\u0000\u0000\u00fe\u00ff\u0005>\u0000\u0000\u00ff\u0109\u0003(\u0014\u0003"+
		"\u0100\u0101\n\u0002\u0000\u0000\u0101\u0102\u0005H\u0000\u0000\u0102"+
		"\u0109\u0003(\u0014\u0003\u0103\u0104\n\u0011\u0000\u0000\u0104\u0105"+
		"\u0005A\u0000\u0000\u0105\u0109\u0005\u0015\u0000\u0000\u0106\u0107\n"+
		"\u0010\u0000\u0000\u0107\u0109\u0007\u0007\u0000\u0000\u0108\u00df\u0001"+
		"\u0000\u0000\u0000\u0108\u00e2\u0001\u0000\u0000\u0000\u0108\u00e5\u0001"+
		"\u0000\u0000\u0000\u0108\u00e8\u0001\u0000\u0000\u0000\u0108\u00eb\u0001"+
		"\u0000\u0000\u0000\u0108\u00ee\u0001\u0000\u0000\u0000\u0108\u00f1\u0001"+
		"\u0000\u0000\u0000\u0108\u00f4\u0001\u0000\u0000\u0000\u0108\u00f7\u0001"+
		"\u0000\u0000\u0000\u0108\u00fa\u0001\u0000\u0000\u0000\u0108\u00fd\u0001"+
		"\u0000\u0000\u0000\u0108\u0100\u0001\u0000\u0000\u0000\u0108\u0103\u0001"+
		"\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0109\u010c\u0001"+
		"\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a\u010b\u0001"+
		"\u0000\u0000\u0000\u010b)\u0001\u0000\u0000\u0000\u010c\u010a\u0001\u0000"+
		"\u0000\u0000\u010d\u0112\u0003,\u0016\u0000\u010e\u0112\u0005\u0015\u0000"+
		"\u0000\u010f\u0112\u0005\u001b\u0000\u0000\u0110\u0112\u0003&\u0013\u0000"+
		"\u0111\u010d\u0001\u0000\u0000\u0000\u0111\u010e\u0001\u0000\u0000\u0000"+
		"\u0111\u010f\u0001\u0000\u0000\u0000\u0111\u0110\u0001\u0000\u0000\u0000"+
		"\u0112+\u0001\u0000\u0000\u0000\u0113\u0119\u0005\u0015\u0000\u0000\u0114"+
		"\u0116\u0005D\u0000\u0000\u0115\u0117\u0003(\u0014\u0000\u0116\u0115\u0001"+
		"\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0001"+
		"\u0000\u0000\u0000\u0118\u011a\u0005E\u0000\u0000\u0119\u0114\u0001\u0000"+
		"\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000"+
		"\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c-\u0001\u0000\u0000"+
		"\u0000\u001a13>@IS_gx~\u008a\u0092\u0096\u00a3\u00b1\u00be\u00c0\u00cd"+
		"\u00d0\u00d3\u00dd\u0108\u010a\u0111\u0116\u011b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}