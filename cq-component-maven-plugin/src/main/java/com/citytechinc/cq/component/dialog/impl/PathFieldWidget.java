package com.citytechinc.cq.component.dialog.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.config.Widget;
import com.citytechinc.cq.component.dialog.AbstractWidget;

@Widget(annotationClass = "com.citytechinc.cq.component.annotations.widgets.PathField", makerClass = "com.citytechinc.cq.component.dialog.maker.impl.PathFieldWidgetMaker", xtypes = PathFieldWidget.XTYPE)
public class PathFieldWidget extends AbstractWidget {
	public static final String XTYPE = "pathfield";
	private static final String PRIMARY_TYPE = "cq:Widget";

	private boolean escapeAmp;
	private boolean hideTrigger;
	private boolean parBrowse;
	private String rootPath;
	private String rootTitle;
	private boolean showTitleInTree;

	public PathFieldWidget(boolean escapeAmp, boolean hideTrigger, boolean parBrowse, String rootPath,
		String rootTitle, boolean showTitleInTree, String fieldLabel, String fieldDescription, boolean allowBlank,
		boolean hideLabel, String defaultValue, String name, String fieldName, Map<String, String> additionalProperties) {
		super(XTYPE, fieldLabel, fieldDescription, allowBlank, hideLabel, defaultValue, name, PRIMARY_TYPE, null,
			fieldName, additionalProperties, null);
		this.escapeAmp = escapeAmp;
		this.hideTrigger = hideTrigger;
		this.parBrowse = parBrowse;
		this.rootPath = rootPath;
		this.rootTitle = rootTitle;
		this.showTitleInTree = showTitleInTree;
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
