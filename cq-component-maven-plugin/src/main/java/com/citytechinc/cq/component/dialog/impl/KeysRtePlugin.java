package com.citytechinc.cq.component.dialog.impl;

public class KeysRtePlugin extends RtePlugin {

	public static final String KEYS = "keys";

	private final String tabSize;

	public KeysRtePlugin(String tabSize) {
		super(KEYS, null, null);

		this.tabSize = tabSize;
	}

	public String getTabSize() {
		return tabSize;
	}

}
