/**
 *    Copyright 2017 ICF Olson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.container;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.LayoutElement;
import com.citytechinc.cq.component.xml.XmlElement;

public class ContainerParameters extends DefaultTouchUIDialogElementParameters {

	private LayoutElement layoutElement;

	protected List<TouchUIDialogElement> items;

	public ContainerParameters() {
		items = new ArrayList<TouchUIDialogElement>();
	}

	@Override
	public String getResourceType() {
		return Container.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resource type is Static for Container");
	}

	@Override
	public String getPrimaryType() {
		return Container.PRIMARY_TYPE;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("primary type is Static for Container");
	}

	public LayoutElement getLayoutElement() {
		return layoutElement;
	}

	public void setLayoutElement(LayoutElement layoutElement) {
		this.layoutElement = layoutElement;
	}

	public List<TouchUIDialogElement> getItems() {
		return items;
	}

	public Items getItemsElement() {
		ItemsParameters itemsParameters = new ItemsParameters();

		itemsParameters.setFieldName("items");

		List<XmlElement> elements = new ArrayList<XmlElement>();

		if (!items.isEmpty()) {
			elements.addAll(items);
			itemsParameters.setContainedElements(elements);
			return new Items(itemsParameters);
		}

		return null;
	}

	public void addItem(TouchUIDialogElement element) {
		items.add(element);
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {

		List<XmlElement> elements = new ArrayList<XmlElement>();

		if (layoutElement != null) {
			elements.add(layoutElement);
		}

		Items items = getItemsElement();

		if (items != null) {
			elements.add(items);
		}
		if (super.getContainedElements() != null) {
			elements.addAll(super.getContainedElements());
		}

		return elements;
	}

}
