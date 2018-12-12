package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = DateField.class, makerClass = DateFieldWidgetMaker.class,
    resourceType = DateFieldCoral3Widget.RESOURCE_TYPE)
public class DateFieldCoral3Widget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/coral/foundation/form/datepicker";

    private final String minDate;

    private final String maxDate;

    private final String valueFormat; // default "YYYY-MM-DD[T]HH:mm:ss.000Z"

    private final String displayedFormat;

    public DateFieldCoral3Widget(DateFieldWidgetParameters parameters) {
        super(parameters);

        minDate = parameters.getMinDate();
        maxDate = parameters.getMaxDate();
        valueFormat = parameters.getValueFormat();
        displayedFormat = parameters.getDisplayedFormat();
    }

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getValueFormat() {
        return valueFormat;
    }

    public String getDisplayedFormat() {
        return displayedFormat;
    }
}