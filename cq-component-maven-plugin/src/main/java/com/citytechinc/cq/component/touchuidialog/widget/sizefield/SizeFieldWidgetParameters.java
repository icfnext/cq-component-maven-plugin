package com.citytechinc.cq.component.touchuidialog.widget.sizefield;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.touchuidialog.widget.numberfield.NumberFieldWidget;
import com.citytechinc.cq.component.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

public class SizeFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    protected NumberFieldWidget width;
    protected NumberFieldWidget height;

    public void setWidth(NumberFieldWidget width) {
        width.setFieldName("width");
        this.width = width;
    }

    public void setHeight(NumberFieldWidget height) {
        height.setFieldName("height");
        this.height = height;
    }

    @Override
    public List<? extends XmlElement> getContainedElements() {
        List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

        allContainedElements.add(width);
        allContainedElements.add(height);

        if (containedElements != null) {
            allContainedElements.addAll(containedElements);
        }

        return allContainedElements;
    }

    @Override
    public String getResourceType() {
        return SizeFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for DialogFieldSetWidget");
    }
}
