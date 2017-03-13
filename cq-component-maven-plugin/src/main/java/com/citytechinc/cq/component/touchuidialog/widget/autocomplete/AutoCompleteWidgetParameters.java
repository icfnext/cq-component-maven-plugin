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
package com.citytechinc.cq.component.touchuidialog.widget.autocomplete;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.touchuidialog.TouchUIDialogElement;
import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class AutoCompleteWidgetParameters extends DefaultTouchUIWidgetParameters {

	private boolean multiple;
	private String mode;
	private TouchUIDialogElement datasource;
	private TouchUIDialogElement values;
	private TouchUIDialogElement options;

	// TODO: Look into the translateOptions mechanism

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public TouchUIDialogElement getDatasource() {
		return datasource;
	}

	public void setDatasource(TouchUIDialogElement datasource) {
		this.datasource = datasource;
	}

	public TouchUIDialogElement getValues() {
		return values;
	}

	public void setValues(TouchUIDialogElement values) {
		this.values = values;
	}

	public TouchUIDialogElement getOptions() {
		return options;
	}

	public void setOptions(TouchUIDialogElement options) {
		this.options = options;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {
		List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

		if (getDatasource() != null) {
			allContainedElements.add(getDatasource());
		}
		if (getOptions() != null) {
			allContainedElements.add(getOptions());
		}
		if (getValues() != null) {
			allContainedElements.add(getValues());
		}

		if (containedElements != null) {
			allContainedElements.addAll(containedElements);
		}

		return allContainedElements;
	}

	@Override
	public String getResourceType() {
		return AutoCompleteWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for AutoCompleteWidget");
	}

}
