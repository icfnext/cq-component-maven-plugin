package com.citytechinc.cq.component.dialog;

import java.util.List;
import java.util.Map;

public abstract class AbstractDialogElement implements DialogElement {
	private String primaryType;
	private String nameSpace;
	private String fieldName;
	private Map<String, String> additionalProperties;
	private List<? extends DialogElement> containedElements;

	public AbstractDialogElement(String primaryType, String nameSpace, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		this.primaryType = primaryType;
		this.nameSpace = nameSpace;
		this.fieldName = fieldName;
		this.additionalProperties = additionalProperties;
		this.containedElements = containedElements;
	}

	public final String getPrimaryType() {
		return primaryType;
	}

	public final String getNameSpace() {
		return nameSpace;
	}

	public final String getFieldName() {
		return fieldName;
	}

	public final Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public final List<? extends DialogElement> getContainedElements() {
		return containedElements;
	}
}
