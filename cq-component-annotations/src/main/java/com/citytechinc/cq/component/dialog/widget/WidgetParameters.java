/**
 *    Copyright 2017 ICF Olson
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