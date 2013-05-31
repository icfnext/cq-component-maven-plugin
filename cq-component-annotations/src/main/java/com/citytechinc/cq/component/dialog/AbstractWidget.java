package com.citytechinc.cq.component.dialog;

import java.util.List;
import java.util.Map;

public abstract class AbstractWidget extends AbstractDialogElement {
	private final String xtype;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean allowBlank;
	private final String defaultValue;
	private final String name;
	private final boolean hideLabel;

	public AbstractWidget(String xtype, String fieldLabel, String fieldDescription, boolean allowBlank,
		boolean hideLabel, String defaultValue, String name, String primaryType, String nameSpace, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		super(primaryType, nameSpace, fieldName, additionalProperties, containedElements);
		this.xtype = xtype;
		this.fieldLabel = fieldLabel;
		this.fieldDescription = fieldDescription;
		this.allowBlank = allowBlank;
		this.defaultValue = defaultValue;
		this.name = name;
		this.hideLabel = hideLabel;
	}

	public final String getXtype() {
		return xtype;
	}

	public final String getName() {
		return name;
	}

	public final String getFieldLabel() {
		return fieldLabel;
	}

	public final String getFieldDescription() {
		return fieldDescription;
	}

	public final boolean isAllowBlank() {
		return allowBlank;
	}

	public final boolean isHideLabel() {
		return hideLabel;
	}

	public final String getDefaultValue() {
		return defaultValue;
	}

}
