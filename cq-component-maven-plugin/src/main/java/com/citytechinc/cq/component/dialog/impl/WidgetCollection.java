package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.DialogElement;

public class WidgetCollection implements DialogElement {

	private final static String primaryType="cq:WidgetCollection";
	private final List<DialogElement> elements;
	private String fieldName="items";

	public WidgetCollection() {
		this(new ArrayList<DialogElement>());
	}

	public WidgetCollection(DialogElement element) {
		this.elements = new ArrayList<DialogElement>();

		this.elements.add(element);
	}

	public WidgetCollection(List<DialogElement> elements) {
		this.elements = elements;
	}
	
	public WidgetCollection(String fieldName) {
		this(new ArrayList<DialogElement>(),fieldName);
	}

	public WidgetCollection(DialogElement element,String fieldName) {
		this.elements = new ArrayList<DialogElement>();

		this.elements.add(element);
		this.fieldName=fieldName;
	}

	public WidgetCollection(List<DialogElement> elements,String fieldName) {
		this.elements = elements;
		this.fieldName=fieldName;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public List<DialogElement> getContainedElements() {
		return elements;
	}

	public String getNameSpace() {
		return null;
	}

	public String getFieldName() {
		return fieldName;
	}
}
