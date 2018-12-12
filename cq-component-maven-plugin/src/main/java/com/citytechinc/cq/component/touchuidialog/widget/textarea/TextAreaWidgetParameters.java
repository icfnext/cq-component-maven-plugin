package com.citytechinc.cq.component.touchuidialog.widget.textarea;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class TextAreaWidgetParameters extends DefaultTouchUIWidgetParameters {

	private Integer cols;
	private Integer rows;
	private String resize;

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getResize() {
		return resize;
	}

	public void setResize(String resize) {
		this.resize = resize;
	}

	@Override
	public String getResourceType() {
		return TextAreaWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for TextAreaWidget");
	}
}
