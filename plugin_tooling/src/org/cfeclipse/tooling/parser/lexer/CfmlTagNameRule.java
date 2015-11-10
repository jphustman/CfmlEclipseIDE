package org.cfeclipse.tooling.parser.lexer;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class CfmlTagNameRule extends BaseTextScanningRule {
	
	public CfmlTagNameRule(final IToken theToken) {
		this.token = theToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = read(scanner);
		if (c == 'c' || c == 'C') {
			int f = read(scanner);
			if (f == 'f' || f == 'F') {
				advanceToNextNonLetter(scanner);
				if (charsRead > 2) {
					return token;
				}
			}
		}
		rewind(scanner, charsRead);
		return Token.UNDEFINED;
	}

}
