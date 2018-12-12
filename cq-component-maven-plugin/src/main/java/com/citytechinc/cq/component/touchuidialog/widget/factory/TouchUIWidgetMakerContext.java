package com.citytechinc.cq.component.touchuidialog.widget.factory;

import com.citytechinc.cq.component.touchuidialog.widget.maker.TouchUIWidgetMaker;

public class TouchUIWidgetMakerContext {

	private final Class<? extends TouchUIWidgetMaker> makerClass;
	private final String resourceType;

	public TouchUIWidgetMakerContext(Class<? extends TouchUIWidgetMaker> makerClass, String resourceType) {
		this.makerClass = makerClass;
		this.resourceType = resourceType;
	}

	public Class<? extends TouchUIWidgetMaker> getMakerClass() {
		return makerClass;
	}

	public String getResourceType() {
		return resourceType;
	}

}
