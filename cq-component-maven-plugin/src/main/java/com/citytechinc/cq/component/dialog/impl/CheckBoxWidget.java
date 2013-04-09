package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = "com.citytechinc.cq.component.annotations.widgets.CheckBox", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.CheckBoxWidgetMaker", xtypes = CheckBoxWidget.XTYPE)
public class CheckBoxWidget extends AbstractWidget {
	public static final String XTYPE = "checkbox";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private String inputValue;
	private boolean checked;

	public CheckBoxWidget(String inputValue, boolean checked, String fieldLabel, String fieldDescription,
		boolean allowBlank, boolean hideLabel, String defaultValue, String name, String fieldName,
		Map<String, String> additionalProperties) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
		this.inputValue = inputValue;
		this.checked = checked;
	}

	public String getInputValue() {
		return inputValue;
	}

	public boolean isChecked() {
		return checked;
	}
}
