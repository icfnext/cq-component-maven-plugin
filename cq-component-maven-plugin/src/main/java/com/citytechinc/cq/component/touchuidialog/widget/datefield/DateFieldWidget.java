package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = DateField.class, makerClass = DateFieldWidgetMaker.class,
    resourceType = DateFieldWidget.RESOURCE_TYPE)
public class DateFieldWidget extends AbstractTouchUIWidget {

    public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/datepicker";

    private final String minDate;

    private final String maxDate;

    private final String storedFormat; // default "YYYY-MM-DD[T]HH:mm:ss.000Z"

    private final String displayedFormat;

    public DateFieldWidget(DateFieldWidgetParameters parameters) {
        super(parameters);

        minDate = parameters.getMinDate();
        maxDate = parameters.getMaxDate();
        storedFormat = parameters.getStoredFormat();
        displayedFormat = parameters.getDisplayedFormat();
    }

    public String getMinDate() {
        return minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getStoredFormat() {
        return storedFormat;
    }

    public String getDisplayedFormat() {
        return displayedFormat;
    }
}
