/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.dialogs;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.internal.registry.Category;
import org.eclipse.ui.internal.registry.IViewDescriptor;
import org.eclipse.ui.internal.registry.IViewRegistry;
import org.eclipse.ui.internal.roles.RoleManager;

public class ViewContentProvider implements ITreeContentProvider {
/**
	 * Create a new instance of the ViewContentProvider.
 */
public ViewContentProvider() {
	super();
}
public void dispose() {
}
/**
 * Returns the child elements of the given parent element.
 */
public Object[] getChildren(Object element) {
		if (element instanceof IViewRegistry) {
			IViewRegistry reg = (IViewRegistry) element;
			Category[] categories = reg.getCategories();
			ArrayList filtered = new ArrayList();
			for (int i = 0; i < categories.length; i++) {
				if (RoleManager.getInstance().isEnabledId(categories[i].getId()))
					filtered.add(categories[i]);
			}
			return filtered.toArray();
		} else if (element instanceof Category) {
			ArrayList list = ((Category) element).getElements();
			if (list != null) {
				ArrayList filtered = new ArrayList();
				Iterator elements = list.iterator();
				while (elements.hasNext()) {
					IViewDescriptor next = (IViewDescriptor) elements.next();
					if (RoleManager.getInstance().isEnabledId(next.getId()))
						filtered.add(next);
				}
				return filtered.toArray();
			}

		} else {
			return new Object[0];
		}

		return new Object[0];
	}
/**
 * Return the children of an element.
 */
public Object[] getElements(Object element) {
	return getChildren(element);
}
/**
 * Returns the parent for the given element, or <code>null</code> 
 * indicating that the parent can't be computed. 
 */
public Object getParent(Object element) {
	return null;
}
/**
 * Returns whether the given element has children.
 */
public boolean hasChildren(java.lang.Object element) {
	if (element instanceof IViewRegistry)
		return true;
	else if (element instanceof Category)
		return true;
	return false;
}
public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
}
public boolean isDeleted(Object element) {
	return false;
}
}
