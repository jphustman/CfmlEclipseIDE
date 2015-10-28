package org.cfeclipse.ide.ui.preferences;


import melnorme.lang.ide.ui.LangUIPlugin_Actual;
import melnorme.lang.ide.ui.preferences.common.AbstractPreferencesBlock;
import melnorme.lang.ide.ui.tools.AbstractDeamonToolPrefPage;

public class CfmlDaemonPreferencePage extends AbstractDeamonToolPrefPage {
	
	@Override
	protected AbstractPreferencesBlock init_createPreferencesBlock() {
		return new ServerToolsBlock() {
			@Override
			protected String getDaemonToolName() {
				return LangUIPlugin_Actual.DAEMON_TOOL_Name;
			}
		};
	}
	
}