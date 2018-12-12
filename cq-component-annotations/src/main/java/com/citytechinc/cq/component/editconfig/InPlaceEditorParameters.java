package com.citytechinc.cq.component.editconfig;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;
import com.citytechinc.cq.component.xml.XmlElement;
import com.citytechinc.cq.component.xml.XmlElementParameters;

import java.util.List;

public abstract class InPlaceEditorParameters extends DefaultXmlElementParameters implements XmlElementParameters {

    public abstract Boolean isActive();

    public abstract void setActive(Boolean active);

    public abstract String getEditorType();

    public abstract void setEditorType(String editorType);

    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract ConfigElement getConfigElement();

    public abstract void setConfigElement(ConfigElement configNode);

    @Override
    public void setContainedElements(List<? extends XmlElement> containedElements) {
        throw new UnsupportedOperationException("contained elements should not be set for in place editors");
    }
}
