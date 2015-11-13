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

import java.util.HashMap;
import java.util.Map;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;
import com.citytechinc.cq.component.util.Constants;
import com.citytechinc.cq.component.xml.NameSpacedAttribute;

public class AbstractTouchUIWidget extends AbstractTouchUIDialogElement {

	private String name;
	private final String title;
	private final String fieldLabel;
	private final String fieldDescription;
	private final boolean required;
	private final String value;
	private final String defaultValue;
	private final boolean disabled;
	private final String cssClass;
	private final boolean renderReadOnly;
	private final NameSpacedAttribute<Boolean> showOnCreate;

	public AbstractTouchUIWidget(DefaultTouchUIWidgetParameters parameters) {
		super(parameters);

		this.name = parameters.getName();
		this.title = parameters.getTitle();
		this.fieldLabel = parameters.getFieldLabel();
		this.fieldDescription = parameters.getFieldDescription();
		this.required = parameters.isRequired();
		this.value = parameters.getValue();
		this.defaultValue = parameters.getDefaultValue();
		this.disabled = parameters.isDisabled();
		this.cssClass = parameters.getCssClass();
		this.renderReadOnly = parameters.isRenderReadOnly();
		this.showOnCreate =
			new NameSpacedAttribute<Boolean>(Constants.CQ_NS_URI, Constants.CQ_NS_PREFIX, parameters.isShowOnCreate());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldLabel() {
		return fieldLabel;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public boolean isRequired() {
		return required;
	}

	public String getValue() {
		return value;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public String getTitle() {
		return title;
	}

	public Map<String, String> getCssClass() {

		Map<String, String> retMap = new HashMap<String, String>();

		if (StringUtils.isNotBlank(cssClass)) {
			retMap.put("class", cssClass);
		}

		return retMap;

	}

	public boolean isRenderReadOnly() {
		return renderReadOnly;
	}

	public NameSpacedAttribute<Boolean> getShowOnCreate() {
		return showOnCreate;
	}
}
