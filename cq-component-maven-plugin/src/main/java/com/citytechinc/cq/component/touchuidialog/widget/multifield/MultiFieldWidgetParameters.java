package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class MultiFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    private boolean composite;

    public boolean isComposite() {
        return composite;
    }

    public void setComposite(boolean composite) {
        this.composite = composite;
    }

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return MultiFieldCoral3Widget.RESOURCE_TYPE;
        }
        return MultiFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for MultiFieldWidget");
    }
}