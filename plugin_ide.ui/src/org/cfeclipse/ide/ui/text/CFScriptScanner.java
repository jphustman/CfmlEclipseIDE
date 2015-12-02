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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.SingleLineRule;

import cfml.dictionary.DictionaryManager;
import cfml.dictionary.preferences.DictionaryPreferences;
import cfml.dictionary.syntax.CFSyntaxDictionary;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * @author Rob
 *
 * This is the scanner for cfscript partitions 
 */
public class CFScriptScanner extends AbstractLangScanner {

	
	public CFScriptScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}	

	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		
		IToken cfnumber = getToken(CfmlColorPreferences.CFML_NUMBER);		
		IToken cftag = getToken(CfmlColorPreferences.CFML_TAG);	
		IToken cfcommentBlock = getToken(CfmlColorPreferences.CFML_COMMENT);
		IToken cfcomment = getToken(CfmlColorPreferences.CFML_COMMENT);
		IToken string = getToken(CfmlColorPreferences.CFML_STRING);
		IToken cfkeyword = getToken(CfmlColorPreferences.CFML_KEYWORD);
		IToken cfscope = getToken(CfmlColorPreferences.CFML_SCOPE);		
		IToken cfopperators = getToken(CfmlColorPreferences.CFML_OPERATOR);
		IToken cfbuiltinscope = getToken(CfmlColorPreferences.CFML_BUILTINSCOPE);		
		IToken cffunction = getToken(CfmlColorPreferences.CFSCRIPT_FUNCTION);
		IToken javadoc = getToken(CfmlColorPreferences.CFML_JAVADOC);
		IToken cfdefault = getToken(CfmlColorPreferences.DEFAULT_TEXT);
				
/*
		List rules = new ArrayList();
*/		
		// rules.add(new HashExpressionRule(cfnumber));
		rules.add(new MultiLineRule("\"", "\"", string));
		rules.add(new MultiLineRule("'", "'", string));

		//so the script tags look correct
		rules.add(new SingleLineRule("<cfscript", ">", cftag));
		rules.add(new SingleLineRule("</cfscript", ">", cftag));
		rules.add(new SingleLineRule("<CFSCRIPT", ">", cftag));
		rules.add(new SingleLineRule("</CFSCRIPT", ">", cftag));
		
		
		rules.add(new MultiLineRule("/**", "*/", javadoc, (char) 0, true));
		rules.add(new MultiLineRule("/*", "*/", cfcommentBlock, (char) 0, true));
		rules.add(new EndOfLineRule("//", cfcomment));
		rules.add(new NumberRule(cfnumber));
		
		CFSyntaxDictionary dic = (CFSyntaxDictionary)DictionaryManager.getDictionary(DictionaryManager.CFDIC_KEY);		
		
		//do any keywords
		//get any needed operators (or, and et cetra)
		
		//Set set = dic.getOperators();
		
		//get any script specific keywords (if, case, while, et cetra)		
		//set.addAll(dic.getScriptKeywords());
		
		Set set = dic.getScriptKeywords();
		
		String allkeys[] = new String[set.size()];
		int i=0;
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String opname = (String)it.next(); 
			allkeys[i++] = opname.toUpperCase();
		}
		
		//build the word highlighter
		CFKeywordDetector cfkd = new CFKeywordDetector();
		PredicateWordRule words = new PredicateWordRule(
			cfkd, 
			cfdefault, 
			allkeys, 
			cfkeyword
		);
		words.setCaseSensitive(false);

		//now do the opperators
		set = dic.getOperators();
		it = set.iterator();
		while(it.hasNext())
		{
			String opp = (String)it.next().toString().toLowerCase();
			words.addWord(opp, cfopperators);
		}

		//now do the cffuntions so they look pretty too :)
		set = dic.getFunctions();
		it = set.iterator();
		while(it.hasNext())
		{
			String fun = (String)it.next().toString().toLowerCase();
			words.addWord(fun, cffunction);
		}
		rules.add(words);
		
		//now do the scopes so they look pretty too :)
		set = dic.getAllScopes();
		it = set.iterator();
		while(it.hasNext())
		{
			String scope = (String)it.next().toString().toLowerCase();
			words.addWord(scope, cfscope);
		}
		words.addWord("application", cfbuiltinscope);
		words.addWord("arguments", cfbuiltinscope);
		words.addWord("attributes", cfbuiltinscope);
		words.addWord("caller", cfbuiltinscope);
		words.addWord("client", cfbuiltinscope);
		words.addWord("cookie", cfbuiltinscope);
		words.addWord("flash", cfbuiltinscope);
		words.addWord("form", cfbuiltinscope);
		words.addWord("request", cfbuiltinscope);
		words.addWord("server", cfbuiltinscope);
		words.addWord("session", cfbuiltinscope);
		words.addWord("this",cfbuiltinscope);
		words.addWord("thistag", cfbuiltinscope);
		words.addWord("thread", cfbuiltinscope);
		words.addWord("url", cfbuiltinscope);
		words.addWord("variables",cfbuiltinscope);

		rules.add(words);

		IRule[] rulearry = new IRule[rules.size()];
		rules.toArray(rulearry);
		
		setRules(rulearry);
	}
}

