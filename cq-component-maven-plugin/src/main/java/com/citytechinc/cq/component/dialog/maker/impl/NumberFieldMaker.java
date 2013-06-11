package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.NumberFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class NumberFieldMaker extends AbstractWidgetMaker {

	public NumberFieldMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		NumberField numberFieldAnnotation = getAnnotation(NumberField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		boolean allowDecimals = getAllowDecimalsForField(numberFieldAnnotation);
		boolean allowNegative = getAllowNegativeForField(numberFieldAnnotation);
		int decimalPrecision = getDecimalPrecisionForField(numberFieldAnnotation);
		String decimalSeparator = getDecimalSeparatorForField(numberFieldAnnotation);

		NumberFieldWidget widget = new NumberFieldWidget(allowDecimals, allowNegative, decimalPrecision,
			decimalSeparator, fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName,
			additionalProperties);

		setListeners(widget);

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
