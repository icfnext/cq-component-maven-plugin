package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.SizeField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.SizeFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class SizeFieldMaker extends AbstractWidgetMaker {

	public SizeFieldMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		SizeField sizeFieldAnnotation = getAnnotation(SizeField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		String heightParameter = getHeightParameterForField(sizeFieldAnnotation);
		String heightPrefix = getHeightPrefixForField(sizeFieldAnnotation);
		String heightSuffix = getHeightSuffixForField(sizeFieldAnnotation);
		String widthParameter = getWidthParameterForField(sizeFieldAnnotation);
		String widthPrefix = getWidthPrefixForField(sizeFieldAnnotation);
		String widthSuffix = getWidthSuffixForField(sizeFieldAnnotation);
		int fieldWidth = getFieldWidthForField(sizeFieldAnnotation);

		SizeFieldWidget widget = new SizeFieldWidget(fieldLabel, fieldDescription, !isRequired, hideLabel,
			defaultValue, name, fieldName, additionalProperties, heightParameter, heightPrefix, heightSuffix,
			widthParameter, widthPrefix, widthSuffix, fieldWidth);

		setListeners(widget);

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
