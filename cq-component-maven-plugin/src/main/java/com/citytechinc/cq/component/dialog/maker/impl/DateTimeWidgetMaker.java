package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.DateTimeWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class DateTimeWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName) {

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		DateTimeWidget widget= new DateTimeWidget(fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName,
			additionalProperties);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

}
