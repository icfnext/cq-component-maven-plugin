package com.citytechinc.cq.component.touchuidialog.widget.richtexteditor;

import com.citytechinc.cq.component.touchuidialog.AbstractTouchUIDialogElement;

public class Popover extends AbstractTouchUIDialogElement {

	public static final String PRIMARY_TYPE = "nt:unstructured";
	private final String ref;
	private final String[] items;

	public Popover(PopoverParameters parameters) {
		super(parameters);
		ref = parameters.getRef();
		items = parameters.getItems();
	}

	public String getRef() {
		return ref;
	}

	public Object getItems() {
		return items.length == 1 ? items[0] : items;
	}
}
