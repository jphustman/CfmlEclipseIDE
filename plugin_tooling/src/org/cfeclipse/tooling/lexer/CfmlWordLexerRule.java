package org.cfeclipse.tooling.lexer;

import melnorme.lang.tooling.parser.lexer.WordLexerRule;
import melnorme.lang.utils.parse.ICharacterReader;

public class CfmlWordLexerRule<TOKEN> extends WordLexerRule<TOKEN> {
	
	public static final String[] keywords_boolean_lit = { 
			"true", "false",
	};
	
	public static final String[] keywords = { 
			"accessors",
			"catch", 
			"component",
			"else",
			"extends",
			"function",
			"if",
			"for",
			"param",
			"protected", 
			"private", 
			"public",
			"return",
			"try",
			"var"
	};	
	
	/* -----------------  ----------------- */
	
	//protected final RustNumberLexingRule rustNumberLexingRule = new RustNumberLexingRule();
	
	//protected final TOKEN macroCall;
	//protected final TOKEN numberLiteral;
	
	public CfmlWordLexerRule(
			TOKEN whitespaceToken, 
			TOKEN defaultWordToken,
			TOKEN keywordToken
			) {
		super(whitespaceToken, defaultWordToken);
		
		addKeywords(keywordToken, CfmlWordLexerRule.keywords);
	}
	
	@Override
	public TOKEN doEvaluateToken(ICharacterReader reader) {
		TOKEN result = super.doEvaluateToken(reader);
		
		/*
		if(result == null) {
			if(rustNumberLexingRule.tryMatch(reader)) {
				return numberLiteral;
			}
		}*/
/*		
		if(result != defaultWordToken) {
			return result;
		}
		// We found a word then. Let's see if it's a macro invocation
		
		CharacterReader_SubReader subReader = new CharacterReader_SubReader(reader); 
		LexingUtils.skipWhitespace(subReader);
		if(subReader.tryConsume('!')) {
			subReader.consumeInParentReader();
			return macroCall;
		} */
		return result;
	}
	
}