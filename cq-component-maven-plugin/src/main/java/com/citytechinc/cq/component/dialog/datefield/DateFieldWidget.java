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

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.DateField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = DateField.class, makerClass = DateFieldWidgetMaker.class, xtype = DateFieldWidget.XTYPE)
public class DateFieldWidget extends AbstractWidget {
	public static final String XTYPE = "datefield";

	private final int startDay;
	private final boolean showToday;
	private final String format;

	public DateFieldWidget(DateFieldWidgetParameters parameters) {
		super(parameters);
		this.startDay = parameters.getStartDay();
		this.showToday = parameters.isShowToday();
		this.format = parameters.getFormat();
	}

	public int getStartDay() {
		return startDay;
	}

	public boolean isShowToday() {
		return showToday;
	}

	public String getFormat() {
		return format;
	}

}
