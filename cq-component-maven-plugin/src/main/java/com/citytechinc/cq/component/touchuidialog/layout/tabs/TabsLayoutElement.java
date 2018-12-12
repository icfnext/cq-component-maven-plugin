package com.citytechinc.cq.component.touchuidialog.layout.tabs;

import com.citytechinc.cq.component.touchuidialog.layout.AbstractLayoutElement;

public class TabsLayoutElement extends AbstractLayoutElement {

	public static final String RESOURCE_TYPE = "granite/ui/components/foundation/layouts/tabs";

	private String type;

	public TabsLayoutElement(TabsLayoutElementParameters parameters) {
		super(parameters);

		this.type = parameters.getType();
	}

	public String getType() {
		return type;
	}

}
