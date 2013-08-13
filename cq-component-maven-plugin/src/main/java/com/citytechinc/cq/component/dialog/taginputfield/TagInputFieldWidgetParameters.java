package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class TagInputFieldWidgetParameters extends WidgetParameters {
	private boolean displayTitles;

	public boolean isDisplayTitles() {
		return displayTitles;
	}

	public void setDisplayTitles(boolean displayTitles) {
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for TagInputFieldWidget");
	}

	@Override
	public String getXtype() {
		return TagInputFieldWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for TagInputFieldWidget");
	}
}
