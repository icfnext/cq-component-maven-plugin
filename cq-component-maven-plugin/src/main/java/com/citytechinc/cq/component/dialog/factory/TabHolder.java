package com.citytechinc.cq.component.dialog.factory;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Listeners;

public class TabHolder {
	private final List<DialogElement> elements = new ArrayList<DialogElement>();
	private String title;
	private Listeners listeners;

	public List<DialogElement> getElements() {
		return elements;
	}

	public void addElement(DialogElement element) {
		elements.add(element);
	}

	public Listeners getListeners() {
		return listeners;
	}

	public String getTitle() {
		return title;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
