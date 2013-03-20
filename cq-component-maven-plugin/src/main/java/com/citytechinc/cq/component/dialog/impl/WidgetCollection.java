package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.DialogElement;

public class WidgetCollection implements Container {

	private final String primaryType;
	private final List<DialogElement> elements;

	public WidgetCollection() {
		this(new ArrayList<DialogElement>());
	}

	public WidgetCollection(DialogElement element) {
		this.elements = new ArrayList<DialogElement>();

		this.elements.add(element);

		this.primaryType = "cq:WidgetCollection";
	}

	public WidgetCollection(List<DialogElement> elements) {
		this.primaryType = "cq:WidgetCollection";
		this.elements = elements;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public List<DialogElement> getContainedElements() {
		return elements;
	}

	public String getName() {
		return "items";
	}
}
