package melnorme.lang.ide.ui;

import java.util.List;

import melnorme.lang.ide.core.LangCore_Actual;
import melnorme.lang.ide.ui.editor.hover.ILangEditorTextHover;
import melnorme.lang.ide.ui.editor.text.LangAutoEditsPreferencesAccess;
import melnorme.lang.ide.ui.views.StructureElementLabelProvider;

import org.eclipse.jface.text.source.ISourceViewer;

import org.cfeclipse.ide.core.text.CfmlAutoEditStrategy;
import org.cfeclipse.ide.ui.CfmlImages;
import _org.eclipse.jdt.internal.ui.text.java.hover.AnnotationHover;
import _org.eclipse.jdt.internal.ui.text.java.hover.ProblemHover;

/**
 * Actual/concrete IDE constants and other bindings, for Lang UI code. 
 */
public final class LangUIPlugin_Actual {
	
	public static final String PLUGIN_ID = "org.cfeclipse.ide.ui";
	
	public static final String ROOT_PREF_PAGE_ID = PLUGIN_ID + ".PreferencePages.Root";
	
	public static final String RULER_CONTEXT = "#CfmlRulerContext";
	public static final String EDITOR_CONTEXT = "#CfmlEditorContext";
	
	// ID to start the debug plugin automatically, if present
	protected static final String DEBUG_PLUGIN_ID = "org.cfeclipse.ide.debug";
	
	protected static final Class<?> PLUGIN_IMAGES_CLASS = CfmlImages.class;
	
	protected static void initTextHovers( List<Class<? extends ILangEditorTextHover<?>>> textHoverSpecifications) {
		textHoverSpecifications.add(ProblemHover.class);
		textHoverSpecifications.add(AnnotationHover.class);
	}
	
	public static CfmlAutoEditStrategy createAutoEditStrategy(ISourceViewer sourceViewer, String contentType) {
		return new CfmlAutoEditStrategy(contentType, sourceViewer, new LangAutoEditsPreferencesAccess());
	}
	
	public static StructureElementLabelProvider getStructureElementLabelProvider() {
		return new StructureElementLabelProvider() {
		};
	}
	
	/* ----------------- UI messages:  ----------------- */
	
	public static final String TOOLS_CONSOLE_NAME = LangCore_Actual.CfmlNAME + " build";
	
	public static final String DAEMON_TOOL_Name = "lang_daemon";
	public static final String DAEMON_TOOL_ConsoleName = "lang_daemon log";
	
}