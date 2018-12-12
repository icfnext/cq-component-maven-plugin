package com.citytechinc.cq.component.touchuidialog;

public enum TouchUIDialogType {

	CORAL2("coral2"),
	CORAL3("coral3");

	private String type;

	TouchUIDialogType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public boolean isOfType(String type) {
		return this.type.equalsIgnoreCase(type);
	}
}