package org.cfeclipse.ide.ui.text;

import java.util.Set;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;

import cfml.dictionary.DictionaryManager;
import cfml.dictionary.preferences.DictionaryPreferences;
import cfml.dictionary.syntax.CFSyntaxDictionary;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlTagScanner extends AbstractLangScanner {
	
	public CfmlTagScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		IToken stringToken = getToken(CfmlColorPreferences.CFSTRING);
		IToken numberToken = getToken(CfmlColorPreferences.CFNUMBER);
		IToken tagToken = getToken(CfmlColorPreferences.CFTAG);
		IToken keywordToken = getToken(CfmlColorPreferences.CFOPERATOR);
		
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("'","'",stringToken));
		rules.add(new NumberRule(numberToken));		
		
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
	}		
	
}
