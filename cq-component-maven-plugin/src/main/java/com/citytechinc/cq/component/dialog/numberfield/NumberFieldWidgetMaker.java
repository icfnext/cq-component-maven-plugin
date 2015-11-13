/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.citytechinc.cq.component.dialog.numberfield;

import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class NumberFieldWidgetMaker extends AbstractWidgetMaker<NumberFieldWidgetParameters> {

	public NumberFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(NumberFieldWidgetParameters parameters) throws ClassNotFoundException {

		NumberField numberFieldAnnotation = getAnnotation(NumberField.class);

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
