package org.cfeclipse.ide.ui.text;

import org.cfeclipse.tooling.lexer.CfmlTagNameLexerRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.Token;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlTagScanner extends AbstractLangScanner {
	
	public CfmlTagScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken stringToken = getToken(CfmlColorPreferences.CFML_STRING);
		IToken numberToken = getToken(CfmlColorPreferences.CFML_NUMBER);
		IToken cfmlTagToken = getToken(CfmlColorPreferences.CFML_TAG);
		IToken defaultToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("'","'",stringToken));

		CfmlTagNameLexerRule<IToken> codeLexerRule = new CfmlTagNameLexerRule<>(
				Token.WHITESPACE, 
				defaultToken,
				cfmlTagToken
			);
		rules.add(new LexingRule_RuleAdapter(codeLexerRule));		
		rules.add(new NumberRule(numberToken));				
	}
	
}
