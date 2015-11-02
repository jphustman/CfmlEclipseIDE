package org.cfeclipse.tooling.parser.lexer;

import melnorme.lang.tooling.parser.lexer.CharacterReader_SubReader;
import melnorme.lang.tooling.parser.lexer.WordLexerRule;
import melnorme.lang.utils.parse.ICharacterReader;
import melnorme.lang.utils.parse.LexingUtils;

/**
 * @author Andrew Myers
 */
public class CfmlWordLexerRule<TOKEN> extends WordLexerRule<TOKEN> {
	
	public static final String[] keywords_control = { 
			"abstract", "alignof", "as", "become", "box", "break", "const", "continue", "crate", 
			"do", "else", "enum", "extern", "final", "function", "for", "if", "impl", "in", "let", 
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
	
	/* -----------------  ----------------- */
	
	protected final CfmlNumberLexingRule cfmlNumberLexingRule = new CfmlNumberLexingRule();
	
	protected final TOKEN macroCall;
	protected final TOKEN numberLiteral;
	
	public CfmlWordLexerRule(
			TOKEN whitespaceToken, 
			TOKEN keywords, 
			TOKEN keywords_booleanLiteral, 
			TOKEN keywords_self, 
			TOKEN word,
			TOKEN macroCall, 
			TOKEN numberLiteral
			) {
		super(whitespaceToken, word);
		this.macroCall = macroCall;
		this.numberLiteral = numberLiteral;
		
		addKeywords(keywords, CfmlWordLexerRule.keywords_control);
		addKeywords(keywords_booleanLiteral, CfmlWordLexerRule.keywords_boolean_lit);
		addKeywords(keywords_self, CfmlWordLexerRule.keywords_self);
	}	
	
	@Override
	public TOKEN doEvaluateToken(ICharacterReader reader) {
		TOKEN result = super.doEvaluateToken(reader);
		
		if(result == null) {
			if(cfmlNumberLexingRule.tryMatch(reader)) {
				return numberLiteral;
			}
		}
		
		if(result != defaultWordToken) {
			return result;
		}
		// We found a word then. Let's see if it's a macro invocation
		
		CharacterReader_SubReader subReader = new CharacterReader_SubReader(reader); 
		LexingUtils.skipWhitespace(subReader);
		if(subReader.tryConsume('!')) {
			subReader.consumeInParentReader();
			return macroCall;
		}
		return result;
	}
		
}

