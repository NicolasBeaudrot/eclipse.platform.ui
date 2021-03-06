/**
 * Copyright (c) 2008, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      IBM Corporation - initial API and implementation
 */
package org.eclipse.e4.ui.model.application.ui.menu;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trim Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 * Provisional for 4.3. This represents a potential extension to some trim bar.
 * </p>
 * @noreference This interface is not intended to be referenced by clients.
 * @since 1.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution#getParentId <em>Parent Id</em>}</li>
 *   <li>{@link org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution#getPositionInParent <em>Position In Parent</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface MTrimContribution extends MElementContainer<MTrimElement> {

	/**
	 * Returns the value of the '<em><b>Parent Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The element id of the TrimBar to be contributed to.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent Id</em>' attribute.
	 * @see #setParentId(String)
	 * @model
	 * @generated
	 */
	String getParentId();

	/**
	 * Sets the value of the '{@link org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution#getParentId <em>Parent Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Id</em>' attribute.
	 * @see #getParentId()
	 * @generated
	 */
	void setParentId(String value);

	/**
	 * Returns the value of the '<em><b>Position In Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * Indicates the position in the TrimBar where this contribution should be placed.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Position In Parent</em>' attribute.
	 * @see #setPositionInParent(String)
	 * @model
	 * @generated
	 */
	String getPositionInParent();

	/**
	 * Sets the value of the '{@link org.eclipse.e4.ui.model.application.ui.menu.MTrimContribution#getPositionInParent <em>Position In Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position In Parent</em>' attribute.
	 * @see #getPositionInParent()
	 * @generated
	 */
	void setPositionInParent(String value);
} // MTrimContribution
