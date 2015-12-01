package melnorme.lang.ide.ui.text;

import static melnorme.utilbox.core.Assert.AssertNamespace.assertTrue;

import org.cfeclipse.ide.core.text.CfmlPartitionScanner;
import org.cfeclipse.ide.ui.editor.CfmlCompletionProposalComputer;
<<<<<<< 8f8fdd4d0d1e505939808feab061eda6dc81e391
=======
import org.cfeclipse.ide.ui.text.CFScriptScanner;
import org.cfeclipse.ide.ui.text.CfmlCodeScanner;
>>>>>>> Starting to recognise CFSCRIPT blocks now
import org.cfeclipse.ide.ui.text.CfmlColorPreferences;
import org.cfeclipse.ide.ui.text.CfmlEndTagScanner;
import org.cfeclipse.ide.ui.text.CfmlSetTagScanner;
import org.cfeclipse.ide.ui.text.CfmlStartTagScanner;
import org.cfeclipse.ide.ui.text.HtmlStartTagScanner;
import org.cfeclipse.ide.ui.text.CfmlStartTagEndScanner;
import org.cfeclipse.ide.ui.text.HtmlTagScanner;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.information.IInformationProvider;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
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
import melnorme.utilbox.collections.ArrayList2;

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
		case CFSTRING:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.CFML_STRING);
		case JAVADOC_COMMENT:
		case CF_SCRIPT_COMMENT_BLOCK:
		case CF_SCRIPT_COMMENT:
		case CF_COMMENT:
		case CFML_JAVADOC:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.CFML_COMMENT);			
		case HTM_START_TAG_BEGIN:
//		case HTM_START_TAG_END:
		case HTM_END_TAG:
			return new HtmlTagScanner(tokenStore, CfmlColorPreferences.HTML_TAG);
		case CF_START_TAG_BEGIN:
//		case CF_START_TAG_END:
		case CF_END_TAG:
			return new CfmlTagScanner(tokenStore, CfmlColorPreferences.CFML_TAG);	
		case CF_SCRIPT_REGION:
			return new CFScriptScanner(tokenStore, CfmlColorPreferences.DEFAULT_TEXT);
		default:
			return new SingleTokenScanner(tokenStore, CfmlColorPreferences.DEFAULT_TEXT);
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

	/*
	@Override
	protected void setupPresentationReconciler(PresentationReconciler reconciler, ISourceViewer sourceViewer) {
		// Must be called from UI thread
		assertTrue(Display.getCurrent() != null);
		
		// Create a token registry for given sourceViewer
		TokenRegistry tokenRegistry = new TokenRegistry(colorManager, stylingPrefs) {
			@Override
			protected void handleTokenModified(Token token) {
				sourceViewer.invalidateTextPresentation();
			}
		};
		addConfigurationScopedOwned(sourceViewer, tokenRegistry);
		
		ArrayList2<AbstractLangScanner> scanners = new ArrayList2<>();
		
		for(LangPartitionTypes partitionType : getPartitionTypes()) {
			
			String contentType = partitionType.getId();
			AbstractLangScanner scanner = createScannerFor(Display.getCurrent(), partitionType, tokenRegistry);
			scanners.add(scanner);
			
			DefaultDamagerRepairer dr = getDamagerRepairer(scanner, contentType);
			reconciler.setDamager(dr, contentType);
			reconciler.setRepairer(dr, contentType);
		}

	}
	*/

	/*
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = createPresentationReconciler();
		//reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		//setupPresentationReconciler(reconciler, sourceViewer);
				
		// TODO Auto-generated method stub
		//return super.getPresentationReconciler(sourceViewer);
		
		// CF script		
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getCFScriptScanner());

		reconciler.setDamager(dr, CfmlPartitionScanner.CF_SCRIPT);
		reconciler.setRepairer(dr, CFPartitionScanner.CF_SCRIPT);
		return reconciler;
		
	}
	*/
	
	/**
	 * gets the cfscript scanner (handles highlighting for cfscript
	 * partitons
	 * @return
	 */
	protected CFScriptScanner getCFScriptScanner() {		
		Token textToken = new Token(CfmlColorPreferences.DEFAULT_TEXT.getValue().getTextAttribute(this.getColorManager()));
		CFScriptScanner cfscriptscanner = new CFScriptScanner(null, null);
		cfscriptscanner.setDefaultReturnToken(textToken);		
		return cfscriptscanner;
	}
	
	
}