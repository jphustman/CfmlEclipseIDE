package org.cfeclipse.ide.ui.text;

import melnorme.lang.tooling.parser.lexer.WordLexerRule;

public class CfmlWordLexerRule<TOKEN> extends WordLexerRule<TOKEN> {

	public static final String[] keywords = { 
			"var" 
	};

	public static final String[] operators = { 
			"neq", "eq", "is", "gt", "lt", "gte", "lte" 
	};	
	
	public CfmlWordLexerRule(
			TOKEN whitespaceToken,
			TOKEN defaultWordToken,
			TOKEN keywordToken,			
			TOKEN booleanLiteralToken, 
			TOKEN builtinScopeToken, 
			TOKEN numberLiteralToken,
			TOKEN operatorToken
			) {
		super(whitespaceToken, defaultWordToken);
		
		addKeywords(keywordToken, CfmlWordLexerRule.keywords);
		addKeywords(operatorToken, CfmlWordLexerRule.operators);
	}	
}
