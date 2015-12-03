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
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
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
			LangPartitionTypes.CF_SCRIPT_COMMENT.getId(), 
			LangPartitionTypes.CF_SCRIPT_COMMENT_BLOCK.getId(), 
			null,
			LangPartitionTypes.JAVADOC_COMMENT.getId(), 
			null //LangPartitionTypes.CFSTRING.getId()
		);
		
		IToken htmComment = new Token(LangPartitionTypes.HTM_COMMENT.getId());
		IToken cfmlComment = new Token(LangPartitionTypes.CFML_COMMENT.getId());
		IToken cfmlStartTag = new Token(LangPartitionTypes.CF_START_TAG.getId());
		IToken cfmlCloseTag = new Token(LangPartitionTypes.CF_END_TAG.getId());
		IToken htmlStartTag = new Token(LangPartitionTypes.HTM_START_TAG.getId());
		IToken stringToken = new Token(LangPartitionTypes.CFSTRING.getId());
		IToken cfscriptToken = new Token(LangPartitionTypes.CF_SCRIPT_REGION.getId());
		
		rules.add(new MultiLineRule("\"", "\"", stringToken, '\\'));
		rules.add(new MultiLineRule("'", "'", stringToken, '\\'));
		rules.add(new MultiLineRule("<!---", "--->", cfmlComment));
		rules.add(new MultiLineRule("<!--", "-->", htmComment));	
		rules.add(new MultiLineRule("<cfscript>", "</cfscript>", cfscriptToken));
		rules.add(new MultiLineRule("<CFSCRIPT>", "</CFSCRIPT>", cfscriptToken));
		rules.add(new MultiLineRule("component ", "tnenopmoc", cfscriptToken, NO_ESCAPE_CHAR, true));
		rules.add(new NestableMultiLineRule("<cf", ">", cfmlStartTag));
		rules.add(new NestableMultiLineRule("</cf", ">", cfmlCloseTag));
		rules.add(new NestableMultiLineRule("<:", ">", cfmlStartTag)); // Lucee tags
		rules.add(new NestableMultiLineRule("</:", ">", cfmlCloseTag)); // Lucee tags
		rules.add(new NestableMultiLineRule("<", ">", htmlStartTag));

		
		//rules.add(new PredicateRule_Adapter(LangPartitionTypes.CHARACTER.getId(), new CharacterLexingRule()));
	}
	
}