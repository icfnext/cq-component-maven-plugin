package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public interface XmlElement {
	public NameSpacedAttribute<String> getPrimaryType();

	public String getNameSpace();

	public String getFieldName();

	public Map<String, ?> getAdditionalProperties();

	public List<? extends XmlElement> getContainedElements();
}
