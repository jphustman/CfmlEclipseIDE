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

import org.cfeclipse.tooling.parser.lexer.CfmlEmptyCommentPredicateRule;
import org.eclipse.jface.text.rules.EndOfLineRule;
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
		
		IToken doctype	 	= new Token(LangPartitionTypes.DOCTYPE.getId());
		IToken cfComment 	= new Token(LangPartitionTypes.CF_COMMENT.getId());
		IToken cfscriptCommentBlock = new Token(LangPartitionTypes.CF_SCRIPT_COMMENT_BLOCK.getId());
		IToken cfscriptComment = new Token(LangPartitionTypes.CF_SCRIPT_COMMENT.getId());
		IToken javaDocComment = new Token(LangPartitionTypes.JAVADOC_COMMENT.getId());
		IToken htmComment 	= new Token(LangPartitionTypes.HTM_COMMENT.getId());
		IToken taglibtag		= new Token(LangPartitionTypes.TAGLIB_TAG.getId());
		IToken unktag		= new Token(LangPartitionTypes.UNK_TAG.getId());		
		
		//the order here is important. It should go from specific to
		//general as the rules are applied in order
		
		// NestableMultiLineRule cfScriptRule = new NestableMultiLineRule("component", "}", cfScript);
		// cfScriptRule.setColumnConstraint(0);
		// rules.add(cfScriptRule);
		rules.add(new CFScriptComponentRule("component", "{", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_TAG_ATTRIBS.getId()));
		rules.add(new CFScriptComponentEndRule("}", LangPartitionTypes.CF_END_TAG.getId(), LangPartitionTypes.CF_SCRIPT.getId()));
		// rules.add(new CFScriptComponentRule("}", "}", CF_SCRIPT, CF_SCRIPT));
		rules.add(new CfmlEmptyCommentPredicateRule(cfscriptCommentBlock));
		rules.add(new MultiLineRule("/**", "*/", javaDocComment, (char) 0, true));
		rules.add(new MultiLineRule("/*", "*/", cfscriptCommentBlock, (char) 0, true));
		rules.add(new EndOfLineRule("//", cfscriptComment));
		// Partitions in the document will get marked in this order
		rules.add(new NestableMultiLineRule("<!---", "--->", cfComment, (char) 0, true));
		// rules.add(new TagRule(htmComment));
		rules.add(new MultiLineRule("<!--", "-->", htmComment));
		//doctype rule
		rules.add(new MultiLineRule("<!doctype", ">", doctype));
		
		// Handle the if/elseif/set/return tag partitioning
		rules.add(new NamedTagRule("<cfset",">", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_SET_STATEMENT.getId()));
		rules.add(new NamedTagRule("<cfif",">", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_BOOLEAN_STATEMENT.getId()));
		rules.add(new NamedTagRule("<cfelseif",">", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_BOOLEAN_STATEMENT.getId()));
		rules.add(new NamedTagRule("<cfreturn",">", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_RETURN_STATEMENT.getId()));		
	}
	
	/**
	 * Return the String ranging from the start of the current partition to the current scanning position. Some rules
	 * (@see NestedMultiLineRule) need this information to calculate the comment nesting depth.
	 * 
	 */
	public String getScannedPartitionString() {
		try {
			String scanned = fDocument.get(fPartitionOffset, fOffset - fPartitionOffset);
			return scanned;
		} catch (Exception e) {
			// Do nothing
		}
		return "";
	}	

}