package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public interface XmlElementParameters {

	public abstract String getPrimaryType();

	public abstract void setPrimaryType(String primaryType);

	public abstract String getFieldName();

	public abstract void setFieldName(String fieldName);

	public abstract Map<String, ?> getAdditionalProperties();

	public abstract void setAdditionalProperties(Map<String, ?> additionalProperties);

	public abstract List<? extends XmlElement> getContainedElements();

	public abstract void setContainedElements(List<? extends XmlElement> containedElements);

	public abstract String getNameSpace();

	public abstract void setNameSpace(String nameSpace);

}