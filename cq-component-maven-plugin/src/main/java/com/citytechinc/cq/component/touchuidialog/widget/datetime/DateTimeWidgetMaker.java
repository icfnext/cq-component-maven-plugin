package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import org.codehaus.plexus.util.StringUtils;

public class DateTimeWidgetMaker extends AbstractTouchUIWidgetMaker<DateTimeWidgetParameters> {

    public DateTimeWidgetMaker(TouchUIWidgetMakerParameters parameters) throws ClassNotFoundException {
        super(parameters);
    }

    @Override
    public TouchUIDialogElement make(DateTimeWidgetParameters widgetParameters) throws ClassNotFoundException {

        // Date field specific stuff
        DateTime annotation = getAnnotation(DateTime.class);

        widgetParameters.setDisplayedFormat(getDisplayedFormatForField(annotation));
        widgetParameters.setStoredFormat(getStoredFormatForField(annotation));
        widgetParameters.setValueFormat(getValueFormatForField(annotation));
        widgetParameters.setMinDate(getMinDateForField(annotation));
        widgetParameters.setMaxDate(getMaxDateForField(annotation));
        widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());


        if (TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
            return new DateTimeCoral3Widget(widgetParameters);
        }
        return new DateTimeWidget(widgetParameters);
    }

    protected String getMinDateForField(DateTime annotation) {
        if (StringUtils.isNotBlank(annotation.minDate())) {
            return annotation.minDate();
        }

        return null;
    }

    protected String getMaxDateForField(DateTime annotation) {
        if (StringUtils.isNotBlank(annotation.maxDate())) {
            return annotation.maxDate();
        }

        return null;
    }

    protected String getDisplayedFormatForField(DateTime annotation) {
        if (StringUtils.isNotBlank(annotation.displayedFormat())) {
            return annotation.displayedFormat();
        }

        return null;
    }

    protected String getStoredFormatForField(DateTime annotation) {
        if (StringUtils.isNotBlank(annotation.storedFormat())) {
            return annotation.storedFormat();
        }

        return null;
    }

    protected String getValueFormatForField(DateTime annotation) {
        return annotation.valueFormat();
    }
}