package org.cfeclipse.tooling.parser.lexer;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class CfmlTagNameRule implements IRule {
	
	private final IToken token;
	private int charsRead = 0;
	
	public CfmlTagNameRule(final IToken theToken) {
		this.token = theToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		int c = read(scanner);
		if (c == 'c' || c == 'C') {
			int f = read(scanner);
			if (f == 'f' || f == 'F') {
				while(Character.isLetter(read(scanner))) {
					// consume
				}
				if (charsRead > 3) {
					unread(scanner); // back up from the one we found that isn't a letter
					return token;
				} 
			}
		}
		rewind(scanner, charsRead);
		return Token.UNDEFINED;
	}

	private void rewind(final ICharacterScanner scanner, int theCharsRead) {
		while (theCharsRead > 0) {
			theCharsRead--;
			unread(scanner);
		}
	}

	private void unread(final ICharacterScanner scanner) {
		scanner.unread();
		charsRead--;
	}

	private int read(final ICharacterScanner scanner) {
		final int c = scanner.read();
		charsRead++;
		return c;
	}

}
