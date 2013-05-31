package com.citytechinc.cq.component.dialog;

import java.util.HashMap;
import java.util.Map;

import com.citytechinc.cq.component.annotations.Listener;

public class Listeners extends AbstractDialogElement {
	private static final String FIELD_NAME = "listeners";
	private static final String PRIMARY_TYPE = "nt:unstructured";
	private final Map<String, String> properties = new HashMap<String, String>();

	public Listeners(Listener[] listeners) {
		super(PRIMARY_TYPE, null, FIELD_NAME, null, null);
		for (Listener listener : listeners) {
			properties.put(listener.name(), listener.value());
		}
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
