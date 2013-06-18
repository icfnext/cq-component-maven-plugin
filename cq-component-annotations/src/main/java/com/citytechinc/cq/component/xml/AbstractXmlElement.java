package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.util.Constants;

public class AbstractXmlElement implements XmlElement {
	protected NameSpacedAttribute<String> primaryType;
	protected String fieldName;
	protected Map<String, String> additionalProperties;
	protected List<? extends XmlElement> containedElements;
	protected String nameSpace;

	public AbstractXmlElement(XmlElementParameters parameters) {
		primaryType = new NameSpacedAttribute<String>(Constants.JCR_NS_URI, Constants.JCR_NS_PREFIX,
			parameters.getPrimaryType());
		this.fieldName = parameters.getFieldName();
		this.additionalProperties = parameters.getAdditionalProperties();
		this.containedElements = parameters.getContainedElements();
		this.nameSpace = parameters.getNameSpace();
	}

	public NameSpacedAttribute<String> getPrimaryType() {
		return primaryType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}

	public List<? extends XmlElement> getContainedElements() {
		return containedElements;
	}

	public String getNameSpace() {
		return nameSpace;
	}
}
