package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import com.citytechinc.cq.component.dialog.Dialog;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.global.Constants;

public class SimpleDialog implements Dialog {

	private static final String xtype="dialog";
	private static final String primaryType="cq:Dialog";
	private final String title;
	private final String activeTab;
	private List<DialogElement> containedElements=new ArrayList<DialogElement>();
	public SimpleDialog() {
		this(new ArrayList<DialogElement>(), "Dialog");
	}

	public SimpleDialog(List<DialogElement> tabs, String title) {
		this.title = title;
		this.activeTab = "0";

		DialogElement tabPanelContainer = new WidgetCollection(tabs);
		DialogElement tabPanel = new TabPanel(tabPanelContainer);
		containedElements.add( new WidgetCollection(tabPanel));
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

	public String getNameSpace() {
		return Constants.JCR_NS_URI;
	}

	public List<? extends DialogElement> getContainedElements() {
		return containedElements;
	}

	public String getFieldName() {
		return "jcr:root";
	}
}
