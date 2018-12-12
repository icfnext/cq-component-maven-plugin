package com.citytechinc.cq.component.editconfig;

import java.util.List;

import com.citytechinc.cq.component.xml.XmlElement;

public interface InPlaceEditorElement extends XmlElement {
	Boolean getActive();

	String getEditorType();

	ConfigElement getConfigElement();

	void setConfigElement(ConfigElement configElement);

	void setContainedElements(List<XmlElement> containedElements);
}
