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

import org.cfeclipse.tooling.parser.lexer.CfmlAttributeNameRule;
import org.cfeclipse.tooling.parser.lexer.CfmlTagNameRule;
import org.eclipse.jface.text.rules.IPredicateRule;
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
		
		//the order here is important. It should go from specific to
		//general as the rules are applied in order
		rules.add(new MultiLineRule("<", ">", new Token(LangPartitionTypes.CF_START_TAG.getId())));
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