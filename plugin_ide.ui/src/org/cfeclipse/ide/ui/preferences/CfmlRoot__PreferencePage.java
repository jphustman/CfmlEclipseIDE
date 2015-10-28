/*******************************************************************************
 * Copyright (c) 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package org.cfeclipse.ide.ui.preferences;

import melnorme.lang.ide.ui.preferences.LangRootPreferencePage;
import melnorme.lang.ide.ui.preferences.LangSDKConfigBlock;
import melnorme.lang.tooling.data.CfmlSDKLocationValidator;
import melnorme.lang.tooling.ops.SDKLocationValidator;


/**
 * The main/root preference page.
 */
public class CfmlRoot__PreferencePage extends LangRootPreferencePage {
	
	public CfmlRoot__PreferencePage() {
		super();
	}
	
	@Override
	protected String getHelpId() {
		return null;
	}
	
	@Override
	protected LangSDKConfigBlock init_createLangSDKConfigBlock() {
		return new CfmlSDKConfigBlock();
	}
	
	public static class CfmlSDKConfigBlock extends LangSDKConfigBlock {
		
		public CfmlSDKConfigBlock() {
			super(null);
		}
		
		@Override
		protected SDKLocationValidator getSDKValidator() {
			return new CfmlSDKLocationValidator();
		}
	}
	
}