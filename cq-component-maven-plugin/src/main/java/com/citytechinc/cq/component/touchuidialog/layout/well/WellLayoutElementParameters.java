package com.citytechinc.cq.component.touchuidialog.layout.well;

import com.citytechinc.cq.component.touchuidialog.layout.LayoutElementParameters;

public class WellLayoutElementParameters extends LayoutElementParameters {

	@Override
	public String getResourceType() {
		return WellLayoutElement.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resource type is Static for Well Layout Element");
	}

}
