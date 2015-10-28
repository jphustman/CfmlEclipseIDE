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
package org.cfeclipse.ide.core.operations;

import org.eclipse.core.runtime.CoreException;

import melnorme.lang.ide.core.LangCore;
import melnorme.lang.ide.core.operations.LangProjectBuilder;
import melnorme.utilbox.core.CommonException;

public class CfmlBuilder extends LangProjectBuilder {
	
	public CfmlBuilder() {
	}
	
	@Override
	protected ProcessBuilder createCleanPB() throws CoreException, CommonException {
		// TODO: Lang clean command
		return LangCore.getToolManager().createSDKProcessBuilder(getProject(), "clean");
	}
	
}