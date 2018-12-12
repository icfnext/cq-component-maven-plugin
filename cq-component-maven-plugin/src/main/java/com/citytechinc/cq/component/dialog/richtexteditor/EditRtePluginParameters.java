package com.citytechinc.cq.component.dialog.richtexteditor;

public class EditRtePluginParameters extends RtePluginParameters {
	public static final String EDIT = "edit";
	private String defaultPasteMode;

	public String getDefaultPasteMode() {
		return defaultPasteMode;
	}

	public void setDefaultPasteMode(String defaultPasteMode) {
		this.defaultPasteMode = defaultPasteMode;
	}

	@Override
	public String getFieldName() {
		return EDIT;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for EditRTEPlugin");
	}
}
