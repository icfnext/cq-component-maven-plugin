package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.Option;

public class SimpleOption implements Option {

	private final String primaryType;
	private final String text;
	private final String value;

	public SimpleOption(String text, String value) {

		this.primaryType = "nt:unstructured";

		this.text = text;
		this.value = value;

	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getText() {
		return text;
	}

	public String getValue() {
		return value;
	}

}
