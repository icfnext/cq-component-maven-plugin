package com.citytechinc.cq.component.dialog.sizefield;

import com.citytechinc.cq.component.dialog.widget.DefaultWidgetParameters;
import com.citytechinc.cq.component.util.Constants;

public class SizeFieldWidgetParameters extends DefaultWidgetParameters {
	private String heightParameter;
	private String heightPrefix;
	private String heightSuffix;
	private String widthParameter;
	private String widthPrefix;
	private String widthSuffix;
	private int fieldWidth;

	public String getHeightParameter() {
		return heightParameter;
	}

	public void setHeightParameter(String heightParameter) {
		this.heightParameter = heightParameter;
	}

	public String getHeightPrefix() {
		return heightPrefix;
	}

	public void setHeightPrefix(String heightPrefix) {
		this.heightPrefix = heightPrefix;
	}

	public String getHeightSuffix() {
		return heightSuffix;
	}

	public void setHeightSuffix(String heightSuffix) {
		this.heightSuffix = heightSuffix;
	}

	public String getWidthParameter() {
		return widthParameter;
	}

	public void setWidthParameter(String widthParameter) {
		this.widthParameter = widthParameter;
	}

	public String getWidthPrefix() {
		return widthPrefix;
	}

	public void setWidthPrefix(String widthPrefix) {
		this.widthPrefix = widthPrefix;
	}

	public String getWidthSuffix() {
		return widthSuffix;
	}

	public void setWidthSuffix(String widthSuffix) {
		this.widthSuffix = widthSuffix;
	}

	public int getFieldWidth() {
		return fieldWidth;
	}

	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}

	@Override
	public String getPrimaryType() {
		return Constants.CQ_WIDGET;
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("PrimaryType is Static for SizeFieldWidget");
	}

	@Override
	public String getXtype() {
		return SizeFieldWidget.XTYPE;
	}

	@Override
	public void setXtype(String xtype) {
		throw new UnsupportedOperationException("xtype is Static for SizeFieldWidget");
	}
}
