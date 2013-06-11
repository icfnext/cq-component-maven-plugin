package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.HiddenWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class HiddenWidgetMaker extends AbstractWidgetMaker {

	public HiddenWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		Hidden hiddenFieldAnnotation = getAnnotation(Hidden.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		HiddenWidget widget = new HiddenWidget(hiddenFieldAnnotation.value(), fieldLabel, fieldDescription,
			!isRequired, hideLabel, defaultValue, name, fieldName, additionalProperties);

		setListeners(widget);

		return widget;

	}

}
