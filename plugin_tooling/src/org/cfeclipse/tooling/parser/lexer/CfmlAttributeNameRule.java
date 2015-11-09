package org.cfeclipse.tooling.parser.lexer;

import melnorme.lang.tooling.parser.lexer.IPredicateLexingRule;
import melnorme.lang.utils.parse.ICharacterReader;
import melnorme.lang.utils.parse.LexingUtils;

public class CfmlAttributeNameRule implements IPredicateLexingRule {

	public CfmlAttributeNameRule() {
		super();
	}

	@Override
	public boolean doEvaluate(ICharacterReader reader) {

		String word = LexingUtils.readJavaIdentifier(reader);
		if (!word.isEmpty()) {
			LexingUtils.skipWhitespace(reader);
			if (reader.lookaheadMatches("=")) {
				return true;
			}
		}
		return false;
	}

}