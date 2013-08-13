package com.citytechinc.cq.component.dialog.datetime;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.DateTime;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = DateTime.class, makerClass = DateTimeWidgetMaker.class, xtype = DateTimeWidget.XTYPE)
public class DateTimeWidget extends AbstractWidget {
	public static final String XTYPE = "datetime";

	public DateTimeWidget(DateTimeWidgetParameters parameters) {
		super(parameters);
	}
}
