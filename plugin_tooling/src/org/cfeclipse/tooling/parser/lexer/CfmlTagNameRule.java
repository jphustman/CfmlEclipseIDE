package org.cfeclipse.tooling.parser.lexer;

import melnorme.lang.tooling.parser.lexer.IPredicateLexingRule;
import melnorme.lang.utils.parse.ICharacterReader;
import melnorme.lang.utils.parse.LexingUtils;

public class CfmlTagNameRule implements IPredicateLexingRule {
	
	public CfmlTagNameRule() {
		super();
	}

	@Override
	public boolean doEvaluate(ICharacterReader reader) {

		if(reader.tryConsume("cf")) {
			String word = LexingUtils.readJavaIdentifier(reader);
			if (!word.isEmpty()) {
				return true;
			}
		}
		return false;
	}

}