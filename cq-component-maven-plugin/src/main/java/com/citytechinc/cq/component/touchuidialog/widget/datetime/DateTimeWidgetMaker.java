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
package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.datefield.DateFieldWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class DateTimeWidgetMaker extends DateFieldWidgetMaker {

	public DateTimeWidgetMaker(TouchUIWidgetMakerParameters parameters) throws ClassNotFoundException {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make() throws ClassNotFoundException {
		DateTimeWidgetParameters widgetParameters = new DateTimeWidgetParameters();

		widgetParameters.setFieldName(getFieldNameForField());
		widgetParameters.setName(getNameForField());
		widgetParameters.setFieldLabel(getFieldLabelForField());
		widgetParameters.setFieldDescription(getFieldDescriptionForField());
		widgetParameters.setRequired(getRequiredForField());
		widgetParameters.setDefaultValue(getDefaultValueForField());
		widgetParameters.setValue(getValueForField());
		widgetParameters.setDisabled(getDisabledForField());
		widgetParameters.setCssClass(getCssClassForField());

		// Date field specific stuff
		DateTime annotation = getAnnotation(DateTime.class);

		widgetParameters.setDisplayedFormat(getDisplayedFormatForField(annotation));
		widgetParameters.setStoredFormat(getStoredFormatForField(annotation));
		widgetParameters.setMinDate(getMinDateForField(annotation));
		widgetParameters.setMaxDate(getMaxDateForField(annotation));

		return new DateTimeWidget(widgetParameters);
	}

	protected String getMinDateForField(DateTime annotation) {
		if (StringUtils.isNotBlank(annotation.minDate())) {
			return annotation.minDate();
		}

		return null;
	}

	protected String getMaxDateForField(DateTime annotation) {
		if (StringUtils.isNotBlank(annotation.maxDate())) {
			return annotation.maxDate();
		}

		return null;
	}

	protected String getDisplayedFormatForField(DateTime annotation) {
		if (StringUtils.isNotBlank(annotation.displayedFormat())) {
			return annotation.displayedFormat();
		}

		return null;
	}

	protected String getStoredFormatForField(DateTime annotation) {
		if (StringUtils.isNotBlank(annotation.storedFormat())) {
			return annotation.storedFormat();
		}

		return null;
	}

}
