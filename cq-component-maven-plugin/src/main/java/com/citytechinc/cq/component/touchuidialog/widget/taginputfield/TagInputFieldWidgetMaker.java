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
package com.citytechinc.cq.component.touchuidialog.widget.taginputfield;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.annotations.TagNameSpace;
import com.citytechinc.cq.component.annotations.widgets.TagInputField;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.AutoCompleteWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.options.AutoCompleteOptions;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.values.AutoCompleteValues;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource.TagsDataSource;
import com.citytechinc.cq.component.touchuidialog.widget.taginputfield.datasource.TagsDataSourceParameters;

public class TagInputFieldWidgetMaker extends AutoCompleteWidgetMaker {

	private TagInputField fieldAnnotation;

	public TagInputFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException,
		TouchUIDialogGenerationException {
		fieldAnnotation = getAnnotation(TagInputField.class);
		return super.make();
	}

	@Override
	protected DataSource makeDataSource() {
		TagsDataSourceParameters dataSourceParameters = new TagsDataSourceParameters();

		dataSourceParameters.setResourceType(TagsDataSource.RESOURCE_TYPE);

		List<String> namespaces = new ArrayList<String>();

		for (TagNameSpace currentNamespace : fieldAnnotation.namespaces()) {
			namespaces.add(currentNamespace.value());
		}

		dataSourceParameters.setNamespaces(namespaces);

		return new TagsDataSource(dataSourceParameters);
	}

	@Override
	protected AutoCompleteOptions makeOptions() {
		TouchUIDialogElementParameters optionsParameters = new TouchUIDialogElementParameters();

		optionsParameters.setResourceType(TagInputFieldWidget.OPTIONS_RESOURCE_TYPE);
		optionsParameters.setFieldName(TagInputFieldWidget.OPTIONS_FIELD_NAME);
		optionsParameters.setPrimaryType("nt:unstructured");

		return new AutoCompleteOptions(optionsParameters);
	}

	@Override
	protected AutoCompleteValues makeValues() {
		TouchUIDialogElementParameters valuesParameters = new TouchUIDialogElementParameters();

		valuesParameters.setResourceType(TagInputFieldWidget.VALUES_RESOURCE_TYPE);
		valuesParameters.setFieldName(TagInputFieldWidget.VALUES_FIELD_NAME);
		valuesParameters.setPrimaryType("nt:unstructured");

		return new AutoCompleteValues(valuesParameters);
	}

	@Override
	protected boolean getMultipleForField() {
		return fieldAnnotation.multiple();
	}

	@Override
	protected String getModeForField() {
		// TODO: Determine whether this should be dynamic at all
		return "contains";
	}
}
