package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public class DefaultXmlElementParameters implements XmlElementParameters {
	protected String primaryType;
	protected String fieldName;
	protected Map<String, ?> additionalProperties;
	protected List<? extends XmlElement> containedElements;
	protected String nameSpace;

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Map<String, ?> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, ?> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public List<? extends XmlElement> getContainedElements() {
		return containedElements;
	}

	public void setContainedElements(List<? extends XmlElement> containedElements) {
		this.containedElements = containedElements;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}
