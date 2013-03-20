package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

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
	private final List<Widget> fieldConfigurations;

	public SimpleMultiValueWidget(String xtype, String name, String fieldName, String fieldLabel, String fieldDescription, Boolean isRequired, Map<String, String> additionalProperties, List<Widget> fieldConfigurations) {
		this.xtype = xtype;
		this.name = name;
		this.fieldName = fieldName;
		this.fieldLabel = fieldLabel;
		this.fieldDescription = fieldDescription;
		this.isRequired = isRequired;
		this.additionalProperties = additionalProperties;
		this.fieldConfigurations = fieldConfigurations;

		this.primaryType = "cq:Widget";
	}

	public String getXType() {
		return xtype;
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return fieldLabel;
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

	public Boolean isRequired() {
		return isRequired;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public List<Widget> getFieldConfigurations() {
		return fieldConfigurations;
	}

	public Boolean isSingleFieldConfiguration() {
		return fieldConfigurations.size() == 1;
	}

}
