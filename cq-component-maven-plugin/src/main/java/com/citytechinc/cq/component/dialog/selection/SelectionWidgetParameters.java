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
package com.citytechinc.cq.component.dialog.selection;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class SelectionWidgetParameters extends DefaultWidgetParameters {
	private String type;
	private String options;
	private String optionsProvider;
	private String sortDir;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getOptionsProvider() {
		return optionsProvider;
	}

	public void setOptionsProvider(String optionsProvider) {
		this.optionsProvider = optionsProvider;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for SelectionWidget");
	}

	@Override
	public String getXtype() {
		return SelectionWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for SelectionWidget");
	}
}
