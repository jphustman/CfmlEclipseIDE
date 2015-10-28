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
package org.cfeclipse.ide.ui.editor;

import melnorme.lang.ide.ui.editor.EditorUtils.OpenNewEditorMode;
import melnorme.lang.ide.ui.editor.LangHyperlinkDetector;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.texteditor.ITextEditor;

import org.cfeclipse.ide.ui.actions.CfmlOracleOpenDefinitionOperation;

public class CfmlHyperlinkDetector extends LangHyperlinkDetector {
	
	@Override
	protected AbstractLangElementHyperlink createHyperlink(IRegion requestedRegion, ITextEditor textEditor,
			IRegion wordRegion) {
		return new CfmlElementHyperlink(wordRegion, textEditor);
	}
	
	public class CfmlElementHyperlink extends AbstractLangElementHyperlink {
		
		public CfmlElementHyperlink(IRegion region, ITextEditor textEditor) {
			super(region, textEditor);
		}
		
		@Override
		public void open() {
			textEditor.doSave(new NullProgressMonitor());
			
			new CfmlOracleOpenDefinitionOperation(
				textEditor, getElementRange(), OpenNewEditorMode.TRY_REUSING_EXISTING).executeAndHandle();
		}
		
	}
	
}