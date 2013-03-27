package com.citytechinc.cq.component.dialog.impl;

import java.util.Arrays;
import java.util.Map;

import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

public class MultiValueWidget extends AbstractWidget {
	private static final String PRIMARY_TYPE = "cq:Widget";
	
	public MultiValueWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription, boolean required, String defaultValue,Map<String, String> additionalProperties, BasicFieldConfig fieldConfig) {
		super(xtype,fieldLabel,fieldDescription,!required,defaultValue,name,PRIMARY_TYPE, null, fieldName,additionalProperties,Arrays.asList(new DialogElement[]{fieldConfig}));
	}
}
