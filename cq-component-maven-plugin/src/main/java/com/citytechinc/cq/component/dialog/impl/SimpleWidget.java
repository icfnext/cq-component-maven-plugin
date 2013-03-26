package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.AbstractWidget;

public class SimpleWidget extends AbstractWidget {
	private static final String PRIMARY_TYPE="cq:Widget";

	public SimpleWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription, Boolean required, Map<String, String> additionalProperties,String defaultValue) {
		super(xtype,fieldLabel,fieldDescription,!required,defaultValue,name,PRIMARY_TYPE, null, fieldName,additionalProperties,null);
	}
}
