/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.presentations.newapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
 * @since 3.0
 */
public abstract class AbstractTabFolder {
	private List listeners = new ArrayList(1);
	private Control toolbar;
	
	public abstract Point computeMinimumSize();
	public abstract AbstractTabItem add(int index);
	public abstract Rectangle getClientArea();
	public abstract Control getControl();
	public abstract AbstractTabItem getItem(Point location);
	public abstract AbstractTabItem[] getItems();
	public abstract void layout(boolean flushCache);
	public abstract void setSelection(AbstractTabItem toSelect);
	public abstract void setTabPosition(int tabPosition);
	public abstract void setActive(int activeState);
	public abstract int getTabPosition();
	
	public abstract Point getPaneMenuLocation();
	
	public AbstractTabItem getItem(int idx) {
		return getItems()[idx];
	}
	
	/**
	 * Returns the index of the given item, or -1 if the given item is
	 * not found in this tab folder. Subclasses should override this if
	 * the underlying SWT widget has an equivalent method
	 * 
	 * @param item item to find
	 * @return the index of the given item or -1
	 */
	public int indexOf(AbstractTabItem item) {
		AbstractTabItem[] items = getItems();
		
		for (int idx = 0; idx < items.length; idx++) {
			AbstractTabItem next = items[idx];
			
			if (next == item) {
				return idx;
			}
		}
		
		return -1;
	}
	
	public int getItemCount() {
		return getItems().length;
	}
	
	/**
	 * Enables or disables the maximized state
	 * 
	 * @param isVisible true iff the maximized state is supported
	 */
	public void allowMaximizeButton(boolean isVisible) {
	}
	
	public Control[] getTabList() {
		if (toolbar != null) {
			return new Control[] {getControl(), toolbar};
		} else {
			return new Control[] {getControl()};
		}
	}

	public void setToolbar(Control toolbarControl) {
		this.toolbar = toolbarControl;
	}

	public final Control getToolbar() {
		return toolbar;
	}
	
	/**
	 * Enables or disables the minimized state
	 * 
	 * @param isVisible true iff the minimized state is supported
	 */
	public void allowMinimizeButton(boolean isVisible) {
	}
	
	/**
	 * Sets the current state for the folder
	 * 
	 * @param state one of the IStackPresentationSite.STATE_* constants
	 */
	public void setState(int state) {
	}
	
	/**
	 * Returns the title area for this control (in the control's coordinate system)
	 * 
	 * @return
	 */
	public abstract Rectangle getTabArea();
	
	/**
	 * Called when the tab folder's control is disposed. If the subclass has
	 * registered any listeners with other objects, they should be removed
	 * here.
	 */
	public void disposed() {
	}
	
	/**
	 * Called when the tab folder's shell becomes active or inactive. Subclasses
	 * can override this to change the appearance of the tabs based on activation.
	 * 
	 * @param isActive
	 */
	public void shellActive(boolean isActive) {
	}
	
	/**
	 * Adds the given listener to this AbstractTabFolder
	 * 
	 * @param newListener the listener to add
	 */
	public final void addListener(AbstractTabFolderListener newListener) {
		listeners.add(newListener);
	}
	
	/**
	 * Removes the given listener from this AbstractTabFolder
	 * 
	 * @param toRemove the listener to remove
	 */
	public final void removeListener(AbstractTabFolderListener toRemove) {
		listeners.remove(toRemove);
	}
	
	/**
	 * Fires a stateButtonPressed event to all AbstractTabFolderListeners. Convenience
	 * method for subclasses.
	 * 
	 * @param buttonId one of the IStackPresentationSite.STATE_* constants
	 */
	protected final void fireStateButtonPressed(int buttonId) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			AbstractTabFolderListener next = (AbstractTabFolderListener) iter.next();
			
			next.stateButtonPressed(buttonId);
		}
	}
	
	protected final void fireDragStart(AbstractTabItem tab, Point displayLoc) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			AbstractTabFolderListener next = (AbstractTabFolderListener) iter.next();
			
			next.dragStart(tab, displayLoc);
		}		
	}
	
	/**
	 * Fires a closeButtonPressed event to all AbstractTabFolderListeners. Convenience
	 * method for subclasses.
	 * 
	 * @param item the item whose close button was pressed
	 */
	protected final void fireCloseButtonPressed(AbstractTabItem item) {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			AbstractTabFolderListener next = (AbstractTabFolderListener) iter.next();
			
			next.closeButtonPressed(item);
		}		
	}
	
	/**
	 * Fires a showList event to all AbstractTabFolderListeners. Convenience
	 * method for subclasses.
	 */
	protected final void showList() {
		for (Iterator iter = listeners.iterator(); iter.hasNext();) {
			AbstractTabFolderListener next = (AbstractTabFolderListener) iter.next();
			
			next.showList();
		}
	}
	
}
