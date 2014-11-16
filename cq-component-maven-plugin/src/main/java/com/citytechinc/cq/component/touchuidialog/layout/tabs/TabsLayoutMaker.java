/**
 *    Copyright 2013 CITYTECH, Inc.
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
package com.citytechinc.cq.component.touchuidialog.layout.tabs;

import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.maker.AbstractLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class TabsLayoutMaker extends AbstractLayoutMaker {

    public TabsLayoutMaker(LayoutMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public Layout make() {

        //Construct the TabsLayout Parameters
        TabsLayoutParameters layoutParameters = new TabsLayoutParameters();

        //Construct the appropriate Layout Element
        TabsLayoutElementParameters tabsLayoutElementParameters = new TabsLayoutElementParameters();
        TabsLayoutElement layoutElement = new TabsLayoutElement(tabsLayoutElementParameters);

        //Construct the appropriate Items set
        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");
        Items items = new Items(itemsParameters);

        //Add both to the contained elements list
        List<XmlElement> containedElements = new ArrayList<XmlElement>();
        containedElements.add(layoutElement);
        containedElements.add(items);

        layoutParameters.setContainedElements(containedElements);

        layoutParameters.setFieldName("content");

        return new TabsLayout(layoutParameters);
    }

}
