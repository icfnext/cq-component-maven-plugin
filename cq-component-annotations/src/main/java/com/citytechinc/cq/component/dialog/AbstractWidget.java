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
package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;

/**
 * Abstract class representing a Widget in the context of a Dialog. Concrete
 * Widgets will extend this class and add any properties required by the
 * extending Widget.
 */
public abstract class AbstractWidget extends AbstractDialogElement {
	private final String xtype;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean allowBlank;
	private final String defaultValue;
	private String name;
	private final boolean hideLabel;
	private boolean disabled;

	/**
	 * The constructor for the abstract Widget sets a number of properties
	 * common to all widget types.
	 * 
	 * @param parameters
	 */
	public AbstractWidget(DefaultWidgetParameters parameters) {
		super(parameters);
		this.xtype = parameters.getXtype();
		this.fieldLabel = parameters.getFieldLabel();
		this.fieldDescription = parameters.getFieldDescription();
		this.allowBlank = parameters.isAllowBlank();
		this.defaultValue = parameters.getDefaultValue();
		this.name = parameters.getName();
		this.hideLabel = parameters.isHideLabel();
		this.disabled = parameters.isDisabled();
	}

	/**
	 * 
	 * @return xtype of the Widget
	 */
	public String getXtype() {
		return xtype;
	}

	/**
	 * 
	 * @return name of the Widget
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return field label to be applied to the rendered Widget
	 */
	public String getFieldLabel() {
		return fieldLabel;
	}

	/**
	 * 
	 * @return field description to be applied to the rendered Widget
	 */
	public String getFieldDescription() {
		return fieldDescription;
	}

	/**
	 * 
	 * @return Indication of whether the Widget may be left blank in an
	 *         authoring Dialog
	 */
	public boolean isAllowBlank() {
		return allowBlank;
	}

	/**
	 * 
	 * @return Indication of whether the Widget is disabled by default
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * 
	 * @return Indication of whether the field label should be hidden for the
	 *         Widget in the authoring Dialog
	 */
	public boolean isHideLabel() {
		return hideLabel;
	}

	/**
	 * 
	 * @return Default value of the Widget input
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
