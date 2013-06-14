package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.SimpleWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;
import com.citytechinc.cq.component.dialog.widget.WidgetParameters;

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

		WidgetParameters widgetParameters = new WidgetParameters();
		widgetParameters.setXtype(parameters.getXtype());
		widgetParameters.setName(name);
		widgetParameters.setFieldName(fieldName);
		widgetParameters.setFieldLabel(fieldLabel);
		widgetParameters.setFieldDescription(fieldDescription);
		widgetParameters.setAllowBlank(!isRequired);
		widgetParameters.setHideLabel(hideLabel);
		widgetParameters.setAdditionalProperties(additionalProperties);
		widgetParameters.setDefaultValue(defaultValue);
		widgetParameters.setListeners(getListeners());

		return new SimpleWidget(widgetParameters);

	}

}
