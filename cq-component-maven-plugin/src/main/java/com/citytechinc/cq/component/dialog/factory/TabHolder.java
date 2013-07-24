package com.citytechinc.cq.component.dialog.factory;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;

public class TabHolder {
	private final List<DialogElement> elements = new ArrayList<DialogElement>();
	private String title;

	public List<DialogElement> getElements() {
		return elements;
	}

	public void addElement(DialogElement element) {
		elements.add(element);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
