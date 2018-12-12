package com.citytechinc.cq.component.dialog.widget;

import com.citytechinc.cq.component.dialog.DialogElementParameters;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public interface WidgetParameters extends DialogElementParameters, XmlElementParameters {

	public abstract String getXtype();

	public abstract void setXtype(String xtype);

	public abstract String getFieldLabel();

	public abstract void setFieldLabel(String fieldLabel);

	public abstract String getFieldDescription();

	public abstract void setFieldDescription(String fieldDescription);

	public abstract boolean isAllowBlank();

	public abstract void setAllowBlank(boolean allowBlank);

	public abstract String getDefaultValue();

	public abstract void setDefaultValue(String defaultValue);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract boolean isHideLabel();

	public abstract void setHideLabel(boolean hideLabel);

	public abstract boolean isDisabled();

	public abstract void setDisabled(boolean disabled);

	public abstract String getPrimaryType();

}