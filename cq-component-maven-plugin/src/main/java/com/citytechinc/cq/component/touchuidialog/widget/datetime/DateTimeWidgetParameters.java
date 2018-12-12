package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.datefield.DateFieldWidgetParameters;

public class DateTimeWidgetParameters extends DateFieldWidgetParameters {

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return DateTimeCoral3Widget.RESOURCE_TYPE;
        }
        return DateTimeWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for DateTimeWidget");
    }
}