package com.citytechinc.cq.component.dialog.richtexteditor;

public class KeysRtePluginParameters extends RtePluginParameters {
	public static final String KEYS = "keys";
	private String tabSize;

	public String getTabSize() {
		return tabSize;
	}

	public void setTabSize(String tabSize) {
		this.tabSize = tabSize;
	}

	@Override
	public String getFieldName() {
		return KEYS;
	}

	@Override
	public void setFieldName(String fieldName) {
		throw new UnsupportedOperationException("Field Name is static for KeysRtePlugin");
	}
}
