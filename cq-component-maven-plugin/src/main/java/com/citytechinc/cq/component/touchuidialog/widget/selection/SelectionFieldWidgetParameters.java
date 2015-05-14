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
package com.citytechinc.cq.component.touchuidialog.widget.selection;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.touchuidialog.widget.TouchUIWidgetParameters;
import com.citytechinc.cq.component.touchuidialog.widget.datasource.DataSource;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.Option;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.Options;
import com.citytechinc.cq.component.touchuidialog.widget.selection.options.OptionsParameters;
import com.citytechinc.cq.component.xml.XmlElement;

public class SelectionFieldWidgetParameters extends TouchUIWidgetParameters {

	private boolean multiple;

	private List<Option> options;
	private DataSource dataSource;

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public Options getOptions() {
		if (options != null) {
			OptionsParameters optionsParameters = new OptionsParameters();
			optionsParameters.setOptions(options);
			return new Options(optionsParameters);
		}

		return null;
	}

	public void addOption(Option option) {
		if (options == null) {
			options = new ArrayList<Option>();
		}

		options.add(option);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<? extends XmlElement> getContainedElements() {
		List<XmlElement> allContainedElements = new ArrayList<XmlElement>();

		if (getOptions() != null) {
			allContainedElements.add(getOptions());
		}

		if (getDataSource() != null) {
			allContainedElements.add(getDataSource());
		}

		if (containedElements != null) {
			allContainedElements.addAll(containedElements);
		}

		return allContainedElements;
	}

	@Override
	public String getResourceType() {
		return SelectionFieldWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for SelectionFieldWidget");
	}

}
