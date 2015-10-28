/*******************************************************************************
 * Copyright (c) 2008, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package org.cfeclipse.ide.ui.preferences;


import melnorme.lang.ide.ui.preferences.common.AbstractPreferencesBlockPrefPage;

public class CfmlSourceColoringPreferencePage extends AbstractPreferencesBlockPrefPage {
	
	public final static String PAGE_ID = CfmlSourceColoringPreferencePage.class.getName();
	
	public CfmlSourceColoringPreferencePage() {
		super();
	}
	
	@Override
	protected String getHelpId() {
		return null;
	}
	
	@Override
	protected SourceColoringConfigurationBlock init_createPreferencesBlock() {
		return new SourceColoringConfigurationBlock();
	}
	
}