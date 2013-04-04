package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class RteParaFormat extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";

	private final String tag;
	private final String description;

	public RteParaFormat(String fieldName, String tag, String description) {
		super(PRIMARY_TYPE, null, fieldName, null, null);

		this.tag = tag;
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public String getDescription() {
		return description;
	}
}
