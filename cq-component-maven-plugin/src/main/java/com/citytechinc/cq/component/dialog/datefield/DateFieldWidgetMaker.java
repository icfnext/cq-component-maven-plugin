package com.citytechinc.cq.component.dialog.datefield;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateFieldWidgetMaker extends AbstractWidgetMaker {

	public DateFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		DateField dateFieldAnnotation = getAnnotation(DateField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		int startDay = getStartDayForField(dateFieldAnnotation);
		boolean showToday = getShowTodayForField(dateFieldAnnotation);
		String format = getFormatForField(dateFieldAnnotation);

		DateFieldWidgetParameters widgetParameters = new DateFieldWidgetParameters();
		widgetParameters.setStartDay(startDay);
		widgetParameters.setShowToday(showToday);
		widgetParameters.setFormat(format);
		widgetParameters.setFieldLabel(fieldLabel);
		widgetParameters.setFieldDescription(fieldDescription);
		widgetParameters.setAllowBlank(!isRequired);
		widgetParameters.setHideLabel(hideLabel);
		widgetParameters.setDefaultValue(defaultValue);
		widgetParameters.setName(name);
		widgetParameters.setFieldName(fieldName);
		widgetParameters.setAdditionalProperties(additionalProperties);
		widgetParameters.setListeners(getListeners());

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
