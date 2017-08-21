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
package com.citytechinc.cq.component.touchuidialog.widget.dialogfieldset;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class DialogFieldSetWidgetParameters extends DefaultTouchUIWidgetParameters {

    protected List<TouchUIDialogElement> items;

    protected String text;

    protected String jcrTitle;

    public String getJcrTitle() {
        return jcrTitle;
    }

    public void setJcrTitle(String jcrTitle) {
        this.jcrTitle = jcrTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addItem(TouchUIDialogElement item) {
        if (items == null) {
            items = new ArrayList<TouchUIDialogElement>();
        }

        items.add(item);
    }

    public List<TouchUIDialogElement> getItems() {
        return items;
    }

    public void setItems(List<TouchUIDialogElement> items) {
        this.items = items;
    }

    public Items getItemsElement() {
        ItemsParameters itemsParameters = new ItemsParameters();

        itemsParameters.setFieldName("items");

        List<XmlElement> elements = new ArrayList<XmlElement>();

        if (!getItems().isEmpty()) {
            elements.addAll(getItems());
            itemsParameters.setContainedElements(elements);
            return new Items(itemsParameters);
        }

        return null;
    }

    @Override
    public List<? extends XmlElement> getContainedElements() {
        List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

        Items items = getItemsElement();

        if (items != null) {
            allContainedElements.add(items);
        }

        if (containedElements != null) {
            allContainedElements.addAll(containedElements);
        }

        return allContainedElements;
    }

    @Override
    public String getResourceType() {
	    if(TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
		    return DialogFieldSetCoral3Widget.RESOURCE_TYPE;
	    }
        return DialogFieldSetWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for DialogFieldSetWidget");
    }
}