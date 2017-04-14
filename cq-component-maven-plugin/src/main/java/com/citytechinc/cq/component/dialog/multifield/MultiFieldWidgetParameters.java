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
package com.citytechinc.cq.component.dialog.multifield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class MultiFieldWidgetParameters extends DefaultWidgetParameters {
	private boolean orderable;
	private String addItemLabel;

	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public String getAddItemLabel() {
		return addItemLabel;
	}

	public void setAddItemLabel(String addItemLabel) {
		this.addItemLabel = addItemLabel;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for MultiFieldWidget");
	}

	@Override
	public String getXtype() {
		return MultiFieldWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for MultiFieldWidget");
	}
}
