package com.citytechinc.cq.component.dialog.datefield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = DateField.class, makerClass = DateFieldWidgetMaker.class, xtype = DateFieldWidget.XTYPE)
public class DateFieldWidget extends AbstractWidget {

    public static final String XTYPE = "datefield";

    private final int startDay;

    private final boolean showToday;

    private final String format;

    public DateFieldWidget(DateFieldWidgetParameters parameters) {
        super(parameters);
        this.startDay = parameters.getStartDay();
        this.showToday = parameters.isShowToday();
        this.format = parameters.getFormat();
    }

    public int getStartDay() {
        return startDay;
    }

    public boolean isShowToday() {
        return showToday;
    }

    public String getFormat() {
        return format;
    }

}
