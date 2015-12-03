/*******************************************************************************
 * Copyright (c) 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package org.cfeclipse.ide.ui.text;

import org.cfeclipse.tooling.lexer.CfmlWordLexerRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * CFML code scanner
 */
public class CfscriptCodeScanner extends AbstractLangScanner {
	
	public CfscriptCodeScanner(TokenRegistry tokenStore) {
		super(tokenStore);
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		IToken defaultToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		setDefaultReturnToken(defaultToken);
		
		CfmlWordLexerRule<IToken> codeLexerRule = new CfmlWordLexerRule<>(
				Token.WHITESPACE, 
				defaultToken,
				getToken(CfmlColorPreferences.CFML_KEYWORD)
			);
		rules.add(new LexingRule_RuleAdapter(codeLexerRule));
		
		IToken commentToken = getToken(CfmlColorPreferences.CFML_COMMENT);
		IToken stringToken = getToken(CfmlColorPreferences.CFML_STRING);
		IToken numberToken = getToken(CfmlColorPreferences.CFML_NUMBER);		
		
		rules.add(new SingleLineRule("//", null, commentToken));
		rules.add(new MultiLineRule("/*", "*/", commentToken));
		rules.add(new MultiLineRule("\"", "\"", stringToken, '\\'));
		rules.add(new MultiLineRule("'", "'", stringToken, '\\'));
		rules.add(new NumberRule(numberToken));
	}
	
}