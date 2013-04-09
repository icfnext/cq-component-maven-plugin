package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Namespace extends AbstractDialogElement {

	private static final String PRIMARY_TYPE = "nt:unstructured";
	private final String name;
	private final String maximum;

	public Namespace(String name, String maximum) {
		super(PRIMARY_TYPE, null, name, null, null);
		this.name = name;
		this.maximum = maximum;
	}

	public String getName() {
		return name;
	}

	public String getMaximum() {
		return maximum;
	}
}
