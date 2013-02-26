package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.Container;
import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;

public class SimpleDialog implements Dialog {

	private final String xtype;
	private final String primaryType;
	private final String title;
	private final String activeTab;
	private final Container widgetCollection;

	public SimpleDialog() {
		this(new ArrayList<DialogElement>(), "Dialog");
	}

	public SimpleDialog(List<DialogElement> tabs, String title) {

		this.xtype = "dialog";
		this.primaryType = "cq:Dialog";
		this.title = title;
		this.activeTab = "0";

		Container tabPanelContainer = new WidgetCollection(tabs);
		Container tabPanel = new TabPanel(tabPanelContainer);
		widgetCollection = new WidgetCollection(tabPanel);

	}

	public String toString() {
		StringBuffer retStringBuffer = new StringBuffer("Dialog");

		retStringBuffer.append(widgetCollection.toString());

		return retStringBuffer.toString();
	}

	public String getXtype() {
		return xtype;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public String getTitle() {
		return title;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public Container getWidgetCollection() {
		return widgetCollection;
	}

	public String getXType() {
		return xtype;
	}


}
