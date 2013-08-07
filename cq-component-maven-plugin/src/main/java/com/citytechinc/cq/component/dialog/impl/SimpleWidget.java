package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.maker.impl.SimpleWidgetMaker;

@Widget(makerClass = SimpleWidgetMaker.class, xtype = WidgetFactory.TEXTFIELD_XTYPE)
public class SimpleWidget extends AbstractWidget {
	private static final String PRIMARY_TYPE = "cq:Widget";

	public SimpleWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription,
		Boolean required, boolean hideLabel, Map<String, String> additionalProperties, String defaultValue) {
		super(xtype, fieldLabel, fieldDescription, !required, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
	}
}