package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.Option;
import com.citytechinc.cq.component.dialog.SelectionWidget;

public class SimpleSelectionWidget implements SelectionWidget {

	private final String xtype;
	private final String primaryType;
	private final String selectionType;
	private final String name;
	private final String fieldLabel;
	private final String fieldName;
	private final String fieldDescription;
	private final Boolean isRequired;
	private final Map<String, String> additionalProperties;
	private final List<Option> options;
	private final String defaultValue;

	public SimpleSelectionWidget(
			String type,
			String name,
			String fieldLabel,
			String fieldName,
			String fieldDescription,
			Boolean isRequired,
			String defaultValue,
			Map<String, String> additionalProperties,
			List<Option> options) {

		this.xtype = "selection";
		this.primaryType = "cq:Widget";

		this.selectionType = type;
		this.name = name;
		this.fieldLabel = fieldLabel;
		this.fieldName = fieldName;
		this.fieldDescription = fieldDescription;
		this.isRequired = isRequired;
		this.defaultValue=defaultValue;
		this.additionalProperties = additionalProperties;

		this.options = options;

	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getXType() {
		return xtype;
	}

	public String getSelectionType() {
		return selectionType;
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

	public List<Option> getOptions() {
		return options;
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
