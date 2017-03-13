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
package com.citytechinc.cq.component.dialog.datefield;

import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class DateFieldWidgetMaker extends AbstractWidgetMaker<DateFieldWidgetParameters> {

	public DateFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	@Override
	public DialogElement make(DateFieldWidgetParameters widgetParameters) throws ClassNotFoundException {

		DateField dateFieldAnnotation = getAnnotation(DateField.class);

		int startDay = getStartDayForField(dateFieldAnnotation);
		boolean showToday = getShowTodayForField(dateFieldAnnotation);
		String format = getFormatForField(dateFieldAnnotation);

		widgetParameters.setStartDay(startDay);
		widgetParameters.setShowToday(showToday);
		widgetParameters.setFormat(format);

		return new DateFieldWidget(widgetParameters);
	}

	protected int getStartDayForField(DateField annotation) {
		return annotation.startDay();
	}

	protected boolean getShowTodayForField(DateField annotation) {
		return annotation.showToday();
	}

	protected String getFormatForField(DateField annotation) {
		return annotation.format();
	}

}
