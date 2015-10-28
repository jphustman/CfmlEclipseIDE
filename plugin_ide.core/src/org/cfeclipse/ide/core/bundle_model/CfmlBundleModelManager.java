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
package org.cfeclipse.ide.core.bundle_model;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

import melnorme.lang.ide.core.BundleInfo;
import melnorme.lang.ide.core.LangCore_Actual.CfmlBundleModel;
import melnorme.lang.ide.core.project_model.BundleManifestResourceListener;
import melnorme.lang.ide.core.project_model.BundleModelManager;

public class CfmlBundleModelManager extends BundleModelManager<CfmlBundleModel> {
	
	public CfmlBundleModelManager() {
		super(new CfmlBundleModel());
	}
	
	@Override
	protected BundleManifestResourceListener init_createResourceListener() {
		return new ManagerResourceListener(getDefaultBundleManifestPath());
	}
	
	@Override
	protected Path getDefaultBundleManifestPath() {
		return new Path("lang.bundle");
	}
	
	@Override
	protected BundleInfo createNewInfo(IProject project) {
		return new BundleInfo();
	}
	
}