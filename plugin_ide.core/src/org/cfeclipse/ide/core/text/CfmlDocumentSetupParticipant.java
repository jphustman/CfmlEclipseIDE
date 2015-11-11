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
package org.cfeclipse.ide.core.text;

import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

import melnorme.lang.ide.core.TextSettings_Actual;
import melnorme.lang.ide.core.text.LangDocumentPartitionerSetup;

public class CfmlDocumentSetupParticipant extends LangDocumentPartitionerSetup {

	/*
	@Override
	public FastPartitioner createDocumentPartitioner() {
		IPartitionTokenScanner scanner = TextSettings_Actual.createPartitionScanner();
		return new CFEPartitioner(scanner, LEGAL_CONTENT_TYPES);
	}
	*/

}