package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.DateTimeWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateTimeWidgetMaker extends AbstractWidgetMaker {

	public DateTimeWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() {

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		DateTimeWidget widget = new DateTimeWidget(fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue,
			name, fieldName, additionalProperties);

		setListeners(widget);

		return widget;

	}

}
