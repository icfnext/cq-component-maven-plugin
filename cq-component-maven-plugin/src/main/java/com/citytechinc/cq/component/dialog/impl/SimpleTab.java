package com.citytechinc.cq.component.dialog.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.Tab;

public class SimpleTab implements Tab {

	private final String primaryType;
	private final String xtype;
	private final List<DialogElement> containedElements=new ArrayList<DialogElement>();
	private final String title;

	public SimpleTab() {
		this("", new WidgetCollection());
	}

	public SimpleTab(String name, WidgetCollection widgetCollection) {
		containedElements.add(widgetCollection);

		this.title = name;

		this.primaryType = "cq:Widget";
		this.xtype = "panel";
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
	
	public List<? extends DialogElement> getContainedElements() {
		return containedElements;
	}

	public String getNameSpace() {
		return null;
	}

	public String getFieldName() {
		return StringUtils.deleteWhitespace(title);
	}
}
