package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = "com.citytechinc.cq.component.annotations.widgets.DateField", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.DateFieldWidgetMaker", xtypes = DateFieldWidget.XTYPE)
public class DateFieldWidget extends AbstractWidget {
	public static final String XTYPE = "datefield";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private int startDay;
	private boolean showToday;
	private String format;

	public DateFieldWidget(int startDay, boolean showToday, String format, String fieldLabel, String fieldDescription,
		boolean allowBlank, boolean hideLabel, String defaultValue, String name, String fieldName,
		Map<String, String> additionalProperties) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
		this.startDay = startDay;
		this.showToday = showToday;
		this.format = format;
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
