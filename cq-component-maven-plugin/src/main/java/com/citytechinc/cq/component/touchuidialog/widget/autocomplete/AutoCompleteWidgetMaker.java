/**
 *    Copyright 2017 ICF Olson
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
package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Property;
import com.citytechinc.cq.component.annotations.widgets.AutoComplete;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.options.AutoCompleteOptions;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.values.AutoCompleteValues;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class AutoCompleteWidgetMaker extends AbstractTouchUIWidgetMaker<AutoCompleteWidgetParameters> {

	private AutoComplete fieldAnnotation;

	public AutoCompleteWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(AutoCompleteWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		fieldAnnotation = getAnnotation(AutoComplete.class);

		// Autocomplete specific parameters
		widgetParameters.setMultiple(getMultipleForField());
		widgetParameters.setMode(getModeForField());
		widgetParameters.setDatasource(makeDataSource());
		widgetParameters.setOptions(makeOptions());
		widgetParameters.setValues(makeValues());

		return new AutoCompleteWidget(widgetParameters);
	}

	protected DataSource makeDataSource() {
		if (fieldAnnotation != null) {
			DataSourceParameters parameters = new DataSourceParameters();
			Map<String, String> additionalProperties = new HashMap<String, String>();
			for (Property property : fieldAnnotation.datasourceProperties()) {
				additionalProperties.put(property.name(), property.value());
			}
			parameters.setAdditionalProperties(additionalProperties);
			parameters.setResourceType(fieldAnnotation.datasourceResourceType());
			return new DataSource(parameters);
		}
		return null;
	}

	protected AutoCompleteOptions makeOptions() {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.optionsResourceType())) {
			TouchUIDialogElementParameters optionsParameters = new DefaultTouchUIDialogElementParameters();

			optionsParameters.setResourceType(fieldAnnotation.optionsResourceType());
			optionsParameters.setFieldName(AutoCompleteWidget.OPTIONS_FIELD_NAME);
			optionsParameters.setPrimaryType("nt:unstructured");

			return new AutoCompleteOptions(optionsParameters);
		}
		return null;
	}

	protected AutoCompleteValues makeValues() {
		if (fieldAnnotation != null && StringUtils.isNotEmpty(fieldAnnotation.valuesResourceType())) {
			TouchUIDialogElementParameters valuesParameters = new DefaultTouchUIDialogElementParameters();

			valuesParameters.setResourceType(fieldAnnotation.valuesResourceType());
			valuesParameters.setFieldName(AutoCompleteWidget.VALUES_FIELD_NAME);
			valuesParameters.setPrimaryType("nt:unstructured");

			return new AutoCompleteValues(valuesParameters);
		}
		return null;
	}

	protected boolean getMultipleForField() {
		if (fieldAnnotation != null) {
			return fieldAnnotation.multiple();
		}
		return false;
	}

	protected String getModeForField() {
		if (fieldAnnotation != null) {
			return fieldAnnotation.mode();
		}
		return null;
	}
}
