package org.cfeclipse.ide.ui.text;

import melnorme.lang.tooling.parser.lexer.WordLexerRule;

public class CfmlWordLexerRule<TOKEN> extends WordLexerRule<TOKEN> {

	public static final String[] keywords = { 
			"var" 
	};
	
	public CfmlWordLexerRule(TOKEN whitespaceToken, TOKEN defaultWordToken) {
		super(whitespaceToken, defaultWordToken);
		addKeywords(defaultWordToken, CfmlWordLexerRule.keywords);
	}

}
