package org.cfeclipse.tooling.lexer;

import melnorme.lang.tooling.parser.lexer.WordLexerRule;
import melnorme.lang.utils.parse.ICharacterReader;

public class CfmlWordLexerRule<TOKEN> extends WordLexerRule<TOKEN> {
	
	public static final String[] keywords_control = { 
			"abstract", "alignof", "as", "become", "box", "break", "const", "continue", "crate", 
			"do", "else", "enum", "extern", "final", "fn", "for", "if", "impl", "in", "let", 
			"loop", "macro", "match", "mod", "move", "mut", "offsetof", "override", 
			"priv", "proc", "pub", "pure", "ref", "return", "sizeof", "static", "struct", 
			"trait", "type", "typeof", "unsafe", "unsized", "use", "virtual", "where", "while", "yield" 
	};
	public static final String[] keywords_boolean_lit = { 
			"true", "false",
	};
	public static final String[] keywords_self = { 
			"self", "Self", "super"
	};
	
	public static final String[] tag_names = { 
			"cfinvoke", "cfsetting", "cfoutput"
	};	
	/* -----------------  ----------------- */
	
	//protected final RustNumberLexingRule rustNumberLexingRule = new RustNumberLexingRule();
	
	//protected final TOKEN macroCall;
	//protected final TOKEN numberLiteral;
	
	public CfmlWordLexerRule(
			TOKEN whitespaceToken, 
			TOKEN tagNames,
			TOKEN word
			) {
		super(whitespaceToken, word);
		
		addKeywords(tagNames, CfmlWordLexerRule.tag_names);
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