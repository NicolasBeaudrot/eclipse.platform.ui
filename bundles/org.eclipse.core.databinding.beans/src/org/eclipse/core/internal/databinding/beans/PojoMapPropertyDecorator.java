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
 *     Matthew Hall - initial API and implementation (bug 195222)
 *     Matthew Hall - bug 264307
 ******************************************************************************/

package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.util.Map;

import org.eclipse.core.databinding.beans.IBeanMapProperty;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;

/**
 * @since 3.3
 *
 */
public class PojoMapPropertyDecorator extends MapProperty implements
		IBeanMapProperty {
	private final IMapProperty delegate;
	private final PropertyDescriptor propertyDescriptor;

	/**
	 * @param delegate
	 * @param propertyDescriptor
	 */
	public PojoMapPropertyDecorator(IMapProperty delegate,
			PropertyDescriptor propertyDescriptor) {
		this.delegate = delegate;
		this.propertyDescriptor = propertyDescriptor;
	}

	@Override
	public Object getKeyType() {
		return delegate.getKeyType();
	}

	@Override
	public Object getValueType() {
		return delegate.getValueType();
	}

	@Override
	protected Map doGetMap(Object source) {
		return delegate.getMap(source);
	}

	@Override
	protected void doSetMap(Object source, Map map) {
		delegate.setMap(source, map);
	}

	@Override
	protected void doUpdateMap(Object source, MapDiff diff) {
		delegate.updateMap(source, diff);
	}

	@Override
	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	@Override
	public IBeanMapProperty values(String propertyName) {
		return values(propertyName, null);
	}

	@Override
	public IBeanMapProperty values(String propertyName, Class valueType) {
		Class beanClass = (Class) delegate.getValueType();
		return values(PojoProperties.value(beanClass, propertyName, valueType));
	}

	@Override
	public IBeanMapProperty values(IBeanValueProperty property) {
		return new PojoMapPropertyDecorator(super.values(property),
				property.getPropertyDescriptor());
	}

	@Override
	public IObservableMap observe(Object source) {
		return new BeanObservableMapDecorator(delegate.observe(source),
				propertyDescriptor);
	}

	@Override
	public IObservableMap observe(Realm realm, Object source) {
		return new BeanObservableMapDecorator(delegate.observe(realm, source),
				propertyDescriptor);
	}

	@Override
	public IObservableMap observeDetail(IObservableValue master) {
		return new BeanObservableMapDecorator(delegate.observeDetail(master),
				propertyDescriptor);
	}

	@Override
	public String toString() {
		return delegate.toString();
	}
}
