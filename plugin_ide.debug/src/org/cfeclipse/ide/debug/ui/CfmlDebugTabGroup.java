package org.cfeclipse.ide.debug.ui;

import melnorme.lang.ide.debug.ui.AbstractLangDebugTabGroup;

import org.eclipse.debug.ui.ILaunchConfigurationTab;

import org.cfeclipse.ide.ui.launch.CfmlMainLaunchConfigurationTab;


public class CfmlDebugTabGroup extends AbstractLangDebugTabGroup {
	
	@Override
	protected ILaunchConfigurationTab createMainLaunchConfigTab() {
		return new CfmlMainLaunchConfigurationTab();
	}
	
}