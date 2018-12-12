package com.citytechinc.cq.component.touchuidialog;

import com.citytechinc.cq.component.xml.DefaultXmlElementParameters;

public class DefaultTouchUIDialogElementParameters extends DefaultXmlElementParameters implements TouchUIDialogElementParameters {

	protected String resourceType;

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

}
