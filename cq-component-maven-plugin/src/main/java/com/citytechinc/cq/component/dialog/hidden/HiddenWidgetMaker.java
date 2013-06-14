package com.citytechinc.cq.component.dialog.hidden;

import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class HiddenWidgetMaker extends AbstractWidgetMaker {

	public HiddenWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		Hidden hiddenFieldAnnotation = getAnnotation(Hidden.class);

		HiddenWidgetParameters parameters = new HiddenWidgetParameters();

		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

		parameters.setValue(hiddenFieldAnnotation.value());

		return new HiddenWidget(parameters);

	}
}
