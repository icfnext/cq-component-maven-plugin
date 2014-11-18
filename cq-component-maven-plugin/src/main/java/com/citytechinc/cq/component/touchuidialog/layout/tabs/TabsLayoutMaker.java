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

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.touchuidialog.container.Section;
import com.citytechinc.cq.component.touchuidialog.container.SectionParameters;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.LayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.columns.Column;
import com.citytechinc.cq.component.touchuidialog.layout.columns.ColumnParameters;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutElement;
import com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns.FixedColumnsLayoutElementParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.AbstractLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class TabsLayoutMaker extends AbstractLayoutMaker {

    public TabsLayoutMaker(LayoutMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public Layout make() throws LayoutMakerException {

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
        containedElements.add(makeItems());

        layoutParameters.setContainedElements(containedElements);

        layoutParameters.setFieldName("content");

        return new TabsLayout(layoutParameters);

    }

    protected Items makeItems() throws LayoutMakerException {

        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");

        Component componentAnnotation = null;
        try {
            componentAnnotation = (Component) parameters.getComponentClass().getAnnotation(Component.class);
        } catch (ClassNotFoundException e) {
            throw new LayoutMakerException("Class Not Found Exception encountered looking up Component annotation", e);
        }

        /*
         * Determine the Tabs to create
         */
        List<SectionParameters> tabParameters = new ArrayList<SectionParameters>();
        if (componentAnnotation.tabs().length > 0) {
            for (Tab currentTabAnnotation : componentAnnotation.tabs()) {
                SectionParameters currentTabParameters = new SectionParameters();

                currentTabParameters.setTitle(currentTabAnnotation.title());

                //Determine the layout to use for the Tab
                LayoutElement currentTabLayoutElement = new FixedColumnsLayoutElement(new FixedColumnsLayoutElementParameters());
                currentTabParameters.setLayoutElement(currentTabLayoutElement);

                tabParameters.add(currentTabParameters);
            }
        }
        else {
            SectionParameters currentTabParameters = new SectionParameters();

            currentTabParameters.setTitle(componentAnnotation.value());

            //Determine the layout to use for the Tab
            LayoutElement currentTabLayoutElement = new FixedColumnsLayoutElement(new FixedColumnsLayoutElementParameters());
            currentTabParameters.setLayoutElement(currentTabLayoutElement);

            tabParameters.add(currentTabParameters);
        }

        //Add Fields To Tabs
        ColumnParameters columnParameters = new ColumnParameters();
        columnParameters.setFieldName("column");

        try {
            List<TouchUIWidgetMakerParameters> widgetMakerParameters = TouchUIDialogUtil.getWidgetMakerParametersForComponentClass(parameters.getComponentClass(), parameters.getClassLoader(), parameters.getClassPool());

            for (TouchUIWidgetMakerParameters currentWidgetMakerParameters : widgetMakerParameters) {
                columnParameters.addItem(TouchUIWidgetFactory.make(currentWidgetMakerParameters, -1));
            }
        } catch (Exception e) {
            throw new LayoutMakerException("Exception encountered while constructing widgets for layout", e);
        }

        tabParameters.get(0).addItem(new Column(columnParameters));

        //Create all the Tabs
        List<Section> tabs = new ArrayList<Section>();
        for (int i=0; i < tabParameters.size(); i++) {
            SectionParameters currentSectionParameters = tabParameters.get(i);

            currentSectionParameters.setFieldName("tab_" + i);

            tabs.add(new Section(currentSectionParameters));
        }

        itemsParameters.setContainedElements(tabs);

        return new Items(itemsParameters);

    }

}
