package com.citytechinc.cq.component.dialog.datetime;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class DateTimeWidgetParameters extends WidgetParameters {
	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for DateTimeWidget");
	}

	@Override
	public String getXtype() {
		return DateTimeWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for DateTimeWidget");
	}
}
