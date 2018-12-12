package com.citytechinc.cq.component.touchuidialog.widget.datetime;

import com.citytechinc.cq.component.annotations.config.TouchUIWidget;
import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.touchuidialog.widget.datefield.DateFieldWidget;

@TouchUIWidget(annotationClass = DateTime.class, makerClass = DateTimeWidgetMaker.class,
	resourceType = DateTimeWidget.RESOURCE_TYPE)
public class DateTimeWidget extends DateFieldWidget {

	public static final String RESOURCE_TYPE = DateFieldWidget.RESOURCE_TYPE;

	public DateTimeWidget(DateTimeWidgetParameters parameters) {
		super(parameters);
	}

	public String getType() {
		return "datetime";
	}

}
