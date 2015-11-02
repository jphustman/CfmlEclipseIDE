package org.cfeclipse.ide.ui.text;

import org.eclipse.jface.text.rules.IRule;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.ThemedTextStylingPreference;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlTagScanner extends AbstractLangScanner {
	
	public CfmlTagScanner(TokenRegistry tokenStore, ThemedTextStylingPreference coloringItem) {
		super(tokenStore);
		setDefaultReturnToken(getToken(coloringItem));
	}
	
	@Override
	protected void initRules(ArrayList2<IRule> rules) {
	}
}
