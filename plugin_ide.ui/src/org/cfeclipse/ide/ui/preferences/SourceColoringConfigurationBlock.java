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
package org.cfeclipse.ide.ui.preferences;

import static melnorme.utilbox.core.CoreUtil.array;

import java.io.InputStream;

import org.cfeclipse.ide.ui.text.CfmlColorPreferences;

import melnorme.lang.ide.ui.text.coloring.AbstractSourceColoringConfigurationBlock;
import melnorme.util.swt.jface.LabeledTreeElement;

public class SourceColoringConfigurationBlock extends AbstractSourceColoringConfigurationBlock {
	
	public SourceColoringConfigurationBlock() {
		super();
	}
	
	@Override
	protected LabeledTreeElement[] createTreeElements() {
		return array(
			new SourceColoringCategory("Source", array(
				new SourceColoringElement("CFML Scope", CfmlColorPreferences.CFML_SCOPE),					
				new SourceColoringElement("CFML Tags", CfmlColorPreferences.CFML_TAG),
				new SourceColoringElement("Default", CfmlColorPreferences.DEFAULT_TEXT),				
				new SourceColoringElement("HTML Tags", CfmlColorPreferences.HTML_TAG),
				new SourceColoringElement("Keywords", CfmlColorPreferences.CFML_KEYWORD),
				new SourceColoringElement("Numbers", CfmlColorPreferences.CFML_NUMBER),				
				new SourceColoringElement("Operators", CfmlColorPreferences.CFML_OPERATOR),				
				new SourceColoringElement("Strings", CfmlColorPreferences.CFML_STRING)				
			)),
			new SourceColoringCategory("Comments", array(
				new SourceColoringElement("Comment", CfmlColorPreferences.CFML_COMMENT)
			))
		);
	}
	
	private static final String PREVIEW_FILE_NAME = "SourceColoringPreviewFile.lang";
	
	@Override
	protected InputStream getPreviewContentAsStream() {
		return getClass().getResourceAsStream(PREVIEW_FILE_NAME);
	}
	
}