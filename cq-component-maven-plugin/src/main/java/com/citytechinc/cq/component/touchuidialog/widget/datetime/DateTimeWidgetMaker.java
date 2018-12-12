package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

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
		widgetParameters.setMinDate(getMinDateForField(annotation));
		widgetParameters.setMaxDate(getMaxDateForField(annotation));

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

}
