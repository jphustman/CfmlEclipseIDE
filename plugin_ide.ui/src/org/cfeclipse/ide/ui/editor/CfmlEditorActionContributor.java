/*******************************************************************************
 * Copyright (c) 2014, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package org.cfeclipse.ide.ui.editor;

import org.eclipse.ui.texteditor.ITextEditor;

import org.cfeclipse.ide.ui.actions.CfmlOracleOpenDefinitionOperation;
import melnorme.lang.ide.ui.editor.EditorUtils.OpenNewEditorMode;
import melnorme.lang.ide.ui.editor.LangEditorActionContributor;
import melnorme.lang.tooling.ast.SourceRange;

public class CfmlEditorActionContributor extends LangEditorActionContributor {
	
	@Override
	protected CfmlOracleOpenDefinitionOperation createOpenDefinitionOperation(ITextEditor editor,
			SourceRange range, OpenNewEditorMode newEditorMode) {
		return new CfmlOracleOpenDefinitionOperation(editor, range, newEditorMode);
	}
	
	@Override
	protected void registerOtherEditorHandlers() {
	}
	
}