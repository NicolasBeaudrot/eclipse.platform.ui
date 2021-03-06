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
 *     Matthew Hall - initial API and implementation (bug 247997)
 *     Matthew Hall - bug 264307
 ******************************************************************************/

package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.property.map.DelegatingMapProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;

/**
 * @since 3.3
 *
 */
public class AnonymousBeanMapProperty extends DelegatingMapProperty {
	private final String propertyName;

	private Map delegates;

	/**
	 * @param propertyName
	 * @param keyType
	 * @param valueType
	 */
	public AnonymousBeanMapProperty(String propertyName, Class keyType,
			Class valueType) {
		super(keyType, valueType);
		this.propertyName = propertyName;
		this.delegates = new HashMap();
	}

	@Override
	protected IMapProperty doGetDelegate(Object source) {
		Class beanClass = source.getClass();
		if (delegates.containsKey(beanClass))
			return (IMapProperty) delegates.get(beanClass);

		IMapProperty delegate;
		try {
			delegate = BeanProperties.map(beanClass, propertyName,
					(Class) getKeyType(), (Class) getValueType());
		} catch (IllegalArgumentException noSuchProperty) {
			delegate = null;
		}
		delegates.put(beanClass, delegate);
		return delegate;
	}

	@Override
	public String toString() {
		String s = "?." + propertyName + "{:}"; //$NON-NLS-1$ //$NON-NLS-2$
		Class keyType = (Class) getKeyType();
		Class valueType = (Class) getValueType();
		if (keyType != null || valueType != null) {
			s += " <" + BeanPropertyHelper.shortClassName(keyType) + ", " //$NON-NLS-1$//$NON-NLS-2$
					+ BeanPropertyHelper.shortClassName(valueType) + ">"; //$NON-NLS-1$
		}
		return s;
	}
}
