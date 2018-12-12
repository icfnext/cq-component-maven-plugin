package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.xml.XmlElement;

public interface DialogElement extends XmlElement {
	public void setRanking(double ranking);

	public double getRanking();

	public void setFieldName(String fieldName);
}
