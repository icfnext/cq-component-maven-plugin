package com.citytechinc.cq.component.touchuidialog.widget.multifield;

import com.citytechinc.cq.component.touchuidialog.widget.DefaultTouchUIWidgetParameters;

public class MultiFieldWidgetParameters extends DefaultTouchUIWidgetParameters {
	@Override
	public String getResourceType() {
		return MultiFieldWidget.RESOURCE_TYPE;
	}

	@Override
	public void setResourceType(String resourceType) {
		throw new UnsupportedOperationException("resourceType is Static for MultiFieldWidget");
	}
}
