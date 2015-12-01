/*
 * Created on Jan 31, 2004
 *
 * The MIT License
 * Copyright (c) 2004 Rob Rohan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software 
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in 
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 */
package org.cfeclipse.ide.ui.text;

import java.util.Set;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

import cfml.dictionary.DictionaryManager;
import cfml.dictionary.syntax.JSSyntaxDictionary;
import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;


/**
 * @author Rob
 *
 * This is the Javascript rule scanner (color coder rules).
 */
public class JavaScriptScanner extends AbstractLangScanner {
	
	public JavaScriptScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}	
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken defaultToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		IToken cfnumberToken = getToken(CfmlColorPreferences.CFML_NUMBER);
		IToken tagToken = getToken(CfmlColorPreferences.HTML_TAG);
		IToken commentToken = getToken(CfmlColorPreferences.CFML_COMMENT);		
		IToken stringToken = getToken(CfmlColorPreferences.CFML_STRING);
		IToken keywordToken = getToken(CfmlColorPreferences.CFML_KEYWORD);
		IToken functionToken = getToken(CfmlColorPreferences.CFSCRIPT_FUNCTION); 
		
		//style the whole block with some default colors
		rules.add(new SingleLineRule("<script", ">", tagToken));
		rules.add(new SingleLineRule("</script", ">", tagToken));
		
		rules.add(new SingleLineRule("<SCRIPT", ">", tagToken));
		rules.add(new SingleLineRule("</SCRIPT", ">", tagToken));
		
		rules.add(new MultiLineRule("<cf",">", new Token(LangPartitionTypes.CF_START_TAG)));
		rules.add(new MultiLineRule("<CF",">", new Token(LangPartitionTypes.CF_START_TAG)));
		rules.add(new SingleLineRule("</cf",">", new Token(LangPartitionTypes.CF_END_TAG)));
		rules.add(new SingleLineRule("</CF",">", new Token(LangPartitionTypes.CF_END_TAG)));
		
		JSSyntaxDictionary jssd = (JSSyntaxDictionary)DictionaryManager.getDictionary(DictionaryManager.JSDIC_KEY);
		
		///////////////////////////////////////////////////////////////////////
		//do any keywords
		//get any script specific keywords (if, case, while, et cetra)
		Set<String> set = jssd.getScriptKeywords();
		String allkeys[] = new String[set.size()];
		int i=0;
		
		for (String s: set) {
			allkeys[i++] = s;
		}
		
		CFKeywordDetector cfkd = new CFKeywordDetector();
		PredicateWordRule words = new PredicateWordRule(
			cfkd, 
			defaultToken, 
			allkeys, 
			keywordToken
		);
		
		///////////////////////////////////////////////////////////////////////
		//do any known functions
		//get any script specific functions (alert, parseInt, confirm, et cetra)
		set = jssd.getFunctions();
		i=0;

		for (String func: set) {
			words.addWord(func,functionToken);
		}
				
		rules.add(words);
		
		///////////////////////////////////////////////////////////////////////
		//do any operators
		WordRule wr = new WordRule(new OperatorDetector());
		//get any script specific operators (*, +, =, et cetra)
		set = jssd.getOperators();
		for (String op: set) {
			wr.addWord(op,keywordToken);
		}
		rules.add(wr);
		
		///////////////////////////////////////////////////////////////////////
		rules.add(new MultiLineRule("/*", "*/", commentToken));
		rules.add(new EndOfLineRule("//", commentToken));
		
		rules.add(new SingleLineRule("\"", "\"", stringToken, '\\'));
		rules.add(new SingleLineRule("'", "'", stringToken, '\\'));
		
		rules.add(new NumberRule(cfnumberToken));
		
		IRule[] rulearry = new IRule[rules.size()];
		rules.toArray(rulearry);
		
		setRules(rulearry);
	}


}
