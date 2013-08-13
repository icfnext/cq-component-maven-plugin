package com.citytechinc.cq.component.dialog.hidden;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class HiddenWidgetParameters extends WidgetParameters {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for HiddenWidget");
	}

	@Override
	public String getXtype() {
		return HiddenWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for HiddenWidget");
	}
}
