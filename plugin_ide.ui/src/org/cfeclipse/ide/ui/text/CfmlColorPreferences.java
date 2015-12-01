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
		new TextStyling(new RGB(0, 0, 0), false, false),
		new TextStyling(new RGB(207, 191, 173), false, false));
	ThemedTextStylingPreference HTML_TAG = new ThemedTextStylingPreference(PREFIX + "html_tag",
			new TextStyling(new RGB(0, 0, 128), false, false),
			new TextStyling(new RGB(207, 191, 173), false, false));				
	ThemedTextStylingPreference CFML_STRING = new ThemedTextStylingPreference(PREFIX + "cfml_string",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(236, 228, 126), false, false));
	ThemedTextStylingPreference CFML_TAG = new ThemedTextStylingPreference(PREFIX + "cfml_tag",
		new TextStyling(new RGB(128,0,0), false, false),
		new TextStyling(new RGB(207, 191, 173), false, false));	
	ThemedTextStylingPreference CFML_KEYWORD = new ThemedTextStylingPreference(PREFIX + "cfml_keyword",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(255, 0, 127), false, false));
	ThemedTextStylingPreference CFML_OPERATOR = new ThemedTextStylingPreference(PREFIX + "cfml_operator",
		new TextStyling(new RGB(0, 0, 0), false, false),
		new TextStyling(new RGB(230, 230, 250), false, false));
	ThemedTextStylingPreference CFML_NUMBER = new ThemedTextStylingPreference(PREFIX + "cfml_number",
		new TextStyling(new RGB(255, 10, 10), false, false),
		new TextStyling(new RGB(196, 140, 255), false, false));
	ThemedTextStylingPreference CFML_SCOPE = new ThemedTextStylingPreference(PREFIX + "cfml_scope",
		new TextStyling(new RGB(0, 102, 255), false, false),
		new TextStyling(new RGB(82, 227, 246), false, false));	
	ThemedTextStylingPreference CFML_COMMENT = new ThemedTextStylingPreference(PREFIX + "cfml_comment",
		new TextStyling(new RGB(128, 128, 128), false, false),
		new TextStyling(new RGB(255, 255, 255), false, false));
	ThemedTextStylingPreference CFML_BUILTINSCOPE = new ThemedTextStylingPreference(PREFIX + "cfml_builtinscope",
			new TextStyling(new RGB(0, 102, 255), false, false),
			new TextStyling(new RGB(82, 227, 246), false, false));
	ThemedTextStylingPreference CFSCRIPT_FUNCTION = new ThemedTextStylingPreference(PREFIX + "cfscript_function",
			new TextStyling(new RGB(0, 128, 0), false, false),
			new TextStyling(new RGB(255, 0, 127), false, false));
	ThemedTextStylingPreference CFML_JAVADOC = new ThemedTextStylingPreference(PREFIX + "cfml_javadoc",
			new TextStyling(new RGB(128, 128, 128), false, false),
			new TextStyling(new RGB(255, 255, 255), false, false));
	ThemedTextStylingPreference CFML_ATTRIBUTE = new ThemedTextStylingPreference(PREFIX + "cfml_attr",
			new TextStyling(new RGB(0, 0, 128), false, false),
			new TextStyling(new RGB(0, 0, 128), false, false));
	ThemedTextStylingPreference SQL_NUMBER = new ThemedTextStylingPreference(PREFIX + "sql_number",
			new TextStyling(new RGB(255, 10, 10), false, false),
			new TextStyling(new RGB(196, 140, 255), false, false));
	ThemedTextStylingPreference SQL_COMMENT = new ThemedTextStylingPreference(PREFIX + "sql_comment",
			new TextStyling(new RGB(128, 128, 128), false, false),
			new TextStyling(new RGB(255, 255, 255), false, false));
	ThemedTextStylingPreference SQL_STRING = new ThemedTextStylingPreference(PREFIX + "sql_string",
			new TextStyling(new RGB(0, 0, 255), false, false),
			new TextStyling(new RGB(236, 228, 126), false, false));
	ThemedTextStylingPreference SQL_KEYWORD = new ThemedTextStylingPreference(PREFIX + "sql_keyword",
			new TextStyling(new RGB(0, 0, 255), false, false),
			new TextStyling(new RGB(255, 0, 127), false, false));
}