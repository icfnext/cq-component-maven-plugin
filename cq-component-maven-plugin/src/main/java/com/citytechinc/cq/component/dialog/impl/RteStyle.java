package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class RteStyle extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String cssName;
	private final String text;

	public RteStyle(String fieldName, String cssName, String text) {
		super(PRIMARY_TYPE, null, fieldName, null, null);

		this.cssName = cssName;
		this.text = text;
	}

	public String getCssName() {
		return cssName;
	}

	public String getText() {
		return text;
	}

}
