package org.cfeclipse.ide.ui;

import melnorme.lang.ide.ui.LangUIPlugin;

import org.osgi.framework.BundleContext;

public class CfmlUIPlugin extends LangUIPlugin {
	
	@Override
	protected CfmlOperationsConsoleListener createOperationsConsoleListener() {
		return new CfmlOperationsConsoleListener();
	}
	
	@Override
	protected void doCustomStop(BundleContext context) {
	}
	
}