package com.citytechinc.cq.component.editconfig;

import com.citytechinc.cq.component.xml.DefaultXmlElement;
import com.citytechinc.cq.component.xml.XmlElementParameters;

public class ConfigElement extends DefaultXmlElement {

    public ConfigElement(XmlElementParameters parameters) {
        super(parameters);
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

}
