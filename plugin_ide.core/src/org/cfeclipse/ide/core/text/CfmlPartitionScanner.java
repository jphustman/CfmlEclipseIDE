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

import java.util.Set;

import org.cfeclipse.tooling.parser.lexer.CfmlEmptyCommentPredicateRule;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import cfml.dictionary.DictionaryManager;
import cfml.dictionary.SyntaxDictionary;
import cfml.dictionary.Tag;
import cfml.dictionary.preferences.DictionaryPreferences;
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
		IToken cfscriptRegion = new Token(LangPartitionTypes.CF_SCRIPT_REGION);
		
		//the order here is important. It should go from specific to
		//general as the rules are applied in order
		
		// NestableMultiLineRule cfScriptRule = new NestableMultiLineRule("component", "}", cfScript);
		// cfScriptRule.setColumnConstraint(0);
		// rules.add(cfScriptRule);
		//rules.add(new CFScriptComponentRule("component", "{", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_TAG_ATTRIBS.getId()));
		//rules.add(new CFScriptComponentEndRule("}", LangPartitionTypes.CF_END_TAG.getId(), LangPartitionTypes.CF_SCRIPT.getId()));
		// rules.add(new CFScriptComponentRule("}", "}", CF_SCRIPT, CF_SCRIPT));
		
		rules.add(new MultiLineRule("<cfscript>", "</cfscript>", cfscriptRegion));
		rules.add(new MultiLineRule("<CFSCRIPT>", "</CFSCRIPT>", cfscriptRegion));
		
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
		
		//SyntaxDictionary sd = DictionaryManager.getDictionary(DictionaryManager.CFDIC);
		DictionaryPreferences dp = new DictionaryPreferences();
		dp.setDictionaryDir("D:/AMyers/Documents/projects/cfml.dictionary/src/main/resources/dictionary");
		DictionaryManager.initDictionaries(dp);
		//CFSyntaxDictionary cfd = (CFSyntaxDictionary)DictionaryManager.getDictionary(DictionaryManager.CFDIC_KEY);
		
		SyntaxDictionary sd = DictionaryManager.getDictionary(DictionaryManager.CFDIC_KEY);

		Tag tg = null;
		try {
			Set<String> elements = sd.getAllElements();
			for (String ename : elements) {
				tg = sd.getTag(ename);
				rules.add(new NamedTagRule("<" + ename, ">", LangPartitionTypes.CF_START_TAG.getId(),
						LangPartitionTypes.CF_TAG_ATTRIBS.getId()));
				rules.add(new NamedTagRule("</" + ename, ">", LangPartitionTypes.CF_END_TAG.getId(),
						LangPartitionTypes.CF_END_TAG.getId()));
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		//these are not really handled in the dictionary because you can call 
		//normal pages as cf_'s
		rules.add(new CustomTagRule("<cf_",">", LangPartitionTypes.CF_START_TAG.getId(), LangPartitionTypes.CF_TAG_ATTRIBS.getId()));
		rules.add(new CustomTagRule("</cf_",">", LangPartitionTypes.CF_END_TAG.getId(), LangPartitionTypes.CF_END_TAG.getId()));
		
		//do the html tags now
		sd = DictionaryManager.getDictionary(DictionaryManager.HTDIC_KEY);
		
		try {
			Set<String> elements = sd.getAllElements();

			// this is going to be used to tell if we need to add a form, table,
			// or normal tag for the html tags
			String startTokenType = LangPartitionTypes.HTM_START_TAG.getId();
			String endTokenType = LangPartitionTypes.HTM_END_TAG.getId();
			String midTokenType = LangPartitionTypes.HTM_TAG_ATTRIBS.getId();

			// loop over all the tags in the html dictionary and try to set the
			// partition to the correct type
			for (String ename : elements) {
				tg = sd.getTag(ename);

				// colour form and table tags differently...
				if (tg.isTableTag()) {
					startTokenType = LangPartitionTypes.TABLE_START_TAG.getId();
					midTokenType = LangPartitionTypes.TABLE_TAG_ATTRIBS.getId();
					endTokenType = LangPartitionTypes.TABLE_END_TAG.getId();
				} else if (tg.isFormTag()) {
					startTokenType = LangPartitionTypes.FORM_START_TAG.getId();
					midTokenType = LangPartitionTypes.FORM_TAG_ATTRIBS.getId();
					endTokenType = LangPartitionTypes.FORM_END_TAG.getId();
				} else {
					startTokenType = LangPartitionTypes.HTM_START_TAG.getId();
					midTokenType = LangPartitionTypes.HTM_TAG_ATTRIBS.getId();
					endTokenType = LangPartitionTypes.HTM_END_TAG.getId();
				}

				rules.add(new NamedTagRule("<" + ename, ">", startTokenType, midTokenType));
				rules.add(new NamedTagRule("</" + ename, ">", endTokenType, endTokenType));

			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		// Taglibs that may have been imported
		// <foo:bar> type tags
		rules.add(new TaglibRule(taglibtag));
		
		//catch any other tags we dont know about (xml etc) and make them
		//a different color
		rules.add(new TagRule(unktag));
		
		//for debuggin
		//rules.add(new ShowWhitespaceRule(new CFWhitespaceDetector()));
		
/*		IPredicateRule[] rulearry = new IPredicateRule[rules.size()];
		rules.toArray(rulearry);
		
		setPredicateRules(rulearry);		
*/	}
	
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