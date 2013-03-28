package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.DialogElement;

public class WidgetCollection extends AbstractDialogElement {

	private static final String PRIMARY_TYPE="cq:WidgetCollection";
	private static final String DEFAULT_FIELD_NAME="items";

	public WidgetCollection() {
		this(new ArrayList<DialogElement>());
	}

	public WidgetCollection(DialogElement element) {
		this(element,DEFAULT_FIELD_NAME);
	}

	public WidgetCollection(List<DialogElement> elements) {
		this(elements,DEFAULT_FIELD_NAME);
	}
	
	public WidgetCollection(String fieldName) {
		this(new ArrayList<DialogElement>(),fieldName);
	}

	public WidgetCollection(DialogElement element,String fieldName) {
		this(Arrays.asList(new DialogElement[]{element}),fieldName);
	}

	public WidgetCollection(List<DialogElement> elements,String fieldName) {
		super(PRIMARY_TYPE, null, fieldName, null, elements);
	}
}
