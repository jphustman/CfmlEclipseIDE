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
package org.cfeclipse.ide.ui.text;

import org.eclipse.swt.graphics.RGB;

import melnorme.lang.ide.ui.text.coloring.TextStyling;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;

public interface CfmlColorPreferences {
	
	String PREFIX = "editor.coloring."; 
	
	ThemedTextStylingPreference DEFAULT_TEXT = new ThemedTextStylingPreference(PREFIX + "default_text",
		new TextStyling(new RGB(  0,   0,   0), false, false),
		new TextStyling(new RGB(230, 230, 230), false, false));
	ThemedTextStylingPreference HTML_TAG = new ThemedTextStylingPreference(PREFIX + "html_tag",
			new TextStyling(new RGB(0, 0, 128), false, false),
			new TextStyling(new RGB(0, 0, 128), false, false));				
	ThemedTextStylingPreference CFML_STRING = new ThemedTextStylingPreference(PREFIX + "cfml_string",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));
	ThemedTextStylingPreference CFML_TAG = new ThemedTextStylingPreference(PREFIX + "cfml_tag",
		new TextStyling(new RGB(128,0,0), false, false),
		new TextStyling(new RGB(128,0,0), false, false));	
	ThemedTextStylingPreference CFML_KEYWORD = new ThemedTextStylingPreference(PREFIX + "cfml_keyword",
		new TextStyling(new RGB(60, 197, 255), true, false),
		new TextStyling(new RGB(60, 197, 255), true, false));
	ThemedTextStylingPreference CFML_OPERATOR = new ThemedTextStylingPreference(PREFIX + "cfml_operator",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));
	ThemedTextStylingPreference CFML_NUMBER = new ThemedTextStylingPreference(PREFIX + "cfml_number",
		new TextStyling(new RGB(255, 10, 10), false, false),
		new TextStyling(new RGB(255, 10, 10), false, false));
	ThemedTextStylingPreference CFML_SCOPE = new ThemedTextStylingPreference(PREFIX + "cfml_scope",
		new TextStyling(new RGB(204, 0, 0), false, false),
		new TextStyling(new RGB(204, 0, 0), false, false));	
	ThemedTextStylingPreference CFML_COMMENT = new ThemedTextStylingPreference(PREFIX + "cfml_comment",
		new TextStyling(new RGB(128, 128, 128), false, false),
		new TextStyling(new RGB(128, 128, 128), false, false));	
}