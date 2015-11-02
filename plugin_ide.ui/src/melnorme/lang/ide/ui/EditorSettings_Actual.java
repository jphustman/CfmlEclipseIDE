/*******************************************************************************
 * Copyright (c) 2014, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.ui;

import static melnorme.utilbox.core.CoreUtil.array;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.ui.services.IServiceLocator;

import org.cfeclipse.ide.ui.editor.CfmlEditor;
import org.cfeclipse.ide.ui.text.CfmlColorPreferences;
import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.ui.editor.LangEditorContextMenuContributor;
import melnorme.lang.ide.ui.editor.text.EditorPrefConstants_Common;
import melnorme.lang.ide.ui.text.SimpleSourceViewerConfiguration;
import melnorme.lang.ide.ui.text.coloring.StylingPreferences;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.util.swt.jface.text.ColorManager2;

public class EditorSettings_Actual {
	
	public static final String EDITOR_ID = LangUIPlugin.PLUGIN_ID + ".Editors.SourceEditor";
	public static final String EDITOR_CONTEXT_ID = LangUIPlugin.PLUGIN_ID + ".Contexts.Editor";
	
	public static final String EDITOR_CODE_TARGET = LangUIPlugin.PLUGIN_ID + ".Editor.HyperlinkCodeTarget";
	
	public static Class<CfmlEditor> editorKlass() {
		return CfmlEditor.class;
	}
	
	public static interface EditorPrefConstants extends EditorPrefConstants_Common {
		
	}
	
	public static StylingPreferences getStylingPreferences() {
		return new StylingPreferences(
			CfmlColorPreferences.COMMENTS,
			CfmlColorPreferences.DOC_COMMENTS,		
			CfmlColorPreferences.DEFAULT,
			CfmlColorPreferences.KEYWORDS,
			CfmlColorPreferences.KEYWORDS_VALUES,
			CfmlColorPreferences.BUILTIN_FUNCTION,
			CfmlColorPreferences.CFML_TAG
		);
	}
	
	public static final String TEMPLATE_CONTEXT_TYPE_ID = LangUIPlugin.PLUGIN_ID + ".TemplateContextType";
	
	public static final ThemedTextStylingPreference CODE_DEFAULT_COLOR = CfmlColorPreferences.DEFAULT;
	
	public static SourceViewerConfiguration createTemplateEditorSourceViewerConfiguration(
			IPreferenceStore store, final IContentAssistProcessor templateCAP) {
		ColorManager2 colorManager = LangUIPlugin.getInstance().getColorManager();
		return new SimpleSourceViewerConfiguration(store, colorManager) {
			@Override
			public ContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
				return setupSimpleContentAssistant(templateCAP, array(
					LangPartitionTypes.CODE.getId(), 
					LangPartitionTypes.CF_SCRIPT_COMMENT_BLOCK.getId(), 
					LangPartitionTypes.CF_SCRIPT_COMMENT.getId(), 
					LangPartitionTypes.CF_COMMENT.getId(),
					LangPartitionTypes.HTML_COMMENT.getId() 
				));
			}
		};
	}
	
	/* ----------------- actions ----------------- */
	
	public static interface EditorCommandIds {
		
		public static final String OpenDef_ID = LangUIPlugin.PLUGIN_ID + ".commands.openDefinition";
		
		public static final String GoToMatchingBracket = LangUIPlugin.PLUGIN_ID + ".commands.GoToMatchingBracket";
		public static final String ToggleComment = LangUIPlugin.PLUGIN_ID + ".commands.ToggleComment";
		
		public static final String QuickOutline = LangUIPlugin.PLUGIN_ID + ".commands.QuickOutline";
		
	}
	
	public static LangEditorContextMenuContributor createCommandsContribHelper(IServiceLocator svcLocator) {
		return new LangEditorContextMenuContributor(svcLocator) { };
	}
	
}
