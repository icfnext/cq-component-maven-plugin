package com.citytechinc.cq.component.dialog.factory;

import com.citytechinc.cq.component.dialog.maker.WidgetMaker;

public class WidgetMakerContext {

	private final WidgetMaker maker;
	private final String xtype;

	public WidgetMakerContext(WidgetMaker maker, String xtype) {
		this.maker = maker;
		this.xtype = xtype;
	}

	public WidgetMaker getWidgetMaker() {
		return maker;
	}

	public String getXtype() {
		return xtype;
	}

}
