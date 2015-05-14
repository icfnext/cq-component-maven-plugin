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
package com.citytechinc.cq.component.touchuidialog.widget.selection;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.maven.util.LogSingleton;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;
import com.citytechinc.cq.component.touchuidialog.widget.radiogroup.RadioGroupWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.OptionParameters;

public class SelectionFieldWidgetMaker extends AbstractTouchUIWidgetMaker {

	public SelectionFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make() throws ClassNotFoundException, InvalidComponentFieldException,
		TouchUIDialogGenerationException {

		Selection selectionField = getAnnotation(Selection.class);

		if (Selection.RADIO.equals(selectionField.type())) {
			return makeRadioGroup();
		} else {
			if (StringUtils.isNotBlank(selectionField.type()) && !Selection.SELECT.equals(selectionField.type())) {
				LogSingleton
					.getInstance()
					.warn(
						"Selection field type "
							+ selectionField.type()
							+ " requested for field "
							+ getFieldNameForField()
							+ " however no such presentation is implemented for the Touch UI.  Defaulting to a dropdown selection.");
			}
			return makeSelection(selectionField);
		}

	}

	protected TouchUIDialogElement makeRadioGroup() throws TouchUIDialogGenerationException,
		InvalidComponentFieldException, ClassNotFoundException {
		RadioGroupWidgetMaker maker = new RadioGroupWidgetMaker(parameters);
		return maker.make();
	}

	protected TouchUIDialogElement makeSelection(Selection selectionField) {
		SelectionFieldWidgetParameters widgetParameters = new SelectionFieldWidgetParameters();

		widgetParameters.setFieldName(getFieldNameForField());
		widgetParameters.setName(getNameForField());
		widgetParameters.setFieldLabel(getFieldLabelForField());
		widgetParameters.setFieldDescription(getFieldDescriptionForField());
		widgetParameters.setRequired(getRequiredForField());
		widgetParameters.setDefaultValue(getDefaultValueForField());
		widgetParameters.setResourceType(SelectionFieldWidget.RESOURCE_TYPE);
		widgetParameters.setValue(getValueForField());
		widgetParameters.setDisabled(getDisabledForField());
		widgetParameters.setCssClass(getCssClassForField());

		widgetParameters.setMultiple(getMultipleForField(selectionField));
		widgetParameters.setDataSource(getDataSourceForField(selectionField));

		for (int i = 0; i < selectionField.options().length; i++) {
			Option currentOption = selectionField.options()[i];

			OptionParameters optionParameters = new OptionParameters();
			optionParameters.setText(currentOption.text());
			optionParameters.setValue(currentOption.value());
			optionParameters.setSelected(currentOption.selected());
			optionParameters.setFieldName("option" + i);

			widgetParameters.addOption(new com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option(
				optionParameters));
		}

		return new SelectionFieldWidget(widgetParameters);
	}

	public boolean getMultipleForField(Selection annotation) {
		if (annotation != null) {
			return annotation.multiple();
		}

		return false;
	}

	public DataSource getDataSourceForField(Selection annotation) {
		if (annotation != null && StringUtils.isNotBlank(annotation.dataSource())) {
			DataSourceParameters dataSourceParameters = new DataSourceParameters();
			dataSourceParameters.setResourceType(annotation.dataSource());
			return new DataSource(dataSourceParameters);
		}

		return null;
	}

}
