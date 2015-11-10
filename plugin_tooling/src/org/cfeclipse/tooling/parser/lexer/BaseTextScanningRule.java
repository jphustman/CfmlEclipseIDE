package org.cfeclipse.tooling.parser.lexer;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

public abstract class BaseTextScanningRule implements IRule {

	protected IToken token;
	protected int charsRead = 0;

	protected void rewind(final ICharacterScanner scanner, int theCharsRead) {
		while (theCharsRead > 0) {
			theCharsRead--;
			unread(scanner);
		}
	}

	protected void unread(final ICharacterScanner scanner) {
		scanner.unread();
		charsRead--;
	}

	protected int read(final ICharacterScanner scanner) {
		final int c = scanner.read();
		charsRead++;
		return c;
	}

	protected void advanceToNextNonWhitespace(final ICharacterScanner scanner) {	
		while (Character.isWhitespace(read(scanner)));
		unread(scanner);
	}
	
	protected void advanceToNextNonLetter(final ICharacterScanner scanner) {
		while (Character.isLetter(read(scanner)));
		unread(scanner);		
	}

	protected void advanceToNextSpace(final ICharacterScanner scanner) {
		while (!Character.isWhitespace(read(scanner)));
		unread(scanner);		
	}	
	
}
