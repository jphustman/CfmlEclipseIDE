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
package org.cfeclipse.ide.core.text;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.Token;

import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.core.text.LangPartitionScanner;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlPartitionScanner extends LangPartitionScanner {

	public CfmlPartitionScanner() {
		super();
	}

	@Override
	protected void initPredicateRules(ArrayList2<IPredicateRule> rules) {
		addStandardRules(rules, 
				null, 
				null,
				null,
				null, 
				LangPartitionTypes.STRING.getId()
			);		

		rules.add(new PatternRule("//", null, new Token(LangPartitionTypes.CF_SCRIPT_COMMENT.getId()), NO_ESCAPE_CHAR, true, true));		
		rules.add(new PatternRule("<!---", "--->", new Token(LangPartitionTypes.CF_COMMENT.getId()), NO_ESCAPE_CHAR, false, true)); // TODO: See cfeclipse's NestableMultiLineRule
		rules.add(new PatternRule("<!--", "-->", new Token(LangPartitionTypes.HTML_COMMENT.getId()), NO_ESCAPE_CHAR, false, true));
		rules.add(new PatternRule("/**", "*/", new Token(LangPartitionTypes.JAVADOC_COMMENT.getId()), NO_ESCAPE_CHAR, false, true)); 
		rules.add(new PatternRule("/*", "*/", new Token(LangPartitionTypes.CF_SCRIPT_COMMENT_BLOCK.getId()), NO_ESCAPE_CHAR, false, true));		
	}

}