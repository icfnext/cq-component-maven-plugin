package com.citytechinc.cq.component.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractDialogElement implements DialogElement {
	private final String primaryType;
	private final String nameSpace;
	private String fieldName;
	private final Map<String, String> additionalProperties;
	private List<? extends DialogElement> containedElements;
	private double ranking;

	public AbstractDialogElement(String primaryType, String nameSpace, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		this.primaryType = primaryType;
		this.nameSpace = nameSpace;
		this.fieldName = fieldName;
		this.additionalProperties = additionalProperties;
		this.containedElements = containedElements;
		if (containedElements != null) {
			Collections.sort(containedElements, new DialogElementComparator());
		}
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

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public double getRanking() {
		return ranking;
	}

	public void setListeners(Listeners listeners) {
		List<DialogElement> newElements = new ArrayList<DialogElement>();
		if (containedElements != null) {
			newElements.addAll(containedElements);
		}
		newElements.add(listeners);
		containedElements = newElements;
	}
}
