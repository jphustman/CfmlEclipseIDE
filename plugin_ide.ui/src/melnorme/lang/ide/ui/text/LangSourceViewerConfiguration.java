package melnorme.lang.ide.ui.text;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertUnreachable;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.swt.widgets.Display;

import org.cfeclipse.ide.ui.editor.CfmlCompletionProposalComputer;
import org.cfeclipse.ide.ui.text.CfmlCodeScanner;
import org.cfeclipse.ide.ui.text.CfmlColorPreferences;
import melnorme.lang.ide.core.TextSettings_Actual.LangPartitionTypes;
import melnorme.lang.ide.ui.editor.structure.AbstractLangStructureEditor;
import melnorme.lang.ide.ui.text.coloring.SingleTokenScanner;
import melnorme.lang.ide.ui.text.coloring.StylingPreferences;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.lang.ide.ui.text.completion.ILangCompletionProposalComputer;
import melnorme.lang.ide.ui.text.completion.LangContentAssistProcessor.ContentAssistCategoriesBuilder;
import melnorme.lang.tooling.LANG_SPECIFIC;
import melnorme.util.swt.jface.text.ColorManager2;

@LANG_SPECIFIC
public class LangSourceViewerConfiguration extends AbstractLangSourceViewerConfiguration {
	
	public LangSourceViewerConfiguration(IPreferenceStore preferenceStore, ColorManager2 colorManager,
			AbstractLangStructureEditor editor, StylingPreferences stylingPrefs) {
		super(preferenceStore, colorManager, stylingPrefs, editor);
	}
	
	@Override
	protected AbstractLangScanner createScannerFor(Display current, LangPartitionTypes partitionType, 
			TokenRegistry tokenStore) {
		switch (partitionType) {
		case CODE: 
			return new CfmlCodeScanner(tokenStore);
		
		case LINE_COMMENT: 
		case BLOCK_COMMENT: 
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.COMMENTS);
		
		case DOC_LINE_COMMENT:
		case DOC_BLOCK_COMMENT:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.DOC_COMMENTS);
		
		case STRING:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.STRINGS);
		
		case CHARACTER:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.CHARACTER);
		}
		throw assertUnreachable();
	}
	
	@Override
	protected String getToggleCommentPrefix() {
		return "//";
	}
	
	@Override
	protected IInformationProvider getInformationProvider(String contentType) {
		return null;
	}
	
	@Override
	protected ContentAssistCategoriesBuilder getContentAssistCategoriesProvider() {
		return new ContentAssistCategoriesBuilder() {
			@Override
			protected ILangCompletionProposalComputer createDefaultSymbolsProposalComputer() {
				return new CfmlCompletionProposalComputer();
			}
		};
	}
	
}