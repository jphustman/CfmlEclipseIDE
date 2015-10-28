package org.cfeclipse.ide.core;

import melnorme.lang.ide.core.LangCore_Actual;
import melnorme.lang.ide.core.LangNature;

public class CfmlNature extends LangNature {
	
	@Override
	protected String getBuilderId() {
		return LangCore_Actual.BUILDER_ID;
	}
	
}