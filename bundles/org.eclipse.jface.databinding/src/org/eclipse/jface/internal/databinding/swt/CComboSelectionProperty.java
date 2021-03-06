/*******************************************************************************
 * Copyright (c) 2008, 2015 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 ******************************************************************************/

package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;

/**
 * @since 3.3
 *
 */
public class CComboSelectionProperty extends WidgetStringValueProperty {
	/**
	 *
	 */
	public CComboSelectionProperty() {
		super(SWT.Modify);
	}

	@Override
	String doGetStringValue(Object source) {
		return ((CCombo) source).getText();
	}

	@Override
	void doSetStringValue(Object source, String value) {
		CCombo ccombo = (CCombo) source;
		String items[] = ccombo.getItems();
		int index = -1;
		if (value == null) {
			ccombo.select(-1);
		} else if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (value.equals(items[i])) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				ccombo.setText(value);
			} else {
				ccombo.select(index); // -1 will not "unselect"
			}
		}
	}

	@Override
	public String toString() {
		return "CCombo.selection <String>"; //$NON-NLS-1$
	}
}
