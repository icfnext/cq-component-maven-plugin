package com.citytechinc.cq.component.editconfig;

import com.citytechinc.cq.component.xml.XmlElement;

import java.util.List;

public interface InPlaceEditorElement extends XmlElement {

    Boolean getActive();

    String getEditorType();

    ConfigElement getConfigElement();

    void setConfigElement(ConfigElement configElement);

    void setContainedElements(List<XmlElement> containedElements);
}
