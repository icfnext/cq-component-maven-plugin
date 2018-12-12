package com.citytechinc.cq.component.dialog.richtexteditor;

public class KeysRtePlugin extends RtePlugin {

	public static final String KEYS = "keys";

	private final String tabSize;

	public KeysRtePlugin(KeysRtePluginParameters parameters) {
		super(parameters);

		this.tabSize = parameters.getTabSize();
	}

	public String getTabSize() {
		return tabSize;
	}

}
