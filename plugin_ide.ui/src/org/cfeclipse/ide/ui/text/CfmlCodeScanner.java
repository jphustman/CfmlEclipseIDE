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

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * CFML code scanner
 */
public class CfmlCodeScanner extends AbstractLangScanner {
	
	public CfmlCodeScanner(TokenRegistry tokenStore) {
		super(tokenStore);
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		IToken defaultToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		setDefaultReturnToken(defaultToken);
		
//		CFScriptScanner cfss = new CFScriptScanner(null, null);
		//rules.add(cfss);
		
		/*
		CfmlWordLexerRule<IToken> codeLexerRule = new CfmlWordLexerRule<>(
			Token.WHITESPACE, 
			defaultToken,
			defaultToken,
			defaultToken,
			defaultToken,
			defaultToken,
			defaultToken
		);
		
		rules.add(new LexingRule_RuleAdapter(codeLexerRule));
		*/
	}
	
}