package com.citytechinc.cq.component.dialog.datefield;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateFieldWidgetMaker extends AbstractWidgetMaker<DateFieldWidgetParameters> {

	public DateFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(DateFieldWidgetParameters widgetParameters) throws ClassNotFoundException {

		DateField dateFieldAnnotation = getAnnotation(DateField.class);

		int startDay = getStartDayForField(dateFieldAnnotation);
		boolean showToday = getShowTodayForField(dateFieldAnnotation);
		String format = getFormatForField(dateFieldAnnotation);

		widgetParameters.setStartDay(startDay);
		widgetParameters.setShowToday(showToday);
		widgetParameters.setFormat(format);

		return new DateFieldWidget(widgetParameters);
	}

	protected int getStartDayForField(DateField annotation) {
		return annotation.startDay();
	}

	protected boolean getShowTodayForField(DateField annotation) {
		return annotation.showToday();
	}

	protected String getFormatForField(DateField annotation) {
		return annotation.format();
	}

}
