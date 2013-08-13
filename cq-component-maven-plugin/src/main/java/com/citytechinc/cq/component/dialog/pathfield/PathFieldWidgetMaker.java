package com.citytechinc.cq.component.dialog.pathfield;

import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMakerParameters;

public class PathFieldWidgetMaker extends AbstractWidgetMaker {

	public PathFieldWidgetMaker(WidgetMakerParameters parameters) {
		super(parameters);
	}

	public DialogElement make() throws ClassNotFoundException {

		PathField pathFieldAnnotation = getAnnotation(PathField.class);
		PathFieldWidgetParameters parameters = new PathFieldWidgetParameters();

		parameters.setName(getNameForField());
		parameters.setFieldName(getFieldNameForField());
		parameters.setFieldLabel(getFieldLabelForField());
		parameters.setFieldDescription(getFieldDescriptionForField());
		parameters.setAllowBlank(!getIsRequiredForField());
		parameters.setDefaultValue(getDefaultValueForField());
		parameters.setHideLabel(getHideLabelForField());
		parameters.setListeners(getListeners());

		parameters.setEscapeAmp(getEscapeAmpForField(pathFieldAnnotation));
		parameters.setHideTrigger(getHideTriggerForField(pathFieldAnnotation));
		parameters.setParBrowse(getParBrowseForField(pathFieldAnnotation));
		parameters.setRootPath(getRootPathForField(pathFieldAnnotation));
		parameters.setRootTitle(getRootTitleForField(pathFieldAnnotation));
		parameters.setShowTitleInTree(getShowTitleInTreeForField(pathFieldAnnotation));

		return new PathFieldWidget(parameters);

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
