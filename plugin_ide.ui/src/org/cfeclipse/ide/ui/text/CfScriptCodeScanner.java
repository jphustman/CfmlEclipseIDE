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
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * Sample CFML code scanner
 */
public class CfScriptCodeScanner extends AbstractLangScanner {
	
	public CfScriptCodeScanner(TokenRegistry tokenStore) {
		super(tokenStore);
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken tkOther = getToken(CfmlColorPreferences.DEFAULT);
		IToken tkKeywords = getToken(CfmlColorPreferences.KEYWORDS);
		IToken tkKeywordValues = getToken(CfmlColorPreferences.KEYWORDS_VALUES);
		
		// Add generic whitespace rule.
		rules.add(new WhitespaceRule(new LangWhitespaceDetector()));
		
		WordRule wordRule = new WordRule(new JavaWordDetector(), tkOther);
		
		wordRule.addWord("function",  tkKeywords);
		wordRule.addWord("null", tkKeywordValues);
		wordRule.addWord("true", tkKeywordValues);
		wordRule.addWord("false", tkKeywordValues);
		
		
		rules.add(wordRule);
		
		setDefaultReturnToken(tkOther);
	}
	
}