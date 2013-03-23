package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.MultiValueWidget;
import com.citytechinc.cq.component.dialog.Widget;

public class SimpleMultiValueWidget implements MultiValueWidget {

	private final String xtype;
	private final String name;
	private final String fieldLabel;
	private final String fieldName;
	private final String fieldDescription;
	private final Boolean isRequired;
	private final Map<String, String> additionalProperties;
	private final String primaryType;
	private final List<Widget> containedElements;
	private final String defaultValue;
	
	public SimpleMultiValueWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription, Boolean isRequired, String defaultValue,Map<String, String> additionalProperties, List<Widget> fieldConfigurations) {
		this.xtype = xtype;
		this.name = name;
		this.fieldName = fieldName;
		this.fieldLabel = fieldLabel;
		this.fieldDescription = fieldDescription;
		this.isRequired = isRequired;
		this.defaultValue=defaultValue;
		this.additionalProperties = additionalProperties;
		this.containedElements = fieldConfigurations;
		
		this.primaryType = "cq:Widget";
	}

	public String getXtype() {
		return xtype;
	}

	public String getName() {
		return name;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public Boolean isRequired() {
		return isRequired;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public Boolean isSingleFieldConfiguration() {
		return containedElements.size() == 1;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public String getNameSpace() {
		return null;
	}

	public List<? extends DialogElement> getContainedElements() {
		return containedElements;
	}
}
