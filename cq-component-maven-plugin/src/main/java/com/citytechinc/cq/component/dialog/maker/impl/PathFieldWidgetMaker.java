package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.PathFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class PathFieldWidgetMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException {
		PathField pathFieldAnnotation = (PathField) ctWidgetField.getAnnotation(PathField.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		boolean escapeAmp = false;
		boolean hideTrigger = false;
		boolean parBrowse = false;
		String rootPath = "/";
		String rootTitle = "Websites";
		boolean showTitleInTree = true;
		if (pathFieldAnnotation != null) {
			escapeAmp = pathFieldAnnotation.escapeAmp();
			hideTrigger = pathFieldAnnotation.hideTrigger();
			parBrowse = pathFieldAnnotation.parBrowse();
			rootPath = pathFieldAnnotation.rootPath();
			rootTitle = pathFieldAnnotation.rootTitle();
			showTitleInTree = pathFieldAnnotation.showTitleInTree();
		}

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		return new PathFieldWidget(escapeAmp, hideTrigger, parBrowse, rootPath, rootTitle, showTitleInTree, fieldLabel,
			fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName, additionalProperties);
	}

}
