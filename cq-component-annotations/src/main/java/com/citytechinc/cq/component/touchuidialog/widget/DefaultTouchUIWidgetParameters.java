package com.citytechinc.cq.component.touchuidialog.widget;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class DefaultTouchUIWidgetParameters extends DefaultTouchUIDialogElementParameters implements
	TouchUIWidgetParameters {

	protected String name;
	protected String title;
	protected String fieldLabel;
	protected String fieldDescription;
	protected boolean required;
	protected String value;
	protected String defaultValue;
	protected boolean disabled;
	protected String cssClass;
	protected boolean renderReadOnly;
	protected boolean showOnCreate;
    protected boolean hideOnEdit;

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getPrimaryType() {
		if (StringUtils.isBlank(super.getPrimaryType())) {
			return Constants.NT_UNSTRUCTURED;
		}

		return super.getPrimaryType();
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public boolean isRenderReadOnly() {
		return renderReadOnly;
	}

	public void setRenderReadOnly(boolean renderReadOnly) {
		this.renderReadOnly = renderReadOnly;
	}

	public boolean isShowOnCreate() {
		return showOnCreate;
	}

	public void setShowOnCreate(boolean showOnCreate) {
		this.showOnCreate = showOnCreate;
	}

    public boolean isHideOnEdit() {
        return hideOnEdit;
    }

    public void setHideOnEdit(boolean hideOnEdit) {
        this.hideOnEdit = hideOnEdit;
    }

}
