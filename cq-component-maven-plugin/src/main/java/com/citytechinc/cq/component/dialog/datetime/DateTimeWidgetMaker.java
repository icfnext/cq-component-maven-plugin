package com.citytechinc.cq.component.dialog.datetime;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateTimeWidgetMaker extends AbstractWidgetMaker {

	public DateTimeWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() {

		DateTimeWidgetParameters parameters = new DateTimeWidgetParameters();

		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setAdditionalProperties(getAdditionalPropertiesForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

		return new DateTimeWidget(parameters);
	}

}
