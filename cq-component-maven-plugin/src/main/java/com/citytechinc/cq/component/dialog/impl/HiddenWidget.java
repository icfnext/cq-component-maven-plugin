package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Hidden;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.maker.impl.HiddenWidgetMaker;

@Widget(annotationClass = Hidden.class, makerClass = HiddenWidgetMaker.class, xtype = HiddenWidget.XTYPE)
public class HiddenWidget extends AbstractWidget {
	public static final String XTYPE = "hidden";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private String value;

	public HiddenWidget(String value, String fieldLabel, String fieldDescription, boolean allowBlank,
		boolean hideLabel, String defaultValue, String name, String fieldName, Map<String, String> additionalProperties) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
