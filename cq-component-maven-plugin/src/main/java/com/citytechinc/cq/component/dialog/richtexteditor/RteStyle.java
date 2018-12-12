package com.citytechinc.cq.component.dialog.richtexteditor;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class RteStyle extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String cssName;
	private final String text;

	public RteStyle(RteStyleParameters parameters) {
		super(parameters);

		this.cssName = parameters.getCssName();
		this.text = parameters.getText();
	}

	public String getCssName() {
		return cssName;
	}

	public String getText() {
		return text;
	}

}
