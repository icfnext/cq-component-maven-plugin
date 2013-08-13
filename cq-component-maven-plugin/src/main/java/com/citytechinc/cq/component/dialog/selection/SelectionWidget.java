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

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = Selection.class, makerClass = SelectionWidgetMaker.class, xtype = SelectionWidget.XTYPE)
public class SelectionWidget extends AbstractWidget {

	public static final String XTYPE = "selection";
	private final String type;
	private final String options;
	private final String optionsProvider;
	private final String sortDir;

	public SelectionWidget(SelectionWidgetParameters parameters) {
		super(parameters);

		this.type = parameters.getType();
		this.options = parameters.getOptions();
		this.optionsProvider = parameters.getOptionsProvider();
		this.sortDir = parameters.getSortDir();
	}

	public String getType() {
		return type;
	}

	public String getOptions() {
		return options;
	}

	public String getOptionsProvider() {
		return optionsProvider;
	}

	public String getSortDir() {
		return sortDir;
	}
}
