package com.citytechinc.cq.component.dialog;

import com.citytechinc.cq.component.dialog.tabpanel.TabPanel;
import com.citytechinc.cq.component.dialog.tabpanel.TabPanelParameters;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollection;
import com.citytechinc.cq.component.dialog.widgetcollection.WidgetCollectionParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dialog extends AbstractWidget {
	private final String title;
	private final String activeTab;
	private final String fileName;
	private final Integer width;
	private final Integer height;

	public Dialog(DialogParameters parameters) {
		super(parameters);
		this.title = parameters.getTitle();
		this.activeTab = "0";
		this.fileName = parameters.getFileName();
		this.height = parameters.getHeight();
		this.width = parameters.getWidth();
	}

	public static final List<DialogElement> buildTabPanel(List<DialogElement> tabs) {
		List<DialogElement> containedElements = new ArrayList<DialogElement>();
		WidgetCollectionParameters wcp = new WidgetCollectionParameters();
		wcp.setContainedElements(tabs);
		DialogElement tabPanelContainer = new WidgetCollection(wcp);

		TabPanelParameters tpp = new TabPanelParameters();
		tpp.setContainedElements(Arrays.asList(new DialogElement[] { tabPanelContainer }));
		DialogElement tabPanel = new TabPanel(tpp);

		WidgetCollectionParameters widgetCollectionForTabPanelParams = new WidgetCollectionParameters();
		widgetCollectionForTabPanelParams.setContainedElements(Arrays.asList(new DialogElement[] { tabPanel }));
		containedElements.add(new WidgetCollection(widgetCollectionForTabPanelParams));
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
