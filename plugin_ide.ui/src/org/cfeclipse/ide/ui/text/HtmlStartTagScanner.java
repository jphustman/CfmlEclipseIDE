package org.cfeclipse.ide.ui.text;

import org.cfeclipse.tooling.parser.lexer.CfmlAttributeNameRule;
import org.cfeclipse.tooling.parser.lexer.CfmlTagNameRule;
import org.cfeclipse.tooling.parser.lexer.HtmlTagNameRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class HtmlStartTagScanner extends AbstractLangScanner {

	public HtmlStartTagScanner(TokenRegistry tokenRegistry) {
		super(tokenRegistry);
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken tagToken = getToken(CfmlColorPreferences.HTML_TAG);
		IToken stringToken = getToken(CfmlColorPreferences.CF_STRING);
		IToken keywordToken = getToken(CfmlColorPreferences.CFKEYWORD);
		IToken attributeNameToken = getToken(CfmlColorPreferences.HTML_TAG_ATTRIBUTE);
		IToken defaultToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		IToken operatorToken = getToken(CfmlColorPreferences.CFOPERATOR);
		
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("\'", "\'", stringToken));
		//rules.add(new MultiLineRule("<", ">", tagToken));
		//rules.add(new HtmlTagNameRule(tagToken));		
		//rules.add(new CfmlAttributeNameRule(attributeNameToken));	
			
		CfmlWordLexerRule<IToken> codeLexerRule = new CfmlWordLexerRule<IToken>(Token.WHITESPACE, defaultToken, keywordToken, 
				null, null, null, operatorToken);
		rules.add(new LexingRule_RuleAdapter(codeLexerRule));
	}

	
	
}
