package org.cfeclipse.tooling.parser.lexer;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class HtmlTagNameRule extends BaseTextScanningRule {
	
	public HtmlTagNameRule(final IToken theToken) {
		this.token = theToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int firstChar = read(scanner);
				
		if (firstChar == '<') { //Character.isLetter(firstChar)) {
			advanceToNextNonLetter(scanner);
			if (charsRead > 1) {
				return token;
			}
		} else {
			if (firstChar == '>') {
				return token;
			}
		}
		unread(scanner);
		return Token.UNDEFINED;
	}

}
