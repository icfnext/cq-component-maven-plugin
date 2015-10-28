/**
 *    Copyright 2013 CITYTECH, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
