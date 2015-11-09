package org.cfeclipse.ide.ui.text;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.lang.tooling.parser.lexer.WordLexerRule;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlStartTagBeginScanner extends AbstractLangScanner {

	public CfmlStartTagBeginScanner(TokenRegistry tokenRegistry) {
		super(tokenRegistry);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken tagToken = getToken(CfmlColorPreferences.CF_TAG);
		IToken stringToken = getToken(CfmlColorPreferences.CF_STRING);
		IToken keywordToken = getToken(CfmlColorPreferences.CFKEYWORD);
		

		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("\'", "\'", stringToken));
		//CfmlWordLexerRule<IToken> codeLexerRule = new CfmlWordLexerRule<IToken>(Token.WHITESPACE, keywordToken);
		//rules.add(new LexingRule_RuleAdapter(codeLexerRule));
		
		// For dev purposes consider only the cfset tag
		
		
		
	}

	@Override
	protected IToken doNextToken() {
		
		System.out.println("in doNextToken");
		// TODO Auto-generated method stub
		IToken foo = super.doNextToken();
		System.out.println(foo.toString());
		return foo;
	}

}
