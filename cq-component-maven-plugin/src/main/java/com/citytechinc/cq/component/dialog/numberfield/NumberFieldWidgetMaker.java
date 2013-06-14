package com.citytechinc.cq.component.dialog.numberfield;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class NumberFieldWidgetMaker extends AbstractWidgetMaker {

	public NumberFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		NumberField numberFieldAnnotation = getAnnotation(NumberField.class);

		NumberFieldWidgetParameters parameters = new NumberFieldWidgetParameters();

		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

		parameters.setAllowDecimals(getAllowDecimalsForField(numberFieldAnnotation));
		parameters.setAllowNegative(getAllowNegativeForField(numberFieldAnnotation));
		parameters.setDecimalPrecision(getDecimalPrecisionForField(numberFieldAnnotation));
		parameters.setDecimalSeparator(getDecimalSeparatorForField(numberFieldAnnotation));

		return new NumberFieldWidget(parameters);

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
