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
package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.exceptions.TouchUIDialogGenerationException;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.options.AutoCompleteOptions;
import com.citytechinc.cq.component.touchuidialog.widget.autocomplete.values.AutoCompleteValues;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public abstract class AutoCompleteWidgetMaker extends AbstractTouchUIWidgetMaker<AutoCompleteWidgetParameters> {

	public AutoCompleteWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(AutoCompleteWidgetParameters widgetParameters) throws ClassNotFoundException,
		InvalidComponentFieldException, TouchUIDialogGenerationException {

		// Autocomplete specific parameters
		widgetParameters.setMultiple(getMultipleForField());
		widgetParameters.setMode(getModeForField());
		widgetParameters.setDatasource(makeDataSource());
		widgetParameters.setOptions(makeOptions());
		widgetParameters.setValues(makeValues());

		return new AutoCompleteWidget(widgetParameters);
	}

	protected abstract DataSource makeDataSource();

	protected abstract AutoCompleteOptions makeOptions();

	protected abstract AutoCompleteValues makeValues();

	protected abstract boolean getMultipleForField();

	protected abstract String getModeForField();

}
