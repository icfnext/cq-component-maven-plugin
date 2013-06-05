package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.DateFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class DateFieldWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException {

		DateField dateFieldAnnotation = field.getAnnotation(DateField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		int startDay = getStartDayForField(dateFieldAnnotation);
		boolean showToday = getShowTodayForField(dateFieldAnnotation);
		String format = getFormatForField(dateFieldAnnotation);

		DateFieldWidget widget = new DateFieldWidget(startDay, showToday, format, fieldLabel, fieldDescription, !isRequired, hideLabel,
			defaultValue, name, fieldName, additionalProperties);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

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
