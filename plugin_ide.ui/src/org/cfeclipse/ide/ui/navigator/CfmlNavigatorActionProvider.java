/*******************************************************************************
 * Copyright (c) 2015 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package org.cfeclipse.ide.ui.navigator;

import org.eclipse.ui.IViewPart;

import org.cfeclipse.ide.ui.launch.CfmlLaunchShortcut;
import melnorme.lang.ide.ui.launch.LangLaunchShortcut;
import melnorme.lang.ide.ui.navigator.BuildTargetsActionGroup;
import melnorme.lang.ide.ui.navigator.LangNavigatorActionProvider;

public class CfmlNavigatorActionProvider extends LangNavigatorActionProvider {
	
	@Override
	protected BuildTargetsActionGroup createBuildTargetsActionGroup(IViewPart viewPart) {
		return new BuildTargetsActionGroup(viewPart) {
			@Override
			protected LangLaunchShortcut createLaunchShortcut() {
				return new CfmlLaunchShortcut();
			}
		};
	}
	
}