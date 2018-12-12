package com.citytechinc.cq.component.dialog.dialogfieldset;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = DialogFieldSet.class, makerClass = DialogFieldSetWidgetMaker.class,
	xtype = DialogFieldSetWidget.XTYPE)
public class DialogFieldSetWidget extends AbstractWidget {
	public static final String XTYPE = "dialogfieldset";

	private final boolean collapseFirst;
	private final boolean collapsible;
	private final boolean collapsed;
	private final boolean border;
	private final String title;

	public DialogFieldSetWidget(DialogFieldSetWidgetParameters parameters) {
		super(parameters);
		this.collapseFirst = parameters.isCollapseFirst();
		this.collapsible = parameters.isCollapsible();
		this.collapsed = parameters.isCollapsed();
		this.border = parameters.isBorder();
		this.title = parameters.getTitle();
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

	public String getTitle() {
		return title;
	}
}
