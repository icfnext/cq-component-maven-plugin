package com.citytechinc.cq.component.dialog.datetime;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateTimeWidgetMaker extends AbstractWidgetMaker<DateTimeWidgetParameters> {

    public DateTimeWidgetMaker(WidgetMakerParameters parameters) {
        super(parameters);
    }

    @Override
    public DialogElement make(DateTimeWidgetParameters parameters) {
        return new DateTimeWidget(parameters);
    }

}
