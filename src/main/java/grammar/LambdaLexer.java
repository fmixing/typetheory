// Generated from src/main/java/grammar/Lambda.g4 by ANTLR 4.7.1

package grammar;
import lambda.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, IN=2, EQUALS=3, OB=4, CB=5, LAMBDA=6, DOT=7, VAR=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LET", "IN", "EQUALS", "OB", "CB", "LAMBDA", "DOT", "VAR", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'let'", "'in'", "'='", "'('", "')'", "'\\'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LET", "IN", "EQUALS", "OB", "CB", "LAMBDA", "DOT", "VAR", "WS"
	};
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


	public LambdaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lambda.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13\66\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\7\t)\n\t\f\t\16\t,\13\t\3\n\3\n\3\n\6\n\61\n\n\r\n\16\n\62\3\n\3\n\2"+
		"\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\3\2\5\3\2c|\6\2))\62;^"+
		"^c|\4\2\13\13\"\"\28\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25\3"+
		"\2\2\2\5\31\3\2\2\2\7\34\3\2\2\2\t\36\3\2\2\2\13 \3\2\2\2\r\"\3\2\2\2"+
		"\17$\3\2\2\2\21&\3\2\2\2\23\60\3\2\2\2\25\26\7n\2\2\26\27\7g\2\2\27\30"+
		"\7v\2\2\30\4\3\2\2\2\31\32\7k\2\2\32\33\7p\2\2\33\6\3\2\2\2\34\35\7?\2"+
		"\2\35\b\3\2\2\2\36\37\7*\2\2\37\n\3\2\2\2 !\7+\2\2!\f\3\2\2\2\"#\7^\2"+
		"\2#\16\3\2\2\2$%\7\60\2\2%\20\3\2\2\2&*\t\2\2\2\')\t\3\2\2(\'\3\2\2\2"+
		"),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\22\3\2\2\2,*\3\2\2\2-\61\t\4\2\2./\7\17"+
		"\2\2/\61\7\f\2\2\60-\3\2\2\2\60.\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\b\n\2\2\65\24\3\2\2\2\6\2*\60\62\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}