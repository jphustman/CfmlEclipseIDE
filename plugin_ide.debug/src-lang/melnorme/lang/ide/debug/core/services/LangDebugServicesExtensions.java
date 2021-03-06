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
package melnorme.lang.ide.debug.core.services;

import org.eclipse.cdt.dsf.debug.service.IDsfDebugServicesFactory;
import org.eclipse.cdt.dsf.debug.service.IExpressions;
import org.eclipse.cdt.dsf.gdb.service.GDBBackend;
import org.eclipse.cdt.dsf.gdb.service.GDBPatternMatchingExpressions;
import org.eclipse.cdt.dsf.mi.service.IMIBackend;
import org.eclipse.cdt.dsf.mi.service.IMIExpressions;
import org.eclipse.cdt.dsf.mi.service.MIExpressions;
import org.eclipse.cdt.dsf.mi.service.MIVariableManager;
import org.eclipse.cdt.dsf.service.DsfServicesTracker;
import org.eclipse.cdt.dsf.service.DsfSession;
import org.eclipse.debug.core.ILaunchConfiguration;

public class LangDebugServicesExtensions implements IDsfDebugServicesFactory {
	
	protected final IDsfDebugServicesFactory parentServiceFactory;
	
	public LangDebugServicesExtensions(IDsfDebugServicesFactory parentServiceFactory) {
		this.parentServiceFactory = parentServiceFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <V> V createService(Class<V> clazz, DsfSession session, Object... optionalArguments) {
		
		if(IExpressions.class.isAssignableFrom(clazz)) {
			return (V) createExpressionService(session);
		}
		
		if(IMIBackend.class.isAssignableFrom(clazz)) {
			for (Object arg : optionalArguments) {
				if (arg instanceof ILaunchConfiguration) {
					return (V) createBackendGDBService(session, (ILaunchConfiguration) arg);
				}
			}
		} 
		
		return parentServiceFactory.createService(clazz, session, optionalArguments);
	}
	
	public IExpressions createExpressionService(DsfSession session) {
		IMIExpressions originialExpressionService = new MIExpressions(session) {
			@Override
			protected MIVariableManager createMIVariableManager() {
				return services_MIExpressions_createMIVariableManager(getSession(), getServicesTracker());
			}
		};
		return new GDBPatternMatchingExpressions(session, originialExpressionService);
	}
	
	protected MIVariableManager services_MIExpressions_createMIVariableManager(DsfSession session,
			DsfServicesTracker servicesTracker) {
		return new MIVariableManager_LangExtension(session, servicesTracker);
	}
	
	public IMIBackend createBackendGDBService(DsfSession session, ILaunchConfiguration lc) {
		return new GDBBackend(session, lc);
	}
	
}