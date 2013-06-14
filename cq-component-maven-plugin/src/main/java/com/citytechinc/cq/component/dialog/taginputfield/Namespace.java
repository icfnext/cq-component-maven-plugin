package com.citytechinc.cq.component.dialog.taginputfield;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Namespace extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";
	private final String name;
	private final String maximum;

	public Namespace(NamespaceParameters parameters) {
		super(parameters);
		this.name = parameters.getName();
		this.maximum = parameters.getMaximum();
	}

	public String getName() {
		return name;
	}

	public String getMaximum() {
		return maximum;
	}
}
