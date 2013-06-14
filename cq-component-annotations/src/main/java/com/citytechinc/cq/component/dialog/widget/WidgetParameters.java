package com.citytechinc.cq.component.dialog.widget;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class WidgetParameters extends DialogElementParameters {
	protected String xtype;
	protected String fieldLabel;
	protected String fieldDescription;
	protected boolean allowBlank;
	protected String defaultValue;
	protected String name;
	protected boolean hideLabel;

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public boolean isAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHideLabel() {
		return hideLabel;
	}

	public void setHideLabel(boolean hideLabel) {
		this.hideLabel = hideLabel;
	}

	@Override
	public String getPrimaryType() {
		if (StringUtils.isEmpty(primaryType)) {
			return Constants.CQ_WIDGET;
		}
		return primaryType;
	}
}
