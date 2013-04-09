package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;

public class Dialog extends AbstractWidget {
	private static final String DEFAULT_TITLE = "Dialog";
	private static final String DEFAULT_FILE_NAME = "dialog.xml";
	private static final String XTYPE = "dialog";
	private static final String PRIMARY_TYPE = "cq:Dialog";
	private static final String FIELD_NAME = "jcr:root";
	private final String title;
	private final String activeTab;
	private final String fileName;
	private final Integer width;
	private final Integer height;

	public Dialog() {
		this(new ArrayList<DialogElement>(), DEFAULT_TITLE, DEFAULT_FILE_NAME, null, null);
	}

	public Dialog(List<DialogElement> tabs, String title, String fileName, Integer width, Integer height) {
		super(XTYPE, null, null, false, false, null, null, PRIMARY_TYPE, null, FIELD_NAME, null, buildTabPanel(tabs));
		this.title = title;
		this.activeTab = "0";
		this.fileName = fileName;
		this.height = height;
		this.width = width;
	}

	private static final List<DialogElement> buildTabPanel(List<DialogElement> tabs) {
		List<DialogElement> containedElements = new ArrayList<DialogElement>();
		DialogElement tabPanelContainer = new WidgetCollection(tabs);
		DialogElement tabPanel = new TabPanel(tabPanelContainer);
		containedElements.add(new WidgetCollection(tabPanel));
		return containedElements;
	}

	public String getTitle() {
		return title;
	}

	public String getActiveTab() {
		return activeTab;
	}

	public String getFileName() {
		return fileName + ".xml";
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}
}
