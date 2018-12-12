package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.xml.NameSpacedAttribute;
import com.citytechinc.cq.component.xml.XmlElement;

public interface TouchUIDialogElement extends XmlElement {

    NameSpacedAttribute<String> getSlingResourceType();

    void setFieldName(String fieldName);

    double getRanking();

    void setRanking(double ranking);

}
