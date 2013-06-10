package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.NumberFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class NumberFieldMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws ClassNotFoundException {

		NumberField numberFieldAnnotation = field.getAnnotation(NumberField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean allowDecimals = getAllowDecimalsForField(numberFieldAnnotation);
		boolean allowNegative = getAllowNegativeForField(numberFieldAnnotation);
		int decimalPrecision = getDecimalPrecisionForField(numberFieldAnnotation);
		String decimalSeparator = getDecimalSeparatorForField(numberFieldAnnotation);

		NumberFieldWidget widget = new NumberFieldWidget(allowDecimals, allowNegative, decimalPrecision,
			decimalSeparator, fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName,
			additionalProperties);

		setListeners(widget, field.getAnnotation().listeners());

		return widget;

	}

	protected boolean getAllowDecimalsForField(NumberField annotation) {
		if (annotation != null) {
			return annotation.allowDecimals();
		}

		return NumberField.ALLOW_DECIMALS_DEFAULT;
	}

	protected boolean getAllowNegativeForField(NumberField annotation) {
		if (annotation != null) {
			return annotation.allowNegative();
		}

		return NumberField.ALLOW_NEGATIVE_DEFAULT;
	}

	protected int getDecimalPrecisionForField(NumberField annotation) {
		if (annotation != null) {
			return annotation.decimalPrecision();
		}

		return NumberField.DECIMAL_PRECISION_DEFAULT;
	}

	protected String getDecimalSeparatorForField(NumberField annotation) {
		if (annotation != null) {
			return annotation.decimalSeparator();
		}

		return NumberField.DECIMAL_SEPARATOR_DEFAULT;
	}

}
