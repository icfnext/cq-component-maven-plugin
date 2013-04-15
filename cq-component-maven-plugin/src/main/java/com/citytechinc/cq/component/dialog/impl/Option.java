package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Option extends AbstractDialogElement {

	private static final String PRIMARY_TYPE = "nt:unstructured";
	private final String text;
	private final String value;
	private final String qtip;

	public Option(String text, String value, String qtip) {
		super(PRIMARY_TYPE, null, value, null, null);
		this.text = text;
		this.value = value;
		this.qtip = qtip;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

	public String getQtip() {
		return qtip;
	}
}
