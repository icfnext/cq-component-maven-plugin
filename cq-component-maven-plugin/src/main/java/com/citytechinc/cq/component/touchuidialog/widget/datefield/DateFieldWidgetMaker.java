package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogType;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class DateFieldWidgetMaker extends AbstractTouchUIWidgetMaker<DateFieldWidgetParameters> {

	public DateFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(DateFieldWidgetParameters widgetParameters) throws ClassNotFoundException {
		// Date field specific stuff
		DateField annotation = getAnnotation(DateField.class);

		widgetParameters.setDisplayedFormat(getDisplayedFormatForField(annotation));
		widgetParameters.setStoredFormat(getStoredFormatForField(annotation));
		widgetParameters.setValueFormat(getValueFormatForField(annotation));
		widgetParameters.setMinDate(getMinDateForField(annotation));
		widgetParameters.setMaxDate(getMaxDateForField(annotation));
		widgetParameters.setTouchUIDialogType(parameters.getTouchUIDialogType());

		if(TouchUIDialogType.CORAL3.isOfType(widgetParameters.getTouchUIDialogType())) {
			return new DateFieldCoral3Widget(widgetParameters);
		}
		return new DateFieldWidget(widgetParameters);
	}

	protected String getMinDateForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.minDate())) {
			return annotation.minDate();
		}

		return null;
	}

	protected String getMaxDateForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.maxDate())) {
			return annotation.maxDate();
		}

		return null;
	}

	protected String getDisplayedFormatForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.displayedFormat())) {
			return annotation.displayedFormat();
		}

		return null;
	}

	protected String getStoredFormatForField(DateField annotation) {
		return annotation.storedFormat();
	}

	protected String getValueFormatForField(DateField annotation) {
		return annotation.valueFormat();
	}
}