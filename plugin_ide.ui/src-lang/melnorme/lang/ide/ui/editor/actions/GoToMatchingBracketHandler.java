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
package melnorme.lang.ide.ui.editor.actions;

import melnorme.lang.ide.ui.editor.AbstractLangEditor;
import melnorme.lang.ide.ui.editor.LangEditorMessages;

import org.eclipse.ui.IWorkbenchPage;


public class GoToMatchingBracketHandler extends AbstractEditorHandler {
	
	public GoToMatchingBracketHandler(IWorkbenchPage page) {
		super(page);
	}
	
	@Override
	protected String getOperationName() {
		return LangEditorMessages.GotoMatchingBracket_error_title;
	}
	
	@Override
	protected void doRunWithEditor(AbstractLangEditor editor) {
		editor.getGotoMatchingBracketManager().gotoMatchingBracket();
	}
	
}