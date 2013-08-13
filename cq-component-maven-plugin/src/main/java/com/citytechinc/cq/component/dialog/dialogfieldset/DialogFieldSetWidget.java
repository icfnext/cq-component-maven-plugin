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
package com.citytechinc.cq.component.dialog.dialogfieldset;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = DialogFieldSet.class, makerClass = DialogFieldSetWidgetMaker.class, xtype = DialogFieldSetWidget.XTYPE)
public class DialogFieldSetWidget extends AbstractWidget {
	public static final String XTYPE = "dialogfieldset";

	private final boolean collapseFirst;
	private final boolean collapsible;
	private final boolean collapsed;
	private final boolean border;
	private final String title;

	public DialogFieldSetWidget(DialogFieldSetWidgetParameters parameters) {
		super(parameters);
		this.collapseFirst = parameters.isCollapseFirst();
		this.collapsible = parameters.isCollapsible();
		this.collapsed = parameters.isCollapsed();
		this.border = parameters.isBorder();
		this.title = parameters.getTitle();
	}

	public boolean isCollapseFirst() {
		return collapseFirst;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public boolean isBorder() {
		return border;
	}

	public String getTitle() {
		return title;
	}
}
