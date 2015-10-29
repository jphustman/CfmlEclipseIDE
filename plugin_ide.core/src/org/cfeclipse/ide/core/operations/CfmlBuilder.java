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

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import melnorme.lang.ide.core.LangCore;
import melnorme.lang.ide.core.operations.LangProjectBuilder;
import melnorme.utilbox.concurrency.OperationCancellation;
import melnorme.utilbox.core.CommonException;

public class CfmlBuilder extends LangProjectBuilder {
	
	public CfmlBuilder() {
	}
	
	@Override
	protected ProcessBuilder createCleanPB() throws CoreException, CommonException {
		// TODO: Lang clean command
		return LangCore.getToolManager().createSDKProcessBuilder(getProject(), "clean");
	}
	
	@Override
	protected IProject[] doBuild(IProject project, int kind, Map<String, String> args, IProgressMonitor monitor)
			throws CoreException, OperationCancellation {
		// TODO Auto-generated method stub
		//return super.doBuild(project, kind, args, monitor);
		return null;
	}
	
}