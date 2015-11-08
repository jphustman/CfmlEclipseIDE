/*
 * Created on Jan 30, 2004
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

public class HtmlTagScanner extends AbstractLangScanner {
	
	public HtmlTagScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {

		IToken stringToken = getToken(CfmlColorPreferences.CFSTRING);
		
		//I think its more important to show cfvariable then to color code the
		// attributes.
		//rules[0] = new SingleLineRule("#", "#", cfvar);
		rules.add(new MultiLineRule("\"", "\"", stringToken));
		rules.add(new MultiLineRule("'","'",stringToken));
		// Add generic whitespace rule.
		//rules[2] = new ShowWhitespaceRule(new CFWhitespaceDetector());
		
		//setRules(rules);
	}
}