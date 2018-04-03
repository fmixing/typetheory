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
		LET=1, IN=2, EQUALS=3, OB=4, CB=5, LAMBDA=6, DOT=7, VAR=8, WS=9;
	public static final int
		RULE_let_expr = 0, RULE_expression = 1, RULE_application = 2, RULE_atomic = 3, 
		RULE_abstraction = 4;
	public static final String[] ruleNames = {
		"let_expr", "expression", "application", "atomic", "abstraction"
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
	public static class Let_exprContext extends ParserRuleContext {
		public Expression expr;
		public Token VAR;
		public Let_exprContext expr1;
		public Let_exprContext expr2;
		public ExpressionContext expr3;
		public TerminalNode LET() { return getToken(LambdaParser.LET, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode EQUALS() { return getToken(LambdaParser.EQUALS, 0); }
		public TerminalNode IN() { return getToken(LambdaParser.IN, 0); }
		public List<Let_exprContext> let_expr() {
			return getRuleContexts(Let_exprContext.class);
		}
		public Let_exprContext let_expr(int i) {
			return getRuleContext(Let_exprContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Let_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterLet_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitLet_expr(this);
		}
	}

	public final Let_exprContext let_expr() throws RecognitionException {
		Let_exprContext _localctx = new Let_exprContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_let_expr);
		try {
			setState(21);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				match(LET);
				setState(11);
				((Let_exprContext)_localctx).VAR = match(VAR);
				setState(12);
				match(EQUALS);
				setState(13);
				((Let_exprContext)_localctx).expr1 = let_expr();
				setState(14);
				match(IN);
				setState(15);
				((Let_exprContext)_localctx).expr2 = let_expr();
				 ((Let_exprContext)_localctx).expr =  new Let(new Variable((((Let_exprContext)_localctx).VAR!=null?((Let_exprContext)_localctx).VAR.getText():null)), ((Let_exprContext)_localctx).expr1.expr, ((Let_exprContext)_localctx).expr2.expr); 
				}
				break;
			case OB:
			case LAMBDA:
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				((Let_exprContext)_localctx).expr3 = expression();
				 ((Let_exprContext)_localctx).expr =  ((Let_exprContext)_localctx).expr3.expr; 
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
		enterRule(_localctx, 2, RULE_expression);
		try {
			setState(33);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				((ExpressionContext)_localctx).app2 = application(0);
				 ((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).app2.expr; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				((ExpressionContext)_localctx).abst2 = abstraction();
				 ((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).abst2.expr; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				((ExpressionContext)_localctx).app1 = application(0);
				setState(30);
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_application, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(36);
			((ApplicationContext)_localctx).atom2 = atomic();
			 ((ApplicationContext)_localctx).expr =  ((ApplicationContext)_localctx).atom2.expr; 
			}
			_ctx.stop = _input.LT(-1);
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
					setState(39);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(40);
					((ApplicationContext)_localctx).atom1 = atomic();
					 ((ApplicationContext)_localctx).expr =  new Application(((ApplicationContext)_localctx).app.expr, ((ApplicationContext)_localctx).atom1.expr); 
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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
		enterRule(_localctx, 6, RULE_atomic);
		try {
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OB:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(OB);
				setState(49);
				((AtomicContext)_localctx).exp = expression();
				setState(50);
				match(CB);
				 ((AtomicContext)_localctx).expr =  ((AtomicContext)_localctx).exp.expr; 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
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
		enterRule(_localctx, 8, RULE_abstraction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(LAMBDA);
			setState(58);
			((AbstractionContext)_localctx).VAR = match(VAR);
			setState(59);
			match(DOT);
			setState(60);
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
		case 2:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13B\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\5\2\30\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3$\n\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5:\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\2\3\6\7\2\4\6\b\n\2\2\2A\2"+
		"\27\3\2\2\2\4#\3\2\2\2\6%\3\2\2\2\b9\3\2\2\2\n;\3\2\2\2\f\r\7\3\2\2\r"+
		"\16\7\n\2\2\16\17\7\5\2\2\17\20\5\2\2\2\20\21\7\4\2\2\21\22\5\2\2\2\22"+
		"\23\b\2\1\2\23\30\3\2\2\2\24\25\5\4\3\2\25\26\b\2\1\2\26\30\3\2\2\2\27"+
		"\f\3\2\2\2\27\24\3\2\2\2\30\3\3\2\2\2\31\32\5\6\4\2\32\33\b\3\1\2\33$"+
		"\3\2\2\2\34\35\5\n\6\2\35\36\b\3\1\2\36$\3\2\2\2\37 \5\6\4\2 !\5\n\6\2"+
		"!\"\b\3\1\2\"$\3\2\2\2#\31\3\2\2\2#\34\3\2\2\2#\37\3\2\2\2$\5\3\2\2\2"+
		"%&\b\4\1\2&\'\5\b\5\2\'(\b\4\1\2(/\3\2\2\2)*\f\4\2\2*+\5\b\5\2+,\b\4\1"+
		"\2,.\3\2\2\2-)\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\7\3\2\2\2"+
		"\61/\3\2\2\2\62\63\7\6\2\2\63\64\5\4\3\2\64\65\7\7\2\2\65\66\b\5\1\2\66"+
		":\3\2\2\2\678\7\n\2\28:\b\5\1\29\62\3\2\2\29\67\3\2\2\2:\t\3\2\2\2;<\7"+
		"\b\2\2<=\7\n\2\2=>\7\t\2\2>?\5\4\3\2?@\b\6\1\2@\13\3\2\2\2\6\27#/9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}