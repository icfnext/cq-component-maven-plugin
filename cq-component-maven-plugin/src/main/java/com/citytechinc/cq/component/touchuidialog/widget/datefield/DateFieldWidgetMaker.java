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
package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.maker.AbstractTouchUIWidgetMaker;
import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMakerParameters;

public class DateFieldWidgetMaker extends AbstractTouchUIWidgetMaker<DateFieldWidgetParameters> {

	public DateFieldWidgetMaker(TouchUIWidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public TouchUIDialogElement make(DateFieldWidgetParameters widgetParameters) throws ClassNotFoundException {
		// Date field specific stuff
		DateField annotation = getAnnotation(DateField.class);

		widgetParameters.setDisplayedFormat(getDisplayedFormatForField(annotation));
		widgetParameters.setStoredFormat(getStoredFormatForField(annotation));
		widgetParameters.setMinDate(getMinDateForField(annotation));
		widgetParameters.setMaxDate(getMaxDateForField(annotation));

		return new DateFieldWidget(widgetParameters);
	}

	protected String getMinDateForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.minDate())) {
			return annotation.minDate();
		}

		return null;
	}

	protected String getMaxDateForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.maxDate())) {
			return annotation.maxDate();
		}

		return null;
	}

	protected String getDisplayedFormatForField(DateField annotation) {
		if (StringUtils.isNotBlank(annotation.displayedFormat())) {
			return annotation.displayedFormat();
		}

		return null;
	}

	protected String getStoredFormatForField(DateField annotation) {
		return annotation.storedFormat();
	}

}
