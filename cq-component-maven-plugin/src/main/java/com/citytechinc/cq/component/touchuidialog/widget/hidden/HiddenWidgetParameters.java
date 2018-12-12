package com.citytechinc.cq.component.touchuidialog.widget.hidden;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class HiddenWidgetParameters extends DefaultTouchUIWidgetParameters {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getResourceType() {
        return HiddenWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for HiddenWidget");
    }
}
