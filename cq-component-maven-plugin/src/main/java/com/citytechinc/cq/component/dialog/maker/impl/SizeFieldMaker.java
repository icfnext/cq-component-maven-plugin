package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.SizeFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class SizeFieldMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws ClassNotFoundException {

		SizeField sizeFieldAnnotation = field.getAnnotation(SizeField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		String heightParameter = getHeightParameterForField(sizeFieldAnnotation);
		String heightPrefix = getHeightPrefixForField(sizeFieldAnnotation);
		String heightSuffix = getHeightSuffixForField(sizeFieldAnnotation);
		String widthParameter = getWidthParameterForField(sizeFieldAnnotation);
		String widthPrefix = getWidthPrefixForField(sizeFieldAnnotation);
		String widthSuffix = getWidthSuffixForField(sizeFieldAnnotation);
		int fieldWidth = getFieldWidthForField(sizeFieldAnnotation);

		SizeFieldWidget widget = new SizeFieldWidget(xtype, fieldLabel, fieldDescription, !isRequired, hideLabel,
			defaultValue, name, fieldName, additionalProperties, heightParameter, heightPrefix, heightSuffix,
			widthParameter, widthPrefix, widthSuffix, fieldWidth);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	private int getFieldWidthForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.fieldWidth();
	}

	private String getWidthSuffixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.widthSuffix())) {
			return sizeFieldAnnotation.widthSuffix();
		}
		return null;
	}

	private String getWidthPrefixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.widthPrefix())) {
			return sizeFieldAnnotation.widthPrefix();
		}
		return null;
	}

	private String getWidthParameterForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.widthParameter();
	}

	private String getHeightSuffixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.heightSuffix())) {
			return sizeFieldAnnotation.heightSuffix();
		}
		return null;
	}

	private String getHeightPrefixForField(SizeField sizeFieldAnnotation) {
		if (StringUtils.isNotEmpty(sizeFieldAnnotation.heightPrefix())) {
			return sizeFieldAnnotation.heightPrefix();
		}
		return null;
	}

	private String getHeightParameterForField(SizeField sizeFieldAnnotation) {
		return sizeFieldAnnotation.heightParameter();
	}

}
