package com.citytechinc.cq.component.dialog.impl;

import java.util.List;
import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.Panel;
import com.citytechinc.cq.component.dialog.AbstractWidget;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.impl.PanelWidgetMaker;

@Widget(annotationClass = Panel.class, makerClass = PanelWidgetMaker.class, xtype = PanelWidget.XTYPE)
public class PanelWidget extends AbstractWidget {
	public static final String XTYPE = "panel";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private final boolean collapseFirst;
	private final boolean collapsible;
	private final boolean collapsed;
	private final boolean border;
	private final String title;

	public PanelWidget(boolean collapseFirst, boolean collapsible, boolean collapsed, boolean border,
		String title, String fieldLabel, String fieldDescription, boolean hideLabel, String fieldName,
		Map<String, String> additionalProperties, List<? extends DialogElement> containedElements) {
		super(XTYPE, fieldLabel, fieldDescription, true, hideLabel, null, null, PRIMARY_TYPE, null, fieldName,
			additionalProperties, containedElements);
		this.collapseFirst = collapseFirst;
		this.collapsible = collapsible;
		this.collapsed = collapsed;
		this.border = border;
		this.title = title;
	}

	public boolean isCollapseFirst() {
		return collapseFirst;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public boolean isBorder() {
		return border;
	}
	
	public String getTitle(){
		return title;
	}
}
