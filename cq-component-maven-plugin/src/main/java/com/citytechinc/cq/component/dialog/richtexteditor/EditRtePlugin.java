package com.citytechinc.cq.component.dialog.richtexteditor;

public class EditRtePlugin extends RtePlugin {

	public static final String EDIT = "edit";

	private final String defaultPasteMode;

	public EditRtePlugin(EditRtePluginParameters parameters) {

		super(parameters);

		this.defaultPasteMode = parameters.getDefaultPasteMode();

	}

	public String getDefaultPasteMode() {
		return defaultPasteMode;
	}
}
