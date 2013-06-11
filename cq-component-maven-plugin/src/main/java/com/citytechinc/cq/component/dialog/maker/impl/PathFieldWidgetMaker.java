package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.impl.PathFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class PathFieldWidgetMaker extends AbstractWidgetMaker {

	public PathFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}

	public DialogElement make() throws ClassNotFoundException {

		PathField pathFieldAnnotation = getAnnotation(PathField.class);

		String name = getNameForField();
		String fieldName = getFieldNameForField();
		String fieldLabel = getFieldLabelForField();
		String fieldDescription = getFieldDescriptionForField();
		Boolean isRequired = getIsRequiredForField();
		Map<String, String> additionalProperties = getAdditionalPropertiesForField();
		String defaultValue = getDefaultValueForField();
		boolean hideLabel = getHideLabelForField();

		boolean escapeAmp = getEscapeAmpForField(pathFieldAnnotation);
		boolean hideTrigger = getHideTriggerForField(pathFieldAnnotation);
		boolean parBrowse = getParBrowseForField(pathFieldAnnotation);
		String rootPath = getRootPathForField(pathFieldAnnotation);
		String rootTitle = getRootTitleForField(pathFieldAnnotation);
		boolean showTitleInTree = getShowTitleInTreeForField(pathFieldAnnotation);

		PathFieldWidget widget = new PathFieldWidget(escapeAmp, hideTrigger, parBrowse, rootPath, rootTitle,
			showTitleInTree, fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName,
			additionalProperties);

		setListeners(widget);

		return widget;

	}

	protected boolean getEscapeAmpForField(PathField annotation) {
		if (annotation != null) {
			return annotation.escapeAmp();
		}

		return PathField.ESCAPE_AMP_DEFAULT;
	}

	protected boolean getHideTriggerForField(PathField annotation) {
		if (annotation != null) {
			return annotation.hideTrigger();
		}

		return PathField.HIDE_TRIGGER_DEFAULT;
	}

	protected boolean getParBrowseForField(PathField annotation) {
		if (annotation != null) {
			return annotation.parBrowse();
		}

		return PathField.PAR_BROWSE_DEFAULT;
	}

	protected String getRootPathForField(PathField annotation) {
		if (annotation != null) {
			return annotation.rootPath();
		}

		return PathField.ROOT_PATH_DEFAULT;
	}

	protected String getRootTitleForField(PathField annotation) {
		if (annotation != null) {
			return annotation.rootTitle();
		}

		return PathField.ROOT_TITLE_DEFAULT;
	}

	protected boolean getShowTitleInTreeForField(PathField annotation) {
		if (annotation != null) {
			return annotation.showTitleInTree();
		}

		return PathField.SHOW_TITLE_IN_TREE_DEFAULT;
	}

}
