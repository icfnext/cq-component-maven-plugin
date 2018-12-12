package com.citytechinc.cq.component.dialog.pathfield;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = PathField.class, makerClass = PathFieldWidgetMaker.class, xtype = PathFieldWidget.XTYPE)
public class PathFieldWidget extends AbstractWidget {
	public static final String XTYPE = "pathfield";

	private final boolean escapeAmp;
	private final boolean hideTrigger;
	private final boolean parBrowse;
	private final String rootPath;
	private final String rootTitle;
	private final boolean showTitleInTree;

	public PathFieldWidget(PathFieldWidgetParameters parameters) {
		super(parameters);
		this.escapeAmp = parameters.isEscapeAmp();
		this.hideTrigger = parameters.isHideTrigger();
		this.parBrowse = parameters.isParBrowse();
		this.rootPath = parameters.getRootPath();
		this.rootTitle = parameters.getRootTitle();
		this.showTitleInTree = parameters.isShowTitleInTree();
	}

	public boolean isEscapeAmp() {
		return escapeAmp;
	}

	public boolean isHideTrigger() {
		return hideTrigger;
	}

	public boolean isParBrowse() {
		return parBrowse;
	}

	public String getRootPath() {
		return rootPath;
	}

	public String getRootTitle() {
		return rootTitle;
	}

	public boolean isShowTitleInTree() {
		return showTitleInTree;
	}

}
