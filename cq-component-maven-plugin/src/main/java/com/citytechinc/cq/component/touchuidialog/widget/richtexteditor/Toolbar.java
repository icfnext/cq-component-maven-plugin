package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public class Toolbar extends AbstractTouchUIDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String[] toolbar;

	public Toolbar(ToolbarParameters parameters) {
		super(parameters);
		toolbar = parameters.getToolbar();
	}

	public String[] getToolbar() {
		return toolbar;
	}

}
