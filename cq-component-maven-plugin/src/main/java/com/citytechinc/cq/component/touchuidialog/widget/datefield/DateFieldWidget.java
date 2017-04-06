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
package com.citytechinc.cq.component.touchuidialog.widget.datefield;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.touchuidialog.widget.AbstractTouchUIWidget;

@TouchUIWidget(annotationClass = DateField.class, makerClass = DateFieldWidgetMaker.class,
	resourceType = DateFieldWidget.RESOURCE_TYPE)
public class DateFieldWidget extends AbstractTouchUIWidget {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/form/datepicker";

	private final String minDate;
	private final String maxDate;
	private final String storedFormat; // default "YYYY-MM-DD[T]HH:mm:ss.000Z"
	private final String displayedFormat;

	public DateFieldWidget(DateFieldWidgetParameters parameters) {
		super(parameters);

		minDate = parameters.getMinDate();
		maxDate = parameters.getMaxDate();
		storedFormat = parameters.getStoredFormat();
		displayedFormat = parameters.getDisplayedFormat();
	}

	public String getMinDate() {
		return minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public String getStoredFormat() {
		return storedFormat;
	}

	public String getDisplayedFormat() {
		return displayedFormat;
	}

	public String getType() {
		return "datetime";
	}
}
