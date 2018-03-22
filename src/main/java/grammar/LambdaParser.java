// Generated from src/main/java/grammar/Lambda.g4 by ANTLR 4.7.1

package grammar;
import lambda.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OB=1, CB=2, LAMBDA=3, DOT=4, VAR=5, WS=6;
	public static final int
		RULE_expression = 0, RULE_application = 1, RULE_atomic = 2, RULE_abstraction = 3;
	public static final String[] ruleNames = {
		"expression", "application", "atomic", "abstraction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'\\'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OB", "CB", "LAMBDA", "DOT", "VAR", "WS"
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

	@Override
	public String getGrammarFileName() { return "Lambda.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LambdaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Expression expr;
		public ApplicationContext app2;
		public AbstractionContext abst2;
		public ApplicationContext app1;
		public AbstractionContext abst1;
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public AbstractionContext abstraction() {
			return getRuleContext(AbstractionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(18);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				((ExpressionContext)_localctx).app2 = application(0);
				 ((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).app2.expr; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				((ExpressionContext)_localctx).abst2 = abstraction();
				 ((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).abst2.expr; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				((ExpressionContext)_localctx).app1 = application(0);
				setState(15);
				((ExpressionContext)_localctx).abst1 = abstraction();
				 ((ExpressionContext)_localctx).expr =  new Application(((ExpressionContext)_localctx).app1.expr, ((ExpressionContext)_localctx).abst1.expr); 
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

	public static class ApplicationContext extends ParserRuleContext {
		public Expression expr;
		public ApplicationContext app;
		public AtomicContext atom2;
		public AtomicContext atom1;
		public AtomicContext atomic() {
			return getRuleContext(AtomicContext.class,0);
		}
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public ApplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_application; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterApplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitApplication(this);
		}
	}

	public final ApplicationContext application() throws RecognitionException {
		return application(0);
	}

	private ApplicationContext application(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ApplicationContext _localctx = new ApplicationContext(_ctx, _parentState);
		ApplicationContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_application, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(21);
			((ApplicationContext)_localctx).atom2 = atomic();
			 ((ApplicationContext)_localctx).expr =  ((ApplicationContext)_localctx).atom2.expr; 
			}
			_ctx.stop = _input.LT(-1);
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ApplicationContext(_parentctx, _parentState);
					_localctx.app = _prevctx;
					_localctx.app = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_application);
					setState(24);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(25);
					((ApplicationContext)_localctx).atom1 = atomic();
					 ((ApplicationContext)_localctx).expr =  new Application(((ApplicationContext)_localctx).app.expr, ((ApplicationContext)_localctx).atom1.expr); 
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class AtomicContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionContext exp;
		public Token VAR;
		public TerminalNode OB() { return getToken(LambdaParser.OB, 0); }
		public TerminalNode CB() { return getToken(LambdaParser.CB, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public AtomicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAtomic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAtomic(this);
		}
	}

	public final AtomicContext atomic() throws RecognitionException {
		AtomicContext _localctx = new AtomicContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_atomic);
		try {
			setState(40);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OB:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				match(OB);
				setState(34);
				((AtomicContext)_localctx).exp = expression();
				setState(35);
				match(CB);
				 ((AtomicContext)_localctx).expr =  ((AtomicContext)_localctx).exp.expr; 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				((AtomicContext)_localctx).VAR = match(VAR);
				 ((AtomicContext)_localctx).expr =  new Variable((((AtomicContext)_localctx).VAR!=null?((AtomicContext)_localctx).VAR.getText():null)); 
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

	public static class AbstractionContext extends ParserRuleContext {
		public Expression expr;
		public Token VAR;
		public ExpressionContext exp;
		public TerminalNode LAMBDA() { return getToken(LambdaParser.LAMBDA, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode DOT() { return getToken(LambdaParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AbstractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstraction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAbstraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAbstraction(this);
		}
	}

	public final AbstractionContext abstraction() throws RecognitionException {
		AbstractionContext _localctx = new AbstractionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_abstraction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(LAMBDA);
			setState(43);
			((AbstractionContext)_localctx).VAR = match(VAR);
			setState(44);
			match(DOT);
			setState(45);
			((AbstractionContext)_localctx).exp = expression();
			 ((AbstractionContext)_localctx).expr =  new Abstraction(new Variable((((AbstractionContext)_localctx).VAR!=null?((AbstractionContext)_localctx).VAR.getText():null)), ((AbstractionContext)_localctx).exp.expr); 
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
		case 1:
			return application_sempred((ApplicationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean application_sempred(ApplicationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\b\63\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\25\n"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\5\4+\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\3\4\6\2\4"+
		"\6\b\2\2\2\62\2\24\3\2\2\2\4\26\3\2\2\2\6*\3\2\2\2\b,\3\2\2\2\n\13\5\4"+
		"\3\2\13\f\b\2\1\2\f\25\3\2\2\2\r\16\5\b\5\2\16\17\b\2\1\2\17\25\3\2\2"+
		"\2\20\21\5\4\3\2\21\22\5\b\5\2\22\23\b\2\1\2\23\25\3\2\2\2\24\n\3\2\2"+
		"\2\24\r\3\2\2\2\24\20\3\2\2\2\25\3\3\2\2\2\26\27\b\3\1\2\27\30\5\6\4\2"+
		"\30\31\b\3\1\2\31 \3\2\2\2\32\33\f\4\2\2\33\34\5\6\4\2\34\35\b\3\1\2\35"+
		"\37\3\2\2\2\36\32\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\5\3\2\2"+
		"\2\" \3\2\2\2#$\7\3\2\2$%\5\2\2\2%&\7\4\2\2&\'\b\4\1\2\'+\3\2\2\2()\7"+
		"\7\2\2)+\b\4\1\2*#\3\2\2\2*(\3\2\2\2+\7\3\2\2\2,-\7\5\2\2-.\7\7\2\2./"+
		"\7\6\2\2/\60\5\2\2\2\60\61\b\5\1\2\61\t\3\2\2\2\5\24 *";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}