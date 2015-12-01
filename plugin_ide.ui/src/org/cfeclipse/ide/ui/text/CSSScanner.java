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

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.SingleLineRule;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

/**
 * @author Rob
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CSSScanner extends AbstractLangScanner {
	
	
	public CSSScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}		
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
				
		IToken tagToken = getToken(CfmlColorPreferences.HTML_TAG);		
		IToken commentToken = getToken(CfmlColorPreferences.CFML_COMMENT);		
		IToken stringToken = getToken(CfmlColorPreferences.CFML_STRING);			
		IToken keywordToken = getToken(CfmlColorPreferences.CFML_KEYWORD);
		
		//style the whole block with some default colors
		rules.add(new SingleLineRule("<style", ">", tagToken));
		rules.add(new SingleLineRule("</style", ">", tagToken));
		
		rules.add(new SingleLineRule("<STYLE", ">", tagToken));
		rules.add(new SingleLineRule("</STYLE", ">", tagToken));
		
		rules.add(new MultiLineRule("/*", "*/", commentToken));
		
		rules.add(new SingleLineRule("\"", "\"", stringToken));
		rules.add(new SingleLineRule("'", "'", stringToken));
		
		//the value of a css - this will change when the StyleDictionary is done
		rules.add(new SingleLineRule(":", ";", keywordToken));
		
		//TODO this should load the name / values from a list somewhere
		//like border and border-color - but I don't feel like typeing all that
		//shite at the moment. (need StyleDictionary.class)
		
		//do any keywords
		/* WordRule wr = new WordRule(new CFKeywordDetector());
		//get any needed operators (or, and et cetra)
		Set set = SyntaxDictionary.getOperators();
		//get any script specific keywords (if, case, while, et cetra)
		set.addAll(SyntaxDictionary.getScriptKeywords());
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String op = (String)it.next();
			wr.addWord(op,cfkeyword);
			wr.addWord(op.toUpperCase(),cfkeyword);
		}
		
		rules.add(wr); */
		
		
		IRule[] rulearry = new IRule[rules.size()];
		rules.toArray(rulearry);
		
		setRules(rulearry);
	}
}
