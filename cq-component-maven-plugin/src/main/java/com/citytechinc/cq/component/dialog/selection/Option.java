package com.citytechinc.cq.component.dialog.selection;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;

public class Option extends AbstractDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";
	private final String text;
	private final String value;
	private final String qtip;

	public Option(OptionParameters parameters) {
		super(parameters);
		this.text = parameters.getText();
		this.value = parameters.getValue();
		this.qtip = parameters.getQtip();
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
