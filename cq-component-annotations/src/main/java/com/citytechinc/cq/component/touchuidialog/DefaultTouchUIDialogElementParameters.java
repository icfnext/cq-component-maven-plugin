package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class DefaultTouchUIDialogElementParameters extends DefaultXmlElementParameters implements TouchUIDialogElementParameters {

	protected String resourceType;
	protected String touchUIDialogType;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getTouchUIDialogType() {
		return touchUIDialogType;
	}

	public void setTouchUIDialogType(String touchUIDialogType) {
		this.touchUIDialogType = touchUIDialogType;
	}
}