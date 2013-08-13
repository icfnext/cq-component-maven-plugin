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
package com.citytechinc.cq.component.dialog.checkbox;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Listeners;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class CheckBoxWidgetMaker extends AbstractWidgetMaker {

	public CheckBoxWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		CheckBox checkBoxAnnotation = getAnnotation(CheckBox.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		String inputValue = getInputValueForField(checkBoxAnnotation);
		boolean checked = getCheckedForField(checkBoxAnnotation);

		Listeners listeners = getListeners();

		CheckBoxWidgetParameters widgetParameters = new CheckBoxWidgetParameters();
		widgetParameters.setInputValue(inputValue);
		widgetParameters.setChecked(checked);
		widgetParameters.setFieldLabel(fieldLabel);
		widgetParameters.setFieldDescription(fieldDescription);
		widgetParameters.setAllowBlank(!isRequired);
		widgetParameters.setHideLabel(hideLabel);
		widgetParameters.setDefaultValue(defaultValue);
		widgetParameters.setName(name);
		widgetParameters.setFieldName(fieldName);
		widgetParameters.setAdditionalProperties(additionalProperties);
		widgetParameters.setListeners(listeners);

		return new CheckBoxWidget(widgetParameters);

	}

	protected String getInputValueForField(CheckBox annotation) {
		return annotation.inputValue();
	}

	protected boolean getCheckedForField(CheckBox annotation) {
		return annotation.checked();
	}

}
