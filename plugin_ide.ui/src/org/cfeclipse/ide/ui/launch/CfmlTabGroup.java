package org.cfeclipse.ide.ui.launch;

import org.eclipse.debug.ui.ILaunchConfigurationTab;

import melnorme.lang.ide.ui.launch.AbstractLangTabGroup;


public class CfmlTabGroup extends AbstractLangTabGroup {
	
	@Override
	protected ILaunchConfigurationTab createMainLaunchConfigTab() {
		return new CfmlMainLaunchConfigurationTab();
	}
	
}