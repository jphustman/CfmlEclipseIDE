package org.cfeclipse.ide.ui.text;

import org.eclipse.jface.text.rules.IRule;

import melnorme.lang.ide.ui.text.AbstractLangScanner;
import melnorme.lang.ide.ui.text.coloring.TokenRegistry;
import melnorme.utilbox.collections.ArrayList2;

public class CfmlStartTagEndScanner extends AbstractLangScanner {

	public CfmlStartTagEndScanner(TokenRegistry tokenRegistry) {
		super(tokenRegistry);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initRules(ArrayList2<IRule> rules) {
		// TODO Auto-generated method stub

	}

}
