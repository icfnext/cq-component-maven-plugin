package com.citytechinc.cq.component.dialog.impl;

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.Tab;

public class SimpleTab implements Tab {

	private final String primaryType;
	private final String xtype;
	private final Container widgetCollection;
	private final String name;
	private final String title;

	public SimpleTab() {
		this("", new WidgetCollection());
	}

	public SimpleTab(String name, Container widgetCollection) {
		this.widgetCollection = widgetCollection;
		this.name = name;

		this.title = name;

		this.primaryType = "cq:Widget";
		this.xtype = "panel";
	}

	public Container getWidgetCollection() {
		return widgetCollection;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer retStringBuffer = new StringBuffer(name + " ");

		retStringBuffer.append(widgetCollection.toString());

		return retStringBuffer.toString();
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getXtype() {
		return xtype;
	}

	public String getTitle() {
		return title;
	}

}
