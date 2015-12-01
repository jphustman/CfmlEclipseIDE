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

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.SingleLineRule;

import cfml.dictionary.DictionaryManager;
import cfml.dictionary.syntax.SQLSyntaxDictionary;
import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * @author Rob
 *
 *         This is the scanner for cfquery partitions
 */
public class SQLScanner extends AbstractLangScanner {

	public SQLScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {

		IToken numberToken = getToken(CfmlColorPreferences.SQL_NUMBER);
		IToken commentToken = getToken(CfmlColorPreferences.SQL_COMMENT);
		IToken stringToken = getToken(CfmlColorPreferences.SQL_STRING);
		IToken defaultTextToken = getToken(CfmlColorPreferences.DEFAULT_TEXT);
		IToken keywordToken = getToken(CfmlColorPreferences.SQL_KEYWORD);
		IToken functionToken = getToken(CfmlColorPreferences.CFSCRIPT_FUNCTION);

		rules.add(new SingleLineRule("\"", "\"", stringToken));
		rules.add(new SingleLineRule("'", "'", stringToken));

		rules.add(new NumberRule(numberToken));

		// I think the reason this doesnt work as well as the <!-- type of
		// comment
		// is that the <! type is defined on the partition scanner where this is
		// only here... javascript has the same problem
		rules.add(new MultiLineRule("/*", "*/", commentToken));

		SQLSyntaxDictionary dic = (SQLSyntaxDictionary) DictionaryManager.getDictionary(DictionaryManager.SQLDIC_KEY);

		// get any SQL specific keywords (select, from, where, et cetra)

		Set<String> sqlKeywordSet = dic.getSQLKeywords();

		String allkeys[] = new String[sqlKeywordSet.size()];
		int i = 0;
		for (String keywd : sqlKeywordSet) {
			allkeys[i++] = keywd;
		}

		// build the word highlighter
		SQLKeywordDetector cfqkd = new SQLKeywordDetector();
		PredicateWordRule words = new PredicateWordRule(cfqkd, defaultTextToken, allkeys, keywordToken);
		words.setCaseSensitive(false);

		// now do the cffunctions so they look pretty too :)
		Set <String> cfFunctionSet = dic.getFunctions();
		for (String fun : cfFunctionSet) {
			if (!sqlKeywordSet.contains(fun)) {
				words.addWord(fun, functionToken);
			}
		}

		rules.add(words);

		IRule[] rulearry = new IRule[rules.size()];
		rules.toArray(rulearry);

		setRules(rulearry);
	}
}
