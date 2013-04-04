package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.dialog.AbstractWidget;

public class BasicFieldConfig extends AbstractWidget {

	private static final String FIELD_NAME="fieldConfig";
	private static final String PRIMARY_TYPE="nt:unstructured";
	
	public BasicFieldConfig(String xtype,Map<String,String> additionalProperties){
		super(xtype,null,null,false,false,null,null,PRIMARY_TYPE, null, FIELD_NAME,additionalProperties,null);
	}
}
