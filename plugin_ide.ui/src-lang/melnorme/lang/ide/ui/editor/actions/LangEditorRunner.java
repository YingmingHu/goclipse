/*******************************************************************************
 * Copyright (c) 2014, 2014 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.ui.editor.actions;


import static melnorme.utilbox.core.Assert.AssertNamespace.assertNotNull;
import melnorme.lang.ide.ui.actions.UIUserInteractionsHelper;
import melnorme.lang.ide.ui.editor.AbstractLangEditor;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;

public abstract class LangEditorRunner implements Runnable {
	
	protected final Shell shell;
	protected final IEditorPart part;
	
	public LangEditorRunner(Shell shell, IEditorPart part) {
		this.shell = assertNotNull(shell);
		this.part = part;
	}
	
	protected Shell getShell() {
		return shell;
	}
	
	@Override
	public void run() {
		if(part == null) {
			UIUserInteractionsHelper.openError(shell, "Internal Error", "No editor available.");
		}
		if(part instanceof AbstractLangEditor) {
			AbstractLangEditor langEditor = (AbstractLangEditor) part;
			runWithLangEditor(langEditor);
		} else {
			UIUserInteractionsHelper.openError(shell, "Internal Error", "Editor is not of the expected kind.");
		}
		
	}
	
	protected abstract void runWithLangEditor(AbstractLangEditor langEditor);
	
}