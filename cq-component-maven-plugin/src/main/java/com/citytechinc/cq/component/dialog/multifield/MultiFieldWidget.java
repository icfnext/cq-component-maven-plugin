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
package com.citytechinc.cq.component.dialog.multifield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.MultiField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = MultiField.class, makerClass = MultifieldWidgetMaker.class, xtype = MultiFieldWidget.XTYPE, ranking = MultiFieldWidget.RANKING)
public class MultiFieldWidget extends AbstractWidget {
	public static final int RANKING = 1000000;
	public static final String XTYPE = "multifield";

	private final boolean orderable;
	private final String addItemLabel;

	public MultiFieldWidget(MultiFieldWidgetParameters parameters) {
		super(parameters);
		this.orderable = parameters.isOrderable();
		this.addItemLabel = parameters.getAddItemLabel();
	}

	public boolean isOrderable() {
		return orderable;
	}

	public String getAddItemLabel() {
		return addItemLabel;
	}
}
