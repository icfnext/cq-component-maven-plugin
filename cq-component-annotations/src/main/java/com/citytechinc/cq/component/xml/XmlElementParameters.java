package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public interface XmlElementParameters {

    String getPrimaryType();

    void setPrimaryType(String primaryType);

    String getFieldName();

    void setFieldName(String fieldName);

    Map<String, ?> getAdditionalProperties();

    void setAdditionalProperties(Map<String, ?> additionalProperties);

    List<? extends XmlElement> getContainedElements();

    void setContainedElements(List<? extends XmlElement> containedElements);

    String getNameSpace();

    void setNameSpace(String nameSpace);

}