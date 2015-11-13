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
package com.citytechinc.cq.component.dialog.widget;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DefaultDialogElementParameters;
import com.citytechinc.cq.component.util.Constants;

public class DefaultWidgetParameters extends DefaultDialogElementParameters implements WidgetParameters {
	protected String xtype;
	protected String fieldLabel;
	protected String fieldDescription;
	protected boolean allowBlank;
	protected String defaultValue;
	protected String name;
	protected boolean hideLabel;
	protected boolean disabled;

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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public String getPrimaryType() {
		if (StringUtils.isEmpty(primaryType)) {
			return Constants.CQ_WIDGET;
		}
		return primaryType;
	}
}
