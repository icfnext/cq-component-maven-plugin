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
package com.citytechinc.cq.component.touchuidialog.widget.radiogroup;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.util.TouchUIDialogUtil;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSourceParameters;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class RadioGroupWidgetMaker extends AbstractTouchUIWidgetMaker<RadioGroupWidgetParameters> {

	public RadioGroupWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(RadioGroupWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {
		Selection selectionField = getAnnotation(Selection.class);

		widgetParameters.setText(getFieldLabelForField());
		widgetParameters.setDataSource(getDataSourceForField(selectionField));

		widgetParameters.setOptions(TouchUIDialogUtil.getOptionsForSelection(selectionField, getType(),
			parameters.getClassLoader(), parameters.getClassPool()));

		return new RadioGroupWidget(widgetParameters);
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
