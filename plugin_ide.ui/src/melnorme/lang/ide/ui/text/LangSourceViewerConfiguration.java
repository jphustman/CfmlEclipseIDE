package melnorme.lang.ide.ui.text;

import org.cfeclipse.ide.ui.editor.CfmlCompletionProposalComputer;
import org.cfeclipse.ide.ui.text.CfmlCodeScanner;
import org.cfeclipse.ide.ui.text.CfmlColorPreferences;
import org.cfeclipse.ide.ui.text.CfmlTagScanner;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.swt.widgets.Display;

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
		case JAVADOC_COMMENT:
		case CF_SCRIPT_COMMENT_BLOCK:
		case CF_SCRIPT_COMMENT:
		case CF_COMMENT:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.COMMENTS);
		case CF_TAG_DATA:
			//return new SingleTokenScanner(tokenStore, CfmlColorPreferences.CFML_TAG);
			return new CfmlTagScanner(tokenStore, CfmlColorPreferences.CFML_TAG);
		default:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.DEFAULT);
		}
		//throw assertUnreachable();
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