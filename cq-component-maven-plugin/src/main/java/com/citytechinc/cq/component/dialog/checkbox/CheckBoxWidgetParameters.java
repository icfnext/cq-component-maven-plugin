package com.citytechinc.cq.component.dialog.checkbox;

import com.citytechinc.cq.component.dialog.widget.WidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class CheckBoxWidgetParameters extends WidgetParameters {
	private String inputValue;
	private boolean checked;

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for CheckBoxWidget");
	}

	@Override
	public String getXtype() {
		return CheckBoxWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for CheckBoxWidget");
	}
}
