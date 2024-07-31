// Generated from /mnt/d/周宸源/大学/学习/程序/MxCompiler/src/antlr/Mxlexer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MxlexerLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Void=1, Bool=2, Int=3, String=4, New=5, Class=6, True=7, False=8, This=9, 
		If=10, Else=11, For=12, While=13, Break=14, Continue=15, Return=16, SmallLetter=17, 
		CapitalLetter=18, Number=19, Interger=20, Identifier=21, StringElement=22, 
		Comment=23, BlockComment=24, Whitespace=25, Plus=26, Minus=27, Multiply=28, 
		Divide=29, Mod=30, Greater=31, Less=32, GreaterEqual=33, LessEqual=34, 
		Equal=35, InEqual=36, And=37, Or=38, Not=39, LeftMove=40, RightMove=41, 
		AndBit=42, OrBit=43, XorBit=44, NotBit=45, Assign=46, AddAssign=47, SubAssign=48, 
		MulAssign=49, DivAssign=50, ModAssign=51, LeftMoveAssign=52, RightMoveAssign=53, 
		AndAssign=54, OrAssign=55, XorAssign=56, AssignType=57, SelfPlus=58, SelfMinus=59, 
		Member=60, LeftBracket=61, RightBracket=62, LeftSquareBracket=63, RightSquareBracket=64, 
		LeftBrace=65, RightBrace=66, Comma=67, Semicolon=68, Colon=69, QestionMark=70;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Void", "Bool", "Int", "String", "New", "Class", "True", "False", "This", 
			"If", "Else", "For", "While", "Break", "Continue", "Return", "SmallLetter", 
			"CapitalLetter", "Number", "Interger", "Identifier", "StringElement", 
			"Comment", "BlockComment", "Whitespace", "Plus", "Minus", "Multiply", 
			"Divide", "Mod", "Greater", "Less", "GreaterEqual", "LessEqual", "Equal", 
			"InEqual", "And", "Or", "Not", "LeftMove", "RightMove", "AndBit", "OrBit", 
			"XorBit", "NotBit", "Assign", "AddAssign", "SubAssign", "MulAssign", 
			"DivAssign", "ModAssign", "LeftMoveAssign", "RightMoveAssign", "AndAssign", 
			"OrAssign", "XorAssign", "AssignType", "SelfPlus", "SelfMinus", "Member", 
			"LeftBracket", "RightBracket", "LeftSquareBracket", "RightSquareBracket", 
			"LeftBrace", "RightBrace", "Comma", "Semicolon", "Colon", "QestionMark"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'void'", "'bool'", "'int'", "'string'", "'new'", "'class'", "'true'", 
			"'false'", "'this'", "'if'", "'else'", "'for'", "'while'", "'break'", 
			"'continue'", "'return'", null, null, null, null, null, null, null, null, 
			null, "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", 
			"'=='", "'!='", "'&&'", "'||'", "'!'", "'<<'", "'>>'", "'&'", "'|'", 
			"'^'", "'~'", "'='", "'+='", "'-='", "'*='", "'/='", "'%='", "'<<='", 
			"'>>='", "'&='", "'|='", "'^='", null, "'++'", "'--'", "'.'", "'('", 
			"')'", "'['", "']'", "'{'", "'}'", "','", "';'", "':'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Void", "Bool", "Int", "String", "New", "Class", "True", "False", 
			"This", "If", "Else", "For", "While", "Break", "Continue", "Return", 
			"SmallLetter", "CapitalLetter", "Number", "Interger", "Identifier", "StringElement", 
			"Comment", "BlockComment", "Whitespace", "Plus", "Minus", "Multiply", 
			"Divide", "Mod", "Greater", "Less", "GreaterEqual", "LessEqual", "Equal", 
			"InEqual", "And", "Or", "Not", "LeftMove", "RightMove", "AndBit", "OrBit", 
			"XorBit", "NotBit", "Assign", "AddAssign", "SubAssign", "MulAssign", 
			"DivAssign", "ModAssign", "LeftMoveAssign", "RightMoveAssign", "AndAssign", 
			"OrAssign", "XorAssign", "AssignType", "SelfPlus", "SelfMinus", "Member", 
			"LeftBracket", "RightBracket", "LeftSquareBracket", "RightSquareBracket", 
			"LeftBrace", "RightBrace", "Comma", "Semicolon", "Colon", "QestionMark"
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


	public MxlexerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mxlexer.g4"; }

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

	public static final String _serializedATN =
		"\u0004\u0000F\u01ab\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
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
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007"+
		"D\u0002E\u0007E\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u00ed\b\u0013"+
		"\n\u0013\f\u0013\u00f0\t\u0013\u0001\u0013\u0003\u0013\u00f3\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u00f7\b\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0005\u0014\u00fd\b\u0014\n\u0014\f\u0014\u0100\t\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u010a\b\u0015\n\u0015\f\u0015\u010d"+
		"\t\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0115\b\u0016\n\u0016\f\u0016\u0118\t\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u0120\b\u0017\n\u0017\f\u0017\u0123\t\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0004\u0018\u012b\b\u0018\u000b"+
		"\u0018\f\u0018\u012c\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		" \u0001 \u0001 \u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001)\u0001)\u0001*\u0001*\u0001"+
		"+\u0001+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001.\u0001/\u0001"+
		"/\u0001/\u00010\u00010\u00010\u00011\u00011\u00011\u00012\u00012\u0001"+
		"2\u00013\u00013\u00013\u00013\u00014\u00014\u00014\u00014\u00015\u0001"+
		"5\u00015\u00016\u00016\u00016\u00017\u00017\u00017\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00038\u018e"+
		"\b8\u00019\u00019\u00019\u0001:\u0001:\u0001:\u0001;\u0001;\u0001<\u0001"+
		"<\u0001=\u0001=\u0001>\u0001>\u0001?\u0001?\u0001@\u0001@\u0001A\u0001"+
		"A\u0001B\u0001B\u0001C\u0001C\u0001D\u0001D\u0001E\u0001E\u0001\u0121"+
		"\u0000F\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\""+
		"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s:u;w<y={>}?\u007f@\u0081"+
		"A\u0083B\u0085C\u0087D\u0089E\u008bF\u0001\u0000\u0007\u0001\u0000az\u0001"+
		"\u0000AZ\u0001\u000009\u0001\u000019\u0004\u0000\n\n\r\r\"\"\\\\\u0002"+
		"\u0000\n\n\r\r\u0003\u0000\t\n\r\r  \u01c2\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?"+
		"\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000"+
		"\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000"+
		"\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M"+
		"\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000"+
		"\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000"+
		"\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000["+
		"\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000"+
		"\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000"+
		"\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i"+
		"\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000"+
		"\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000"+
		"\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w"+
		"\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000"+
		"\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001\u0000\u0000"+
		"\u0000\u0000\u0081\u0001\u0000\u0000\u0000\u0000\u0083\u0001\u0000\u0000"+
		"\u0000\u0000\u0085\u0001\u0000\u0000\u0000\u0000\u0087\u0001\u0000\u0000"+
		"\u0000\u0000\u0089\u0001\u0000\u0000\u0000\u0000\u008b\u0001\u0000\u0000"+
		"\u0000\u0001\u008d\u0001\u0000\u0000\u0000\u0003\u0092\u0001\u0000\u0000"+
		"\u0000\u0005\u0097\u0001\u0000\u0000\u0000\u0007\u009b\u0001\u0000\u0000"+
		"\u0000\t\u00a2\u0001\u0000\u0000\u0000\u000b\u00a6\u0001\u0000\u0000\u0000"+
		"\r\u00ac\u0001\u0000\u0000\u0000\u000f\u00b1\u0001\u0000\u0000\u0000\u0011"+
		"\u00b7\u0001\u0000\u0000\u0000\u0013\u00bc\u0001\u0000\u0000\u0000\u0015"+
		"\u00bf\u0001\u0000\u0000\u0000\u0017\u00c4\u0001\u0000\u0000\u0000\u0019"+
		"\u00c8\u0001\u0000\u0000\u0000\u001b\u00ce\u0001\u0000\u0000\u0000\u001d"+
		"\u00d4\u0001\u0000\u0000\u0000\u001f\u00dd\u0001\u0000\u0000\u0000!\u00e4"+
		"\u0001\u0000\u0000\u0000#\u00e6\u0001\u0000\u0000\u0000%\u00e8\u0001\u0000"+
		"\u0000\u0000\'\u00f2\u0001\u0000\u0000\u0000)\u00f6\u0001\u0000\u0000"+
		"\u0000+\u0101\u0001\u0000\u0000\u0000-\u0110\u0001\u0000\u0000\u0000/"+
		"\u011b\u0001\u0000\u0000\u00001\u012a\u0001\u0000\u0000\u00003\u0130\u0001"+
		"\u0000\u0000\u00005\u0132\u0001\u0000\u0000\u00007\u0134\u0001\u0000\u0000"+
		"\u00009\u0136\u0001\u0000\u0000\u0000;\u0138\u0001\u0000\u0000\u0000="+
		"\u013a\u0001\u0000\u0000\u0000?\u013c\u0001\u0000\u0000\u0000A\u013e\u0001"+
		"\u0000\u0000\u0000C\u0141\u0001\u0000\u0000\u0000E\u0144\u0001\u0000\u0000"+
		"\u0000G\u0147\u0001\u0000\u0000\u0000I\u014a\u0001\u0000\u0000\u0000K"+
		"\u014d\u0001\u0000\u0000\u0000M\u0150\u0001\u0000\u0000\u0000O\u0152\u0001"+
		"\u0000\u0000\u0000Q\u0155\u0001\u0000\u0000\u0000S\u0158\u0001\u0000\u0000"+
		"\u0000U\u015a\u0001\u0000\u0000\u0000W\u015c\u0001\u0000\u0000\u0000Y"+
		"\u015e\u0001\u0000\u0000\u0000[\u0160\u0001\u0000\u0000\u0000]\u0162\u0001"+
		"\u0000\u0000\u0000_\u0165\u0001\u0000\u0000\u0000a\u0168\u0001\u0000\u0000"+
		"\u0000c\u016b\u0001\u0000\u0000\u0000e\u016e\u0001\u0000\u0000\u0000g"+
		"\u0171\u0001\u0000\u0000\u0000i\u0175\u0001\u0000\u0000\u0000k\u0179\u0001"+
		"\u0000\u0000\u0000m\u017c\u0001\u0000\u0000\u0000o\u017f\u0001\u0000\u0000"+
		"\u0000q\u018d\u0001\u0000\u0000\u0000s\u018f\u0001\u0000\u0000\u0000u"+
		"\u0192\u0001\u0000\u0000\u0000w\u0195\u0001\u0000\u0000\u0000y\u0197\u0001"+
		"\u0000\u0000\u0000{\u0199\u0001\u0000\u0000\u0000}\u019b\u0001\u0000\u0000"+
		"\u0000\u007f\u019d\u0001\u0000\u0000\u0000\u0081\u019f\u0001\u0000\u0000"+
		"\u0000\u0083\u01a1\u0001\u0000\u0000\u0000\u0085\u01a3\u0001\u0000\u0000"+
		"\u0000\u0087\u01a5\u0001\u0000\u0000\u0000\u0089\u01a7\u0001\u0000\u0000"+
		"\u0000\u008b\u01a9\u0001\u0000\u0000\u0000\u008d\u008e\u0005v\u0000\u0000"+
		"\u008e\u008f\u0005o\u0000\u0000\u008f\u0090\u0005i\u0000\u0000\u0090\u0091"+
		"\u0005d\u0000\u0000\u0091\u0002\u0001\u0000\u0000\u0000\u0092\u0093\u0005"+
		"b\u0000\u0000\u0093\u0094\u0005o\u0000\u0000\u0094\u0095\u0005o\u0000"+
		"\u0000\u0095\u0096\u0005l\u0000\u0000\u0096\u0004\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0005i\u0000\u0000\u0098\u0099\u0005n\u0000\u0000\u0099\u009a"+
		"\u0005t\u0000\u0000\u009a\u0006\u0001\u0000\u0000\u0000\u009b\u009c\u0005"+
		"s\u0000\u0000\u009c\u009d\u0005t\u0000\u0000\u009d\u009e\u0005r\u0000"+
		"\u0000\u009e\u009f\u0005i\u0000\u0000\u009f\u00a0\u0005n\u0000\u0000\u00a0"+
		"\u00a1\u0005g\u0000\u0000\u00a1\b\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0005n\u0000\u0000\u00a3\u00a4\u0005e\u0000\u0000\u00a4\u00a5\u0005w"+
		"\u0000\u0000\u00a5\n\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005c\u0000"+
		"\u0000\u00a7\u00a8\u0005l\u0000\u0000\u00a8\u00a9\u0005a\u0000\u0000\u00a9"+
		"\u00aa\u0005s\u0000\u0000\u00aa\u00ab\u0005s\u0000\u0000\u00ab\f\u0001"+
		"\u0000\u0000\u0000\u00ac\u00ad\u0005t\u0000\u0000\u00ad\u00ae\u0005r\u0000"+
		"\u0000\u00ae\u00af\u0005u\u0000\u0000\u00af\u00b0\u0005e\u0000\u0000\u00b0"+
		"\u000e\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005f\u0000\u0000\u00b2\u00b3"+
		"\u0005a\u0000\u0000\u00b3\u00b4\u0005l\u0000\u0000\u00b4\u00b5\u0005s"+
		"\u0000\u0000\u00b5\u00b6\u0005e\u0000\u0000\u00b6\u0010\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0005t\u0000\u0000\u00b8\u00b9\u0005h\u0000\u0000\u00b9"+
		"\u00ba\u0005i\u0000\u0000\u00ba\u00bb\u0005s\u0000\u0000\u00bb\u0012\u0001"+
		"\u0000\u0000\u0000\u00bc\u00bd\u0005i\u0000\u0000\u00bd\u00be\u0005f\u0000"+
		"\u0000\u00be\u0014\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005e\u0000\u0000"+
		"\u00c0\u00c1\u0005l\u0000\u0000\u00c1\u00c2\u0005s\u0000\u0000\u00c2\u00c3"+
		"\u0005e\u0000\u0000\u00c3\u0016\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005"+
		"f\u0000\u0000\u00c5\u00c6\u0005o\u0000\u0000\u00c6\u00c7\u0005r\u0000"+
		"\u0000\u00c7\u0018\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005w\u0000\u0000"+
		"\u00c9\u00ca\u0005h\u0000\u0000\u00ca\u00cb\u0005i\u0000\u0000\u00cb\u00cc"+
		"\u0005l\u0000\u0000\u00cc\u00cd\u0005e\u0000\u0000\u00cd\u001a\u0001\u0000"+
		"\u0000\u0000\u00ce\u00cf\u0005b\u0000\u0000\u00cf\u00d0\u0005r\u0000\u0000"+
		"\u00d0\u00d1\u0005e\u0000\u0000\u00d1\u00d2\u0005a\u0000\u0000\u00d2\u00d3"+
		"\u0005k\u0000\u0000\u00d3\u001c\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005"+
		"c\u0000\u0000\u00d5\u00d6\u0005o\u0000\u0000\u00d6\u00d7\u0005n\u0000"+
		"\u0000\u00d7\u00d8\u0005t\u0000\u0000\u00d8\u00d9\u0005i\u0000\u0000\u00d9"+
		"\u00da\u0005n\u0000\u0000\u00da\u00db\u0005u\u0000\u0000\u00db\u00dc\u0005"+
		"e\u0000\u0000\u00dc\u001e\u0001\u0000\u0000\u0000\u00dd\u00de\u0005r\u0000"+
		"\u0000\u00de\u00df\u0005e\u0000\u0000\u00df\u00e0\u0005t\u0000\u0000\u00e0"+
		"\u00e1\u0005u\u0000\u0000\u00e1\u00e2\u0005r\u0000\u0000\u00e2\u00e3\u0005"+
		"n\u0000\u0000\u00e3 \u0001\u0000\u0000\u0000\u00e4\u00e5\u0007\u0000\u0000"+
		"\u0000\u00e5\"\u0001\u0000\u0000\u0000\u00e6\u00e7\u0007\u0001\u0000\u0000"+
		"\u00e7$\u0001\u0000\u0000\u0000\u00e8\u00e9\u0007\u0002\u0000\u0000\u00e9"+
		"&\u0001\u0000\u0000\u0000\u00ea\u00ee\u0007\u0003\u0000\u0000\u00eb\u00ed"+
		"\u0007\u0002\u0000\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef"+
		"\u0001\u0000\u0000\u0000\u00ef\u00f3\u0001\u0000\u0000\u0000\u00f0\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f3\u00050\u0000\u0000\u00f2\u00ea\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f1\u0001\u0000\u0000\u0000\u00f3(\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f7\u0003!\u0010\u0000\u00f5\u00f7\u0003#\u0011\u0000"+
		"\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f6\u00f5\u0001\u0000\u0000\u0000"+
		"\u00f7\u00fe\u0001\u0000\u0000\u0000\u00f8\u00fd\u0003!\u0010\u0000\u00f9"+
		"\u00fd\u0003#\u0011\u0000\u00fa\u00fd\u0003%\u0012\u0000\u00fb\u00fd\u0005"+
		"_\u0000\u0000\u00fc\u00f8\u0001\u0000\u0000\u0000\u00fc\u00f9\u0001\u0000"+
		"\u0000\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fd\u0100\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000"+
		"\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff*\u0001\u0000\u0000"+
		"\u0000\u0100\u00fe\u0001\u0000\u0000\u0000\u0101\u010b\u0005\"\u0000\u0000"+
		"\u0102\u010a\b\u0004\u0000\u0000\u0103\u0104\u0005\\\u0000\u0000\u0104"+
		"\u010a\u0005\"\u0000\u0000\u0105\u0106\u0005\\\u0000\u0000\u0106\u010a"+
		"\u0005n\u0000\u0000\u0107\u0108\u0005\\\u0000\u0000\u0108\u010a\u0005"+
		"\\\u0000\u0000\u0109\u0102\u0001\u0000\u0000\u0000\u0109\u0103\u0001\u0000"+
		"\u0000\u0000\u0109\u0105\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000"+
		"\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000"+
		"\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u010e\u0001\u0000"+
		"\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u010f\u0005\"\u0000"+
		"\u0000\u010f,\u0001\u0000\u0000\u0000\u0110\u0111\u0005/\u0000\u0000\u0111"+
		"\u0112\u0005/\u0000\u0000\u0112\u0116\u0001\u0000\u0000\u0000\u0113\u0115"+
		"\b\u0005\u0000\u0000\u0114\u0113\u0001\u0000\u0000\u0000\u0115\u0118\u0001"+
		"\u0000\u0000\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0116\u0117\u0001"+
		"\u0000\u0000\u0000\u0117\u0119\u0001\u0000\u0000\u0000\u0118\u0116\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0006\u0016\u0000\u0000\u011a.\u0001\u0000"+
		"\u0000\u0000\u011b\u011c\u0005/\u0000\u0000\u011c\u011d\u0005*\u0000\u0000"+
		"\u011d\u0121\u0001\u0000\u0000\u0000\u011e\u0120\t\u0000\u0000\u0000\u011f"+
		"\u011e\u0001\u0000\u0000\u0000\u0120\u0123\u0001\u0000\u0000\u0000\u0121"+
		"\u0122\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122"+
		"\u0124\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0124"+
		"\u0125\u0005*\u0000\u0000\u0125\u0126\u0005/\u0000\u0000\u0126\u0127\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0006\u0017\u0000\u0000\u01280\u0001\u0000"+
		"\u0000\u0000\u0129\u012b\u0007\u0006\u0000\u0000\u012a\u0129\u0001\u0000"+
		"\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012a\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000"+
		"\u0000\u0000\u012e\u012f\u0006\u0018\u0000\u0000\u012f2\u0001\u0000\u0000"+
		"\u0000\u0130\u0131\u0005+\u0000\u0000\u01314\u0001\u0000\u0000\u0000\u0132"+
		"\u0133\u0005-\u0000\u0000\u01336\u0001\u0000\u0000\u0000\u0134\u0135\u0005"+
		"*\u0000\u0000\u01358\u0001\u0000\u0000\u0000\u0136\u0137\u0005/\u0000"+
		"\u0000\u0137:\u0001\u0000\u0000\u0000\u0138\u0139\u0005%\u0000\u0000\u0139"+
		"<\u0001\u0000\u0000\u0000\u013a\u013b\u0005>\u0000\u0000\u013b>\u0001"+
		"\u0000\u0000\u0000\u013c\u013d\u0005<\u0000\u0000\u013d@\u0001\u0000\u0000"+
		"\u0000\u013e\u013f\u0005>\u0000\u0000\u013f\u0140\u0005=\u0000\u0000\u0140"+
		"B\u0001\u0000\u0000\u0000\u0141\u0142\u0005<\u0000\u0000\u0142\u0143\u0005"+
		"=\u0000\u0000\u0143D\u0001\u0000\u0000\u0000\u0144\u0145\u0005=\u0000"+
		"\u0000\u0145\u0146\u0005=\u0000\u0000\u0146F\u0001\u0000\u0000\u0000\u0147"+
		"\u0148\u0005!\u0000\u0000\u0148\u0149\u0005=\u0000\u0000\u0149H\u0001"+
		"\u0000\u0000\u0000\u014a\u014b\u0005&\u0000\u0000\u014b\u014c\u0005&\u0000"+
		"\u0000\u014cJ\u0001\u0000\u0000\u0000\u014d\u014e\u0005|\u0000\u0000\u014e"+
		"\u014f\u0005|\u0000\u0000\u014fL\u0001\u0000\u0000\u0000\u0150\u0151\u0005"+
		"!\u0000\u0000\u0151N\u0001\u0000\u0000\u0000\u0152\u0153\u0005<\u0000"+
		"\u0000\u0153\u0154\u0005<\u0000\u0000\u0154P\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u0005>\u0000\u0000\u0156\u0157\u0005>\u0000\u0000\u0157R\u0001"+
		"\u0000\u0000\u0000\u0158\u0159\u0005&\u0000\u0000\u0159T\u0001\u0000\u0000"+
		"\u0000\u015a\u015b\u0005|\u0000\u0000\u015bV\u0001\u0000\u0000\u0000\u015c"+
		"\u015d\u0005^\u0000\u0000\u015dX\u0001\u0000\u0000\u0000\u015e\u015f\u0005"+
		"~\u0000\u0000\u015fZ\u0001\u0000\u0000\u0000\u0160\u0161\u0005=\u0000"+
		"\u0000\u0161\\\u0001\u0000\u0000\u0000\u0162\u0163\u0005+\u0000\u0000"+
		"\u0163\u0164\u0005=\u0000\u0000\u0164^\u0001\u0000\u0000\u0000\u0165\u0166"+
		"\u0005-\u0000\u0000\u0166\u0167\u0005=\u0000\u0000\u0167`\u0001\u0000"+
		"\u0000\u0000\u0168\u0169\u0005*\u0000\u0000\u0169\u016a\u0005=\u0000\u0000"+
		"\u016ab\u0001\u0000\u0000\u0000\u016b\u016c\u0005/\u0000\u0000\u016c\u016d"+
		"\u0005=\u0000\u0000\u016dd\u0001\u0000\u0000\u0000\u016e\u016f\u0005%"+
		"\u0000\u0000\u016f\u0170\u0005=\u0000\u0000\u0170f\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0005<\u0000\u0000\u0172\u0173\u0005<\u0000\u0000\u0173\u0174"+
		"\u0005=\u0000\u0000\u0174h\u0001\u0000\u0000\u0000\u0175\u0176\u0005>"+
		"\u0000\u0000\u0176\u0177\u0005>\u0000\u0000\u0177\u0178\u0005=\u0000\u0000"+
		"\u0178j\u0001\u0000\u0000\u0000\u0179\u017a\u0005&\u0000\u0000\u017a\u017b"+
		"\u0005=\u0000\u0000\u017bl\u0001\u0000\u0000\u0000\u017c\u017d\u0005|"+
		"\u0000\u0000\u017d\u017e\u0005=\u0000\u0000\u017en\u0001\u0000\u0000\u0000"+
		"\u017f\u0180\u0005^\u0000\u0000\u0180\u0181\u0005=\u0000\u0000\u0181p"+
		"\u0001\u0000\u0000\u0000\u0182\u018e\u0003[-\u0000\u0183\u018e\u0003]"+
		".\u0000\u0184\u018e\u0003_/\u0000\u0185\u018e\u0003a0\u0000\u0186\u018e"+
		"\u0003c1\u0000\u0187\u018e\u0003e2\u0000\u0188\u018e\u0003g3\u0000\u0189"+
		"\u018e\u0003i4\u0000\u018a\u018e\u0003k5\u0000\u018b\u018e\u0003m6\u0000"+
		"\u018c\u018e\u0003o7\u0000\u018d\u0182\u0001\u0000\u0000\u0000\u018d\u0183"+
		"\u0001\u0000\u0000\u0000\u018d\u0184\u0001\u0000\u0000\u0000\u018d\u0185"+
		"\u0001\u0000\u0000\u0000\u018d\u0186\u0001\u0000\u0000\u0000\u018d\u0187"+
		"\u0001\u0000\u0000\u0000\u018d\u0188\u0001\u0000\u0000\u0000\u018d\u0189"+
		"\u0001\u0000\u0000\u0000\u018d\u018a\u0001\u0000\u0000\u0000\u018d\u018b"+
		"\u0001\u0000\u0000\u0000\u018d\u018c\u0001\u0000\u0000\u0000\u018er\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0005+\u0000\u0000\u0190\u0191\u0005+\u0000"+
		"\u0000\u0191t\u0001\u0000\u0000\u0000\u0192\u0193\u0005-\u0000\u0000\u0193"+
		"\u0194\u0005-\u0000\u0000\u0194v\u0001\u0000\u0000\u0000\u0195\u0196\u0005"+
		".\u0000\u0000\u0196x\u0001\u0000\u0000\u0000\u0197\u0198\u0005(\u0000"+
		"\u0000\u0198z\u0001\u0000\u0000\u0000\u0199\u019a\u0005)\u0000\u0000\u019a"+
		"|\u0001\u0000\u0000\u0000\u019b\u019c\u0005[\u0000\u0000\u019c~\u0001"+
		"\u0000\u0000\u0000\u019d\u019e\u0005]\u0000\u0000\u019e\u0080\u0001\u0000"+
		"\u0000\u0000\u019f\u01a0\u0005{\u0000\u0000\u01a0\u0082\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a2\u0005}\u0000\u0000\u01a2\u0084\u0001\u0000\u0000\u0000"+
		"\u01a3\u01a4\u0005,\u0000\u0000\u01a4\u0086\u0001\u0000\u0000\u0000\u01a5"+
		"\u01a6\u0005;\u0000\u0000\u01a6\u0088\u0001\u0000\u0000\u0000\u01a7\u01a8"+
		"\u0005:\u0000\u0000\u01a8\u008a\u0001\u0000\u0000\u0000\u01a9\u01aa\u0005"+
		"?\u0000\u0000\u01aa\u008c\u0001\u0000\u0000\u0000\f\u0000\u00ee\u00f2"+
		"\u00f6\u00fc\u00fe\u0109\u010b\u0116\u0121\u012c\u018d\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}