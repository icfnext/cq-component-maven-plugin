package com.citytechinc.cq.component.touchuidialog.layout.columns.fixedcolumns;

import com.citytechinc.cq.component.touchuidialog.layout.LayoutElementParameters;

public class FixedColumnsLayoutElementParameters extends LayoutElementParameters {

	@Override
	public String getResourceType() {
		return FixedColumnsLayoutElement.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resource type is Static for Tabs Layout Element");
	}

}
