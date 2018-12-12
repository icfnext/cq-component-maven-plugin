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