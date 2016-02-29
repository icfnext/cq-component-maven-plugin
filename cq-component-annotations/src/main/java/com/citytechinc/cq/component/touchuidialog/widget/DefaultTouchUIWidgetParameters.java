/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
