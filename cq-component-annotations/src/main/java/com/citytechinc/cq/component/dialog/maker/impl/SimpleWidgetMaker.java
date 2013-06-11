package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.SimpleWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class SimpleWidgetMaker extends AbstractWidgetMaker {

	public SimpleWidgetMaker(WidgetMakerParameters parameters) {
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

		SimpleWidget widget = new SimpleWidget(parameters.getXtype(), name, fieldName, fieldLabel, fieldDescription,
			isRequired, hideLabel, additionalProperties, defaultValue);

		setListeners(widget);

		return widget;

	}

}
