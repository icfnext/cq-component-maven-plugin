package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.Widget;

public class SimpleWidget implements Widget {

	private final String xtype;
	private final String name;
	private final String fieldLabel;
	private final String fieldName;
	private final String fieldDescription;
	private final String primaryType;
	private final Boolean isRequired;
	private final Map<String, String> additionalProperties;
	private final String defaultValue;

	public SimpleWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription, Boolean isRequired, Map<String, String> additionalProperties,String defaultValue) {
		this.xtype = xtype;
		this.name = name;
		this.fieldName = fieldName;
		this.fieldLabel = fieldLabel;
		this.fieldDescription = fieldDescription;
		this.isRequired = isRequired;
		this.additionalProperties = additionalProperties;
		this.defaultValue=defaultValue;
		this.primaryType = "cq:Widget";
	}

	public String getXType() {
		return xtype;
	}

	public String getName() {
		return name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public Boolean hasFieldDescription() {
		return StringUtils.isNotEmpty(fieldDescription);
	}

	public String toString() {
		StringBuffer retStringBuffer = new StringBuffer(name + " ");

		retStringBuffer.append("xtype : " + xtype);
		retStringBuffer.append(" fieldName : " + fieldName);
		retStringBuffer.append(" fieldDescription : " + fieldDescription);

		return retStringBuffer.toString();
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getLabel() {
		return fieldLabel;
	}

	public Boolean isRequired() {
		return isRequired;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

}
