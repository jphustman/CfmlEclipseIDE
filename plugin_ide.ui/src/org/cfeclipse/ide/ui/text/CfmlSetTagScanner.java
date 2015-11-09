package org.cfeclipse.ide.ui.text;

import org.cfeclipse.tooling.parser.lexer.CfmlTagNameRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlSetTagScanner extends AbstractLangScanner {
	
	public CfmlSetTagScanner(TokenRegistry tokenStore) {
		super(tokenStore);
		//setDefaultReturnToken(getToken(coloringItem));
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		IToken stringToken = getToken(CfmlColorPreferences.CF_STRING);
		IToken numberToken = getToken(CfmlColorPreferences.CFNUMBER);
		IToken tagToken = getToken(CfmlColorPreferences.CF_TAG);
		IToken keywordToken = getToken(CfmlColorPreferences.CFOPERATOR);

		//rules.add(new MultiLineRule("cfset", " ", tagToken));
		rules.add(new MultiLineRule("var", " ", keywordToken));
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("'", "'", stringToken));
		rules.add(new NumberRule(numberToken));		
		/*
		//TODO: Get the dictionary from the project or overall settings, rather than just picking the default
		DictionaryPreferences dp = new DictionaryPreferences();
		dp.setDictionaryDir("D:/AMyers/Documents/projects/cfml.dictionary/src/main/resources/dictionary");
		DictionaryManager.initDictionaries(dp);
		CFSyntaxDictionary cfd = (CFSyntaxDictionary)DictionaryManager.getDictionary(DictionaryManager.CFDIC_KEY);
		
		Set<String> operatorSet = cfd.getOperators();
		String allkeys[] = new String[operatorSet.size()<<1];
		int i = 0;
		for (String op: operatorSet) {
			allkeys[i++] = op;
			allkeys[i++] = op.toUpperCase();

		}
		
		CFKeywordDetector keywordDetector = new CFKeywordDetector();
		PredicateWordRule keywordRule = new PredicateWordRule(keywordDetector, tagToken, allkeys, keywordToken);
		keywordRule.setCaseSensitive(false);		
		rules.add(keywordRule);
		*/
	}
	
}
