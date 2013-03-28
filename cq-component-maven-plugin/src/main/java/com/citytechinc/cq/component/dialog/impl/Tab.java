package com.citytechinc.cq.component.dialog.impl;

import java.util.Arrays;
import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.dialog.AbstractDialogElement;
import com.citytechinc.cq.component.dialog.DialogElement;

public class Tab extends AbstractDialogElement {
	private static final String PRIMARY_TYPE="cq:Widget";
	private final String title;

	public Tab() {
		this("", new WidgetCollection());
	}

	public Tab(String title, WidgetCollection widgetCollection) {
		super(PRIMARY_TYPE, null, StringUtils.deleteWhitespace(title), null, Arrays.asList(new DialogElement[]{widgetCollection}));
		this.title = title;
	}

	
	public String getTitle() {
		return title;
	}
}
