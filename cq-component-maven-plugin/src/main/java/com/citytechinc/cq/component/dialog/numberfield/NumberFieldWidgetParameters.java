package com.citytechinc.cq.component.dialog.numberfield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class NumberFieldWidgetParameters extends DefaultWidgetParameters {
	private boolean allowDecimals;
	private boolean allowNegative;
	private int decimalPrecision;
	private String decimalSeparator;

	public boolean isAllowDecimals() {
		return allowDecimals;
	}

	public void setAllowDecimals(boolean allowDecimals) {
		this.allowDecimals = allowDecimals;
	}

	public boolean isAllowNegative() {
		return allowNegative;
	}

	public void setAllowNegative(boolean allowNegative) {
		this.allowNegative = allowNegative;
	}

	public int getDecimalPrecision() {
		return decimalPrecision;
	}

	public void setDecimalPrecision(int decimalPrecision) {
		this.decimalPrecision = decimalPrecision;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for NumberFieldWidget");
	}

	@Override
	public String getXtype() {
		return NumberFieldWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for NumberFieldWidget");
	}
}
