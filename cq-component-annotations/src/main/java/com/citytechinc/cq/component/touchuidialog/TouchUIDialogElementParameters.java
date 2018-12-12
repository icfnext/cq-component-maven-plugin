package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.xml.XmlElementParameters;

public interface TouchUIDialogElementParameters extends XmlElementParameters {

    String getResourceType();

    void setResourceType(String resourceType);

	String getTouchUIDialogType();

	void setTouchUIDialogType(String touchUIDialogType);
}