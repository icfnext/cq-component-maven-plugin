package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.DialogElement;

public class TabPanel implements Container {

	private final String primaryType;
	private final List<DialogElement> elements;

	public TabPanel() {
		this(new ArrayList<DialogElement>());
	}

	public TabPanel(DialogElement element) {
		elements = new ArrayList<DialogElement>();

		elements.add(element);

		this.primaryType = "cq:TabPanel";
	}

	public TabPanel(List<DialogElement> elements) {
		this.primaryType = "cq:TabPanel";
		this.elements = elements;
	}

	public List<DialogElement> getContainedElements() {
		return elements;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getName() {
		return "tabs";
	}

}
