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
	ThemedTextStylingPreference CFSTRING = new ThemedTextStylingPreference(PREFIX + "cfstring",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));
	ThemedTextStylingPreference CFTAG = new ThemedTextStylingPreference(PREFIX + "cftag",
		new TextStyling(new RGB(128,0,0), false, false),
		new TextStyling(new RGB(128,0,0), false, false));	
	ThemedTextStylingPreference TAGLIB_TAG = new ThemedTextStylingPreference(PREFIX + "taglib_tag",
		new TextStyling(new RGB(60, 60, 170), false, false),
		new TextStyling(new RGB(60, 60, 170), false, false));	
	ThemedTextStylingPreference CFKEYWORD = new ThemedTextStylingPreference(PREFIX + "cfkeyword",
		new TextStyling(new RGB(60, 197, 255), true, false),
		new TextStyling(new RGB(60, 197, 255), true, false));
	ThemedTextStylingPreference CFOPERATOR = new ThemedTextStylingPreference(PREFIX + "cfoperator",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));
	ThemedTextStylingPreference CFNUMBER = new ThemedTextStylingPreference(PREFIX + "cfnumber",
		new TextStyling(new RGB(255, 10, 10), false, false),
		new TextStyling(new RGB(255, 10, 10), false, false));
	ThemedTextStylingPreference CFBUILTINSCOPE = new ThemedTextStylingPreference(PREFIX + "cfbuiltinscope",
		new TextStyling(new RGB(204, 0, 0), false, false),
		new TextStyling(new RGB(204, 0, 0), false, false));
	ThemedTextStylingPreference CFSCOPE = new ThemedTextStylingPreference(PREFIX + "cfscope",
		new TextStyling(new RGB(204, 0, 0), false, false),
		new TextStyling(new RGB(204, 0, 0), false, false));	
	ThemedTextStylingPreference CFCOMMENT = new ThemedTextStylingPreference(PREFIX + "cfcomment",
		new TextStyling(new RGB(128, 128, 128), false, false),
		new TextStyling(new RGB(128, 128, 128), false, false));
	ThemedTextStylingPreference BACKGROUND_CFCOMMENT = new ThemedTextStylingPreference(PREFIX + "background_cfcomment",
		new TextStyling(new RGB(255, 255, 255), false, false),
		new TextStyling(new RGB(255, 255, 255), false, false));
	ThemedTextStylingPreference JAVADOC = new ThemedTextStylingPreference(PREFIX + "javadoc",
		new TextStyling(new RGB(42, 42, 42), false, false),
		new TextStyling(new RGB(42, 42, 42), false, false));
	ThemedTextStylingPreference BACKGROUND_JAVADOC = new ThemedTextStylingPreference(PREFIX + "background_javadoc",
		new TextStyling(new RGB(255, 255, 255), false, false),
		new TextStyling(new RGB(255, 255, 255), false, false));
	ThemedTextStylingPreference CFSCRIPT_TEXT = new ThemedTextStylingPreference(PREFIX + "cfscript_text",
		new TextStyling(new RGB(0, 0, 0), false, false),
		new TextStyling(new RGB(0, 0, 0), false, false));
	ThemedTextStylingPreference CFSCRIPT_KEYWORD = new ThemedTextStylingPreference(PREFIX + "cfscript_keyword",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));	
	ThemedTextStylingPreference CFSCRIPT_FUNCTION = new ThemedTextStylingPreference(PREFIX + "cfscript_function",
		new TextStyling(new RGB(0, 112, 0), false, false),
		new TextStyling(new RGB(0, 112, 0), false, false));
	ThemedTextStylingPreference SQL_TEXT = new ThemedTextStylingPreference(PREFIX + "sql_text",
		new TextStyling(new RGB(0, 0, 0), false, false),
		new TextStyling(new RGB(0, 0, 0), false, false));		
	ThemedTextStylingPreference SQL_KEYWORD = new ThemedTextStylingPreference(PREFIX + "sql_keyword",
		new TextStyling(new RGB(0, 0, 255), false, false),
		new TextStyling(new RGB(0, 0, 255), false, false));		
	ThemedTextStylingPreference SQL_STRING = new ThemedTextStylingPreference(PREFIX + "sql_string",
		new TextStyling(new RGB(255, 0, 0), false, false),
		new TextStyling(new RGB(255, 0, 0), false, false));		
	ThemedTextStylingPreference SQL_OPERATOR = new ThemedTextStylingPreference(PREFIX + "sql_operator",
		new TextStyling(new RGB(128, 128, 128), false, false),
		new TextStyling(new RGB(128, 128, 128), false, false));		
	ThemedTextStylingPreference SQL_COMMENT = new ThemedTextStylingPreference(PREFIX + "sql_comment",
		new TextStyling(new RGB(0, 128, 128), false, false),
		new TextStyling(new RGB(0, 128, 128), false, false));			
}