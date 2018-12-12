package com.citytechinc.cq.component.touchuidialog.widget.selection.options;

import com.citytechinc.cq.component.touchuidialog.DefaultTouchUIDialogElementParameters;

public class OptionParameters extends DefaultTouchUIDialogElementParameters {

	private String name;
	private String text;
	private String value;
	private boolean selected;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String getPrimaryType() {
		return "nt:unstructured";
	}

	@Override
	public void setPrimaryType(String primaryType) {
		throw new UnsupportedOperationException("Primary Type is static for Option");
	}

}
