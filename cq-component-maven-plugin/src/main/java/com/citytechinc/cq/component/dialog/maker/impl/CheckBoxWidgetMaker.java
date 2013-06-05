package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.CheckBoxWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class CheckBoxWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) throws ClassNotFoundException {

		CheckBox checkBoxAnnotation = field.getAnnotation(CheckBox.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		String inputValue = getInputValueForField(checkBoxAnnotation);
		boolean checked = getCheckedForField(checkBoxAnnotation);

		CheckBoxWidget widget= new CheckBoxWidget(inputValue, checked, fieldLabel, fieldDescription, !isRequired, hideLabel,
			defaultValue, name, fieldName, additionalProperties);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	protected String getInputValueForField(CheckBox annotation) {
		return annotation.inputValue();
	}

	protected boolean getCheckedForField(CheckBox annotation) {
		return annotation.checked();
	}

}
