package com.citytechinc.cq.component.dialog;

import java.util.List;
import java.util.Map;

public class DialogElementParameters {
	protected String primaryType;
	protected String nameSpace;
	protected String fieldName;
	protected Map<String, String> additionalProperties;
	protected List<? extends DialogElement> containedElements;
	protected double ranking;
	protected Listeners listeners;

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, String> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public List<? extends DialogElement> getContainedElements() {
		return containedElements;
	}

	public void setContainedElements(List<? extends DialogElement> containedElements) {
		this.containedElements = containedElements;
	}

	public double getRanking() {
		return ranking;
	}

	public void setRanking(double ranking) {
		this.ranking = ranking;
	}

	public Listeners getListeners() {
		return listeners;
	}

	public void setListeners(Listeners listeners) {
		this.listeners = listeners;
	}
}
