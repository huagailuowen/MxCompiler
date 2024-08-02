// Generated from /mnt/d/周宸源/大学/学习/程序/MxCompiler/src/antlr/Hard_Version_FormatString/Mxparser.g4 by ANTLR 4.13.1

	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MxparserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, FormatStringM=8, 
		Void=9, Null=10, Bool=11, Int=12, String=13, New=14, Class=15, True=16, 
		False=17, This=18, If=19, Else=20, For=21, While=22, Break=23, Continue=24, 
		Return=25, Plus=26, Minus=27, Multiply=28, Divide=29, Mod=30, Greater=31, 
		Less=32, GreaterEqual=33, LessEqual=34, Equal=35, InEqual=36, And=37, 
		Or=38, Not=39, LeftMove=40, RightMove=41, AndBit=42, OrBit=43, XorBit=44, 
		NotBit=45, Assign=46, SelfPlus=47, SelfMinus=48, Member=49, Comma=50, 
		Semicolon=51, LeftBrace=52, RightBrace=53, Comment=54, BlockComment=55, 
		Whitespace=56, Interger=57, Identifier=58, ConstString=59, FormatStringI=60, 
		FormatStringL=61, FormatStringR=62;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "FormatStringChar", 
			"FormatStringM", "Void", "Null", "Bool", "Int", "String", "New", "Class", 
			"True", "False", "This", "If", "Else", "For", "While", "Break", "Continue", 
			"Return", "Plus", "Minus", "Multiply", "Divide", "Mod", "Greater", "Less", 
			"GreaterEqual", "LessEqual", "Equal", "InEqual", "And", "Or", "Not", 
			"LeftMove", "RightMove", "AndBit", "OrBit", "XorBit", "NotBit", "Assign", 
			"SelfPlus", "SelfMinus", "Member", "Comma", "Semicolon", "LeftBrace", 
			"RightBrace", "Comment", "BlockComment", "Whitespace", "Interger", "Identifier", 
			"SringChar", "ConstString", "FormatStringI", "FormatStringL", "FormatStringR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'()'", "'?'", "':'", null, "'void'", 
			"'null'", "'bool'", "'int'", "'string'", "'new'", "'class'", "'true'", 
			"'false'", "'this'", "'if'", "'else'", "'for'", "'while'", "'break'", 
			"'continue'", "'return'", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", 
			"'>='", "'<='", "'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", 
			"'&'", "'|'", "'^'", "'~'", "'='", "'++'", "'--'", "'.'", "','", "';'", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "FormatStringM", "Void", 
			"Null", "Bool", "Int", "String", "New", "Class", "True", "False", "This", 
			"If", "Else", "For", "While", "Break", "Continue", "Return", "Plus", 
			"Minus", "Multiply", "Divide", "Mod", "Greater", "Less", "GreaterEqual", 
			"LessEqual", "Equal", "InEqual", "And", "Or", "Not", "LeftMove", "RightMove", 
			"AndBit", "OrBit", "XorBit", "NotBit", "Assign", "SelfPlus", "SelfMinus", 
			"Member", "Comma", "Semicolon", "LeftBrace", "RightBrace", "Comment", 
			"BlockComment", "Whitespace", "Interger", "Identifier", "ConstString", 
			"FormatStringI", "FormatStringL", "FormatStringR"
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


		int l_brace = 0;
		int stack_count = 0;
		Stack<Integer> stack = new Stack<Integer>();


	public MxparserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mxparser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 52:
			LeftBrace_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			RightBrace_action((RuleContext)_localctx, actionIndex);
			break;
		case 62:
			FormatStringL_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			FormatStringR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void LeftBrace_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

				l_brace++;

			break;
		}
	}
	private void RightBrace_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:

				l_brace--;

			break;
		}
	}
	private void FormatStringL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:

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
				
			break;
		}
	}
	private void FormatStringR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:

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
				
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return FormatStringChar_sempred((RuleContext)_localctx, predIndex);
		case 8:
			return FormatStringM_sempred((RuleContext)_localctx, predIndex);
		case 60:
			return ConstString_sempred((RuleContext)_localctx, predIndex);
		case 63:
			return FormatStringR_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean FormatStringChar_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return stack_count!=0&&l_brace == 0;
		}
		return true;
	}
	private boolean FormatStringM_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return stack_count>0 && l_brace==0;
		}
		return true;
	}
	private boolean ConstString_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return stack_count==0||l_brace > 0;
		}
		return true;
	}
	private boolean FormatStringR_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return stack_count!=0&&l_brace==0;
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0000>\u01ab\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u009f\b\u0007\u0001\b\u0001\b\u0004\b\u00a3\b\b\u000b\b\f\b\u00a4\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001"+
		".\u0001/\u0001/\u0001/\u00010\u00010\u00010\u00011\u00011\u00012\u0001"+
		"2\u00013\u00013\u00014\u00014\u00014\u00015\u00015\u00015\u00016\u0001"+
		"6\u00016\u00016\u00056\u014b\b6\n6\f6\u014e\t6\u00016\u00016\u00017\u0001"+
		"7\u00017\u00017\u00057\u0156\b7\n7\f7\u0159\t7\u00017\u00017\u00017\u0001"+
		"7\u00017\u00018\u00048\u0161\b8\u000b8\f8\u0162\u00018\u00018\u00019\u0001"+
		"9\u00059\u0169\b9\n9\f9\u016c\t9\u00019\u00039\u016f\b9\u0001:\u0001:"+
		"\u0005:\u0173\b:\n:\f:\u0176\t:\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0003;\u0181\b;\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0005<\u018e\b<\n<\f<\u0191"+
		"\t<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0003=\u0199\b=\u0001=\u0001"+
		"=\u0001>\u0001>\u0001>\u0001>\u0003>\u01a1\b>\u0001>\u0001>\u0001?\u0001"+
		"?\u0003?\u01a7\b?\u0001?\u0001?\u0001?\u0001\u0157\u0000@\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\u0000\u0011\b\u0013\t\u0015\n\u0017\u000b\u0019\f\u001b\r\u001d\u000e"+
		"\u001f\u000f!\u0010#\u0011%\u0012\'\u0013)\u0014+\u0015-\u0016/\u0017"+
		"1\u00183\u00195\u001a7\u001b9\u001c;\u001d=\u001e?\u001fA C!E\"G#I$K%"+
		"M&O\'Q(S)U*W+Y,[-]._/a0c1e2g3i4k5m6o7q8s9u:w\u0000y;{<}=\u007f>\u0001"+
		"\u0000\b\u0006\u0000\n\n\r\r\"\"\\\\{{}}\u0002\u0000\n\n\r\r\u0003\u0000"+
		"\t\n\r\r  \u0001\u000019\u0001\u000009\u0002\u0000AZaz\u0004\u000009A"+
		"Z__az\u0004\u0000\n\n\r\r\"\"\\\\\u01c1\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001"+
		"\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000"+
		"\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003"+
		"\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000"+
		"\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000"+
		"\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A"+
		"\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000"+
		"\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000"+
		"\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O"+
		"\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000"+
		"\u0000\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000"+
		"\u0000Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]"+
		"\u0001\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000"+
		"\u0000\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000"+
		"\u0000g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k"+
		"\u0001\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000"+
		"\u0000\u0000\u0000q\u0001\u0000\u0000\u0000\u0000s\u0001\u0000\u0000\u0000"+
		"\u0000u\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{"+
		"\u0001\u0000\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001"+
		"\u0000\u0000\u0000\u0001\u0081\u0001\u0000\u0000\u0000\u0003\u0083\u0001"+
		"\u0000\u0000\u0000\u0005\u0085\u0001\u0000\u0000\u0000\u0007\u0087\u0001"+
		"\u0000\u0000\u0000\t\u0089\u0001\u0000\u0000\u0000\u000b\u008c\u0001\u0000"+
		"\u0000\u0000\r\u008e\u0001\u0000\u0000\u0000\u000f\u009e\u0001\u0000\u0000"+
		"\u0000\u0011\u00a0\u0001\u0000\u0000\u0000\u0013\u00a6\u0001\u0000\u0000"+
		"\u0000\u0015\u00ab\u0001\u0000\u0000\u0000\u0017\u00b0\u0001\u0000\u0000"+
		"\u0000\u0019\u00b5\u0001\u0000\u0000\u0000\u001b\u00b9\u0001\u0000\u0000"+
		"\u0000\u001d\u00c0\u0001\u0000\u0000\u0000\u001f\u00c4\u0001\u0000\u0000"+
		"\u0000!\u00ca\u0001\u0000\u0000\u0000#\u00cf\u0001\u0000\u0000\u0000%"+
		"\u00d5\u0001\u0000\u0000\u0000\'\u00da\u0001\u0000\u0000\u0000)\u00dd"+
		"\u0001\u0000\u0000\u0000+\u00e2\u0001\u0000\u0000\u0000-\u00e6\u0001\u0000"+
		"\u0000\u0000/\u00ec\u0001\u0000\u0000\u00001\u00f2\u0001\u0000\u0000\u0000"+
		"3\u00fb\u0001\u0000\u0000\u00005\u0102\u0001\u0000\u0000\u00007\u0104"+
		"\u0001\u0000\u0000\u00009\u0106\u0001\u0000\u0000\u0000;\u0108\u0001\u0000"+
		"\u0000\u0000=\u010a\u0001\u0000\u0000\u0000?\u010c\u0001\u0000\u0000\u0000"+
		"A\u010e\u0001\u0000\u0000\u0000C\u0110\u0001\u0000\u0000\u0000E\u0113"+
		"\u0001\u0000\u0000\u0000G\u0116\u0001\u0000\u0000\u0000I\u0119\u0001\u0000"+
		"\u0000\u0000K\u011c\u0001\u0000\u0000\u0000M\u011f\u0001\u0000\u0000\u0000"+
		"O\u0122\u0001\u0000\u0000\u0000Q\u0124\u0001\u0000\u0000\u0000S\u0127"+
		"\u0001\u0000\u0000\u0000U\u012a\u0001\u0000\u0000\u0000W\u012c\u0001\u0000"+
		"\u0000\u0000Y\u012e\u0001\u0000\u0000\u0000[\u0130\u0001\u0000\u0000\u0000"+
		"]\u0132\u0001\u0000\u0000\u0000_\u0134\u0001\u0000\u0000\u0000a\u0137"+
		"\u0001\u0000\u0000\u0000c\u013a\u0001\u0000\u0000\u0000e\u013c\u0001\u0000"+
		"\u0000\u0000g\u013e\u0001\u0000\u0000\u0000i\u0140\u0001\u0000\u0000\u0000"+
		"k\u0143\u0001\u0000\u0000\u0000m\u0146\u0001\u0000\u0000\u0000o\u0151"+
		"\u0001\u0000\u0000\u0000q\u0160\u0001\u0000\u0000\u0000s\u016e\u0001\u0000"+
		"\u0000\u0000u\u0170\u0001\u0000\u0000\u0000w\u0180\u0001\u0000\u0000\u0000"+
		"y\u0182\u0001\u0000\u0000\u0000{\u0194\u0001\u0000\u0000\u0000}\u019c"+
		"\u0001\u0000\u0000\u0000\u007f\u01a4\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005(\u0000\u0000\u0082\u0002\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		")\u0000\u0000\u0084\u0004\u0001\u0000\u0000\u0000\u0085\u0086\u0005[\u0000"+
		"\u0000\u0086\u0006\u0001\u0000\u0000\u0000\u0087\u0088\u0005]\u0000\u0000"+
		"\u0088\b\u0001\u0000\u0000\u0000\u0089\u008a\u0005(\u0000\u0000\u008a"+
		"\u008b\u0005)\u0000\u0000\u008b\n\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0005?\u0000\u0000\u008d\f\u0001\u0000\u0000\u0000\u008e\u008f\u0005"+
		":\u0000\u0000\u008f\u000e\u0001\u0000\u0000\u0000\u0090\u0091\u0004\u0007"+
		"\u0000\u0000\u0091\u0092\u0005{\u0000\u0000\u0092\u009f\u0005{\u0000\u0000"+
		"\u0093\u0094\u0005}\u0000\u0000\u0094\u009f\u0005}\u0000\u0000\u0095\u009f"+
		"\b\u0000\u0000\u0000\u0096\u0097\u0005\\\u0000\u0000\u0097\u009f\u0005"+
		"\"\u0000\u0000\u0098\u0099\u0005\\\u0000\u0000\u0099\u009f\u0005n\u0000"+
		"\u0000\u009a\u009b\u0005\\\u0000\u0000\u009b\u009f\u0005r\u0000\u0000"+
		"\u009c\u009d\u0005\\\u0000\u0000\u009d\u009f\u0005\\\u0000\u0000\u009e"+
		"\u0090\u0001\u0000\u0000\u0000\u009e\u0093\u0001\u0000\u0000\u0000\u009e"+
		"\u0095\u0001\u0000\u0000\u0000\u009e\u0096\u0001\u0000\u0000\u0000\u009e"+
		"\u0098\u0001\u0000\u0000\u0000\u009e\u009a\u0001\u0000\u0000\u0000\u009e"+
		"\u009c\u0001\u0000\u0000\u0000\u009f\u0010\u0001\u0000\u0000\u0000\u00a0"+
		"\u00a2\u0004\b\u0001\u0000\u00a1\u00a3\u0003\u000f\u0007\u0000\u00a2\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u0012"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005v\u0000\u0000\u00a7\u00a8\u0005"+
		"o\u0000\u0000\u00a8\u00a9\u0005i\u0000\u0000\u00a9\u00aa\u0005d\u0000"+
		"\u0000\u00aa\u0014\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005n\u0000\u0000"+
		"\u00ac\u00ad\u0005u\u0000\u0000\u00ad\u00ae\u0005l\u0000\u0000\u00ae\u00af"+
		"\u0005l\u0000\u0000\u00af\u0016\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005"+
		"b\u0000\u0000\u00b1\u00b2\u0005o\u0000\u0000\u00b2\u00b3\u0005o\u0000"+
		"\u0000\u00b3\u00b4\u0005l\u0000\u0000\u00b4\u0018\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b6\u0005i\u0000\u0000\u00b6\u00b7\u0005n\u0000\u0000\u00b7\u00b8"+
		"\u0005t\u0000\u0000\u00b8\u001a\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005"+
		"s\u0000\u0000\u00ba\u00bb\u0005t\u0000\u0000\u00bb\u00bc\u0005r\u0000"+
		"\u0000\u00bc\u00bd\u0005i\u0000\u0000\u00bd\u00be\u0005n\u0000\u0000\u00be"+
		"\u00bf\u0005g\u0000\u0000\u00bf\u001c\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0005n\u0000\u0000\u00c1\u00c2\u0005e\u0000\u0000\u00c2\u00c3\u0005w"+
		"\u0000\u0000\u00c3\u001e\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005c\u0000"+
		"\u0000\u00c5\u00c6\u0005l\u0000\u0000\u00c6\u00c7\u0005a\u0000\u0000\u00c7"+
		"\u00c8\u0005s\u0000\u0000\u00c8\u00c9\u0005s\u0000\u0000\u00c9 \u0001"+
		"\u0000\u0000\u0000\u00ca\u00cb\u0005t\u0000\u0000\u00cb\u00cc\u0005r\u0000"+
		"\u0000\u00cc\u00cd\u0005u\u0000\u0000\u00cd\u00ce\u0005e\u0000\u0000\u00ce"+
		"\"\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005f\u0000\u0000\u00d0\u00d1"+
		"\u0005a\u0000\u0000\u00d1\u00d2\u0005l\u0000\u0000\u00d2\u00d3\u0005s"+
		"\u0000\u0000\u00d3\u00d4\u0005e\u0000\u0000\u00d4$\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0005t\u0000\u0000\u00d6\u00d7\u0005h\u0000\u0000\u00d7\u00d8"+
		"\u0005i\u0000\u0000\u00d8\u00d9\u0005s\u0000\u0000\u00d9&\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u0005i\u0000\u0000\u00db\u00dc\u0005f\u0000\u0000"+
		"\u00dc(\u0001\u0000\u0000\u0000\u00dd\u00de\u0005e\u0000\u0000\u00de\u00df"+
		"\u0005l\u0000\u0000\u00df\u00e0\u0005s\u0000\u0000\u00e0\u00e1\u0005e"+
		"\u0000\u0000\u00e1*\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005f\u0000\u0000"+
		"\u00e3\u00e4\u0005o\u0000\u0000\u00e4\u00e5\u0005r\u0000\u0000\u00e5,"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005w\u0000\u0000\u00e7\u00e8\u0005"+
		"h\u0000\u0000\u00e8\u00e9\u0005i\u0000\u0000\u00e9\u00ea\u0005l\u0000"+
		"\u0000\u00ea\u00eb\u0005e\u0000\u0000\u00eb.\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ed\u0005b\u0000\u0000\u00ed\u00ee\u0005r\u0000\u0000\u00ee\u00ef\u0005"+
		"e\u0000\u0000\u00ef\u00f0\u0005a\u0000\u0000\u00f0\u00f1\u0005k\u0000"+
		"\u0000\u00f10\u0001\u0000\u0000\u0000\u00f2\u00f3\u0005c\u0000\u0000\u00f3"+
		"\u00f4\u0005o\u0000\u0000\u00f4\u00f5\u0005n\u0000\u0000\u00f5\u00f6\u0005"+
		"t\u0000\u0000\u00f6\u00f7\u0005i\u0000\u0000\u00f7\u00f8\u0005n\u0000"+
		"\u0000\u00f8\u00f9\u0005u\u0000\u0000\u00f9\u00fa\u0005e\u0000\u0000\u00fa"+
		"2\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005r\u0000\u0000\u00fc\u00fd\u0005"+
		"e\u0000\u0000\u00fd\u00fe\u0005t\u0000\u0000\u00fe\u00ff\u0005u\u0000"+
		"\u0000\u00ff\u0100\u0005r\u0000\u0000\u0100\u0101\u0005n\u0000\u0000\u0101"+
		"4\u0001\u0000\u0000\u0000\u0102\u0103\u0005+\u0000\u0000\u01036\u0001"+
		"\u0000\u0000\u0000\u0104\u0105\u0005-\u0000\u0000\u01058\u0001\u0000\u0000"+
		"\u0000\u0106\u0107\u0005*\u0000\u0000\u0107:\u0001\u0000\u0000\u0000\u0108"+
		"\u0109\u0005/\u0000\u0000\u0109<\u0001\u0000\u0000\u0000\u010a\u010b\u0005"+
		"%\u0000\u0000\u010b>\u0001\u0000\u0000\u0000\u010c\u010d\u0005>\u0000"+
		"\u0000\u010d@\u0001\u0000\u0000\u0000\u010e\u010f\u0005<\u0000\u0000\u010f"+
		"B\u0001\u0000\u0000\u0000\u0110\u0111\u0005>\u0000\u0000\u0111\u0112\u0005"+
		"=\u0000\u0000\u0112D\u0001\u0000\u0000\u0000\u0113\u0114\u0005<\u0000"+
		"\u0000\u0114\u0115\u0005=\u0000\u0000\u0115F\u0001\u0000\u0000\u0000\u0116"+
		"\u0117\u0005=\u0000\u0000\u0117\u0118\u0005=\u0000\u0000\u0118H\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0005!\u0000\u0000\u011a\u011b\u0005=\u0000"+
		"\u0000\u011bJ\u0001\u0000\u0000\u0000\u011c\u011d\u0005&\u0000\u0000\u011d"+
		"\u011e\u0005&\u0000\u0000\u011eL\u0001\u0000\u0000\u0000\u011f\u0120\u0005"+
		"|\u0000\u0000\u0120\u0121\u0005|\u0000\u0000\u0121N\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0005!\u0000\u0000\u0123P\u0001\u0000\u0000\u0000\u0124"+
		"\u0125\u0005<\u0000\u0000\u0125\u0126\u0005<\u0000\u0000\u0126R\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0005>\u0000\u0000\u0128\u0129\u0005>\u0000"+
		"\u0000\u0129T\u0001\u0000\u0000\u0000\u012a\u012b\u0005&\u0000\u0000\u012b"+
		"V\u0001\u0000\u0000\u0000\u012c\u012d\u0005|\u0000\u0000\u012dX\u0001"+
		"\u0000\u0000\u0000\u012e\u012f\u0005^\u0000\u0000\u012fZ\u0001\u0000\u0000"+
		"\u0000\u0130\u0131\u0005~\u0000\u0000\u0131\\\u0001\u0000\u0000\u0000"+
		"\u0132\u0133\u0005=\u0000\u0000\u0133^\u0001\u0000\u0000\u0000\u0134\u0135"+
		"\u0005+\u0000\u0000\u0135\u0136\u0005+\u0000\u0000\u0136`\u0001\u0000"+
		"\u0000\u0000\u0137\u0138\u0005-\u0000\u0000\u0138\u0139\u0005-\u0000\u0000"+
		"\u0139b\u0001\u0000\u0000\u0000\u013a\u013b\u0005.\u0000\u0000\u013bd"+
		"\u0001\u0000\u0000\u0000\u013c\u013d\u0005,\u0000\u0000\u013df\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0005;\u0000\u0000\u013fh\u0001\u0000\u0000\u0000"+
		"\u0140\u0141\u0005{\u0000\u0000\u0141\u0142\u00064\u0000\u0000\u0142j"+
		"\u0001\u0000\u0000\u0000\u0143\u0144\u0005}\u0000\u0000\u0144\u0145\u0006"+
		"5\u0001\u0000\u0145l\u0001\u0000\u0000\u0000\u0146\u0147\u0005/\u0000"+
		"\u0000\u0147\u0148\u0005/\u0000\u0000\u0148\u014c\u0001\u0000\u0000\u0000"+
		"\u0149\u014b\b\u0001\u0000\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014b"+
		"\u014e\u0001\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c"+
		"\u014d\u0001\u0000\u0000\u0000\u014d\u014f\u0001\u0000\u0000\u0000\u014e"+
		"\u014c\u0001\u0000\u0000\u0000\u014f\u0150\u00066\u0002\u0000\u0150n\u0001"+
		"\u0000\u0000\u0000\u0151\u0152\u0005/\u0000\u0000\u0152\u0153\u0005*\u0000"+
		"\u0000\u0153\u0157\u0001\u0000\u0000\u0000\u0154\u0156\t\u0000\u0000\u0000"+
		"\u0155\u0154\u0001\u0000\u0000\u0000\u0156\u0159\u0001\u0000\u0000\u0000"+
		"\u0157\u0158\u0001\u0000\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000"+
		"\u0158\u015a\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000\u0000\u0000"+
		"\u015a\u015b\u0005*\u0000\u0000\u015b\u015c\u0005/\u0000\u0000\u015c\u015d"+
		"\u0001\u0000\u0000\u0000\u015d\u015e\u00067\u0002\u0000\u015ep\u0001\u0000"+
		"\u0000\u0000\u015f\u0161\u0007\u0002\u0000\u0000\u0160\u015f\u0001\u0000"+
		"\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162\u0160\u0001\u0000"+
		"\u0000\u0000\u0162\u0163\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000"+
		"\u0000\u0000\u0164\u0165\u00068\u0002\u0000\u0165r\u0001\u0000\u0000\u0000"+
		"\u0166\u016a\u0007\u0003\u0000\u0000\u0167\u0169\u0007\u0004\u0000\u0000"+
		"\u0168\u0167\u0001\u0000\u0000\u0000\u0169\u016c\u0001\u0000\u0000\u0000"+
		"\u016a\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000"+
		"\u016b\u016f\u0001\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000"+
		"\u016d\u016f\u00050\u0000\u0000\u016e\u0166\u0001\u0000\u0000\u0000\u016e"+
		"\u016d\u0001\u0000\u0000\u0000\u016ft\u0001\u0000\u0000\u0000\u0170\u0174"+
		"\u0007\u0005\u0000\u0000\u0171\u0173\u0007\u0006\u0000\u0000\u0172\u0171"+
		"\u0001\u0000\u0000\u0000\u0173\u0176\u0001\u0000\u0000\u0000\u0174\u0172"+
		"\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175v\u0001"+
		"\u0000\u0000\u0000\u0176\u0174\u0001\u0000\u0000\u0000\u0177\u0181\b\u0007"+
		"\u0000\u0000\u0178\u0179\u0005\\\u0000\u0000\u0179\u0181\u0005\"\u0000"+
		"\u0000\u017a\u017b\u0005\\\u0000\u0000\u017b\u0181\u0005n\u0000\u0000"+
		"\u017c\u017d\u0005\\\u0000\u0000\u017d\u0181\u0005r\u0000\u0000\u017e"+
		"\u017f\u0005\\\u0000\u0000\u017f\u0181\u0005\\\u0000\u0000\u0180\u0177"+
		"\u0001\u0000\u0000\u0000\u0180\u0178\u0001\u0000\u0000\u0000\u0180\u017a"+
		"\u0001\u0000\u0000\u0000\u0180\u017c\u0001\u0000\u0000\u0000\u0180\u017e"+
		"\u0001\u0000\u0000\u0000\u0181x\u0001\u0000\u0000\u0000\u0182\u0183\u0004"+
		"<\u0002\u0000\u0183\u018f\u0005\"\u0000\u0000\u0184\u018e\b\u0007\u0000"+
		"\u0000\u0185\u0186\u0005\\\u0000\u0000\u0186\u018e\u0005\"\u0000\u0000"+
		"\u0187\u0188\u0005\\\u0000\u0000\u0188\u018e\u0005n\u0000\u0000\u0189"+
		"\u018a\u0005\\\u0000\u0000\u018a\u018e\u0005r\u0000\u0000\u018b\u018c"+
		"\u0005\\\u0000\u0000\u018c\u018e\u0005\\\u0000\u0000\u018d\u0184\u0001"+
		"\u0000\u0000\u0000\u018d\u0185\u0001\u0000\u0000\u0000\u018d\u0187\u0001"+
		"\u0000\u0000\u0000\u018d\u0189\u0001\u0000\u0000\u0000\u018d\u018b\u0001"+
		"\u0000\u0000\u0000\u018e\u0191\u0001\u0000\u0000\u0000\u018f\u018d\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0192\u0001"+
		"\u0000\u0000\u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0192\u0193\u0005"+
		"\"\u0000\u0000\u0193z\u0001\u0000\u0000\u0000\u0194\u0195\u0005f\u0000"+
		"\u0000\u0195\u0196\u0005\"\u0000\u0000\u0196\u0198\u0001\u0000\u0000\u0000"+
		"\u0197\u0199\u0003\u0011\b\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198"+
		"\u0199\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a"+
		"\u019b\u0005\"\u0000\u0000\u019b|\u0001\u0000\u0000\u0000\u019c\u019d"+
		"\u0005f\u0000\u0000\u019d\u019e\u0005\"\u0000\u0000\u019e\u01a0\u0001"+
		"\u0000\u0000\u0000\u019f\u01a1\u0003\u0011\b\u0000\u01a0\u019f\u0001\u0000"+
		"\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000"+
		"\u0000\u0000\u01a2\u01a3\u0006>\u0003\u0000\u01a3~\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a6\u0004?\u0003\u0000\u01a5\u01a7\u0003\u0011\b\u0000\u01a6"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7"+
		"\u01a8\u0001\u0000\u0000\u0000\u01a8\u01a9\u0005\"\u0000\u0000\u01a9\u01aa"+
		"\u0006?\u0004\u0000\u01aa\u0080\u0001\u0000\u0000\u0000\u000f\u0000\u009e"+
		"\u00a4\u014c\u0157\u0162\u016a\u016e\u0174\u0180\u018d\u018f\u0198\u01a0"+
		"\u01a6\u0005\u00014\u0000\u00015\u0001\u0006\u0000\u0000\u0001>\u0002"+
		"\u0001?\u0003";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}