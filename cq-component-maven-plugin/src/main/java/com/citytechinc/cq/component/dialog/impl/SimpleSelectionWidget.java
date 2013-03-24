package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.SelectionWidget;
import com.citytechinc.cq.component.dialog.impl.WidgetCollection;

public class SimpleSelectionWidget implements SelectionWidget {

	private final String xtype;
	private final String primaryType;
	private final String type;
	private final String name;
	private final String fieldLabel;
	private final String fieldName;
	private final String fieldDescription;
	private final Boolean required;
	private final Map<String, String> additionalProperties;
	private final List<DialogElement> containtedElements=new ArrayList<DialogElement>();
	private final String defaultValue;

	public SimpleSelectionWidget(
			String type,
			String name,
			String fieldLabel,
			String fieldName,
			String fieldDescription,
			Boolean required,
			String defaultValue,
			Map<String, String> additionalProperties,
			List<DialogElement> options) {

		this.xtype = "selection";
		this.primaryType = "cq:Widget";

		this.type = type;
		this.name = name;
		this.fieldLabel = fieldLabel;
		this.fieldName = fieldName;
		this.fieldDescription = fieldDescription;
		this.required = required;
		this.defaultValue=defaultValue;
		this.additionalProperties = additionalProperties;

		containtedElements.add(new WidgetCollection(options,"options"));

	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getXtype() {
		return xtype;
	}

	public String getType() {
		return type;
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

	public Boolean isAllowBlank(){
		return !required;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public List<? extends DialogElement> getContainedElements() {
		return containtedElements;
	}

	public String getNameSpace() {
		return null;
	}
}
