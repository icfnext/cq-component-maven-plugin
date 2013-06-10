package com.citytechinc.cq.component.dialog.maker.impl;

import java.util.Map;

import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.field.DialogFieldMember;
import com.citytechinc.cq.component.dialog.impl.PathFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class PathFieldWidgetMaker extends AbstractWidgetMaker {

	public DialogElement make(DialogFieldMember field, String xtype, boolean useDotSlashInName)
		throws ClassNotFoundException {

		PathField pathFieldAnnotation = field.getAnnotation(PathField.class);

		String name = getNameForField(field, useDotSlashInName);
		String fieldName = getFieldNameForField(field);
		String fieldLabel = getFieldLabelForField(field);
		String fieldDescription = getFieldDescriptionForField(field);
		Boolean isRequired = getIsRequiredForField(field);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(field);
		String defaultValue = getDefaultValueForField(field);
		boolean hideLabel = getHideLabelForField(field);

		boolean escapeAmp = getEscapeAmpForField(pathFieldAnnotation);
		boolean hideTrigger = getHideTriggerForField(pathFieldAnnotation);
		boolean parBrowse = getParBrowseForField(pathFieldAnnotation);
		String rootPath = getRootPathForField(pathFieldAnnotation);
		String rootTitle = getRootTitleForField(pathFieldAnnotation);
		boolean showTitleInTree = getShowTitleInTreeForField(pathFieldAnnotation);

		PathFieldWidget widget = new PathFieldWidget(escapeAmp, hideTrigger, parBrowse, rootPath, rootTitle,
			showTitleInTree, fieldLabel, fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName,
			additionalProperties);

		setListeners(widget, field.getAnnotation().listeners());

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
