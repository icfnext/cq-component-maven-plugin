package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.xml.XmlElement;

public interface DialogElement extends XmlElement {

    void setRanking(double ranking);

    double getRanking();

    void setFieldName(String fieldName);
}
