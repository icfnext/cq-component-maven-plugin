package com.citytechinc.cq.component.xml;

import java.util.List;
import java.util.Map;

public interface XmlElement {

    NameSpacedAttribute<String> getPrimaryType();

    String getNameSpace();

    String getFieldName();

    Map<String, ?> getAdditionalProperties();

    List<? extends XmlElement> getContainedElements();
}
