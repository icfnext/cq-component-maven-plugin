/**
 * Copyright 2017 ICF Olson
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.container.items.Items;
import com.citytechinc.cq.component.touchuidialog.container.items.ItemsParameters;
import com.citytechinc.cq.component.touchuidialog.layout.Layout;
import com.citytechinc.cq.component.touchuidialog.layout.columns.Column;
import com.citytechinc.cq.component.touchuidialog.layout.columns.ColumnParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.AbstractLayoutMaker;
import com.citytechinc.cq.component.touchuidialog.layout.maker.LayoutMakerParameters;
import com.citytechinc.cq.component.touchuidialog.layout.maker.exceptions.LayoutMakerException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.factory.TouchUIWidgetFactory;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class FixedColumnsLayoutMaker extends AbstractLayoutMaker {

    public FixedColumnsLayoutMaker(LayoutMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public Layout make() throws LayoutMakerException {
        // Construct the FixedColumnsLayout Parameters
        FixedColumnsLayoutParameters layoutParameters = new FixedColumnsLayoutParameters();

        // Construct the appropriate Layout Element
        FixedColumnsLayoutElementParameters fixedColumnsLayoutElementParameters = new FixedColumnsLayoutElementParameters();

        // Add the Items to the layout element
        List<XmlElement> containedElements;

        FixedColumnsLayoutElement layoutElement = new FixedColumnsLayoutElement(fixedColumnsLayoutElementParameters);
        layoutElement.setFieldName("layout");

        // Add the Layout element ot the contained elements list
        containedElements = new ArrayList<XmlElement>();
        containedElements.add(layoutElement);
        containedElements.add(makeItems());
        layoutParameters.setContainedElements(containedElements);

        layoutParameters.setFieldName("content");

        return new FixedColumnsLayout(layoutParameters);
    }

    protected Items makeItems() throws LayoutMakerException {
        ItemsParameters itemsParameters = new ItemsParameters();
        itemsParameters.setFieldName("items");

        ColumnParameters columnParameters = new ColumnParameters();
        columnParameters.setFieldName("column");

        try {
            // Populate the column content
            List<TouchUIWidgetMakerParameters> widgetMakerParameters =
                TouchUIDialogUtil.getWidgetMakerParametersForComponentClass(parameters.getComponentClass(),
                    parameters.getClassLoader(), parameters.getClassPool(), parameters.getWidgetRegistry());

            for (TouchUIWidgetMakerParameters currentWidgetMakerParameters : widgetMakerParameters) {

                TouchUIDialogElement currentElement = TouchUIWidgetFactory.make(currentWidgetMakerParameters, -1);
                if (currentElement != null) {
                    currentElement.setRanking(currentWidgetMakerParameters.getDialogFieldConfig().getRanking());
                    columnParameters.addItem(currentElement);
                }

            }
        } catch (Exception e) {
            throw new LayoutMakerException("Exception encountered while constructing widgets for layout", e);
        }

        // Create and add the column section
        List<XmlElement> containedElements = new ArrayList<XmlElement>();
        containedElements.add(new Column(columnParameters));
        itemsParameters.setContainedElements(containedElements);

        return new Items(itemsParameters);
    }
}
