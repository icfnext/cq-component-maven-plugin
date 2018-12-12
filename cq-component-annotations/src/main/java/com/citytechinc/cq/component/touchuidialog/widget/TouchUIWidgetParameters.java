package com.citytechinc.cq.component.touchuidialog.widget;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElementParameters;

public interface TouchUIWidgetParameters extends TouchUIDialogElementParameters {

	public String getFieldLabel();

	public void setFieldLabel(String fieldLabel);

	public String getName();

	public void setName(String name);

	public String getTitle();

	public void setTitle(String title);

	public String getPrimaryType();

	public String getFieldDescription();

	public void setFieldDescription(String fieldDescription);

	public boolean isRequired();

	public void setRequired(boolean required);

	public String getValue();

	public void setValue(String value);

	public String getDefaultValue();

	public void setDefaultValue(String defaultValue);

	public boolean isDisabled();

	public void setDisabled(boolean disabled);

	public String getCssClass();

	public void setCssClass(String cssClass);

	public boolean isRenderReadOnly();

	public void setRenderReadOnly(boolean renderReadOnly);

	public boolean isShowOnCreate();

	public void setShowOnCreate(boolean showOnCreate);

    public boolean isHideOnEdit();

    public void setHideOnEdit(boolean hideOnEdit);
}