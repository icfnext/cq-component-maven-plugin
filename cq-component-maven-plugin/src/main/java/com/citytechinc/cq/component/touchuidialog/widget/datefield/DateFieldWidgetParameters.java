package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class DateFieldWidgetParameters extends DefaultTouchUIWidgetParameters {

    protected String minDate;

    protected String maxDate;

    protected String storedFormat; // default "YYYY-MM-DD[T]HH:mm:ss.000Z"

    protected String valueFormat; // default "YYYY-MM-DD[T]HH:mm:ss.000Z"

    protected String displayedFormat;

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getStoredFormat() {
        return storedFormat;
    }

    public void setStoredFormat(String storedFormat) {
        this.storedFormat = storedFormat;
    }

    public String getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(String valueFormat) {
        this.valueFormat = valueFormat;
    }

    public String getDisplayedFormat() {
        return displayedFormat;
    }

    public void setDisplayedFormat(String displayedFormat) {
        this.displayedFormat = displayedFormat;
    }

    @Override
    public String getResourceType() {
        if (TouchUIDialogType.CORAL3.isOfType(getTouchUIDialogType())) {
            return DateFieldCoral3Widget.RESOURCE_TYPE;
        }
        return DateFieldWidget.RESOURCE_TYPE;
    }

    @Override
    public void setResourceType(String resourceType) {
        throw new UnsupportedOperationException("resourceType is Static for DateFieldWidget");
    }
}