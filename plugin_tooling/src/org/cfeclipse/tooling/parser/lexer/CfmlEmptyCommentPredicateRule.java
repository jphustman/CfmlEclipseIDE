package org.cfeclipse.tooling.parser.lexer;

/**
 * This class identifies empty cfscript comments.
 */
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;

public class CfmlEmptyCommentPredicateRule extends WordRule implements IPredicateRule {

	private IToken fSuccessToken;

	public CfmlEmptyCommentPredicateRule(IToken successToken) {
		super(new EmptyCommentDetector());
		fSuccessToken = successToken;
		addWord("/**/", fSuccessToken); //$NON-NLS-1$
	}

	@Override
	public IToken getSuccessToken() {
		return fSuccessToken;
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner, boolean resume) {
		return super.evaluate(scanner);
	}

	static class EmptyCommentDetector implements IWordDetector {

		/*
		 * (non-Javadoc) Method declared on IWordDetector
		 */
		@Override
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/*
		 * (non-Javadoc) Method declared on IWordDetector
		 */
		@Override
		public boolean isWordPart(char c) {
			return (c == '*' || c == '/');
		}
	}	
	
}