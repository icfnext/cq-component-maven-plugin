package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = "com.citytechinc.cq.component.annotations.widgets.DateTime", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.DateTimeWidgetMaker", xtypes = DateTimeWidget.XTYPE)
public class DateTimeWidget extends AbstractWidget {
	public static final String XTYPE = "datetime";
	private static final String PRIMARY_TYPE = "cq:Widget";

	public DateTimeWidget(String fieldLabel, String fieldDescription, boolean allowBlank, boolean hideLabel,
		String defaultValue, String name, String fieldName, Map<String, String> additionalProperties) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
	}
}
