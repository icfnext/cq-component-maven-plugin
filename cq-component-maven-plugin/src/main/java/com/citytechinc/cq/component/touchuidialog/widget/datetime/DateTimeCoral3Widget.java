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
package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.touchuidialog.widget.datefield.DateFieldCoral3Widget;

@TouchUIWidget(annotationClass = DateTime.class, makerClass = DateTimeWidgetMaker.class,
	resourceType = DateTimeCoral3Widget.RESOURCE_TYPE)
public class DateTimeCoral3Widget extends DateFieldCoral3Widget {

	public static final String RESOURCE_TYPE = DateFieldCoral3Widget.RESOURCE_TYPE;

	public DateTimeCoral3Widget(DateTimeWidgetParameters parameters) {
		super(parameters);
	}

	public String getType() {
		return "datetime";
	}
}