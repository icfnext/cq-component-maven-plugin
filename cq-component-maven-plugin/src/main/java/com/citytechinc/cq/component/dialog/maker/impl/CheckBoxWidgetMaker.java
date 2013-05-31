package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.CheckBox;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.CheckBoxWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class CheckBoxWidgetMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField,
		Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException {

		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		CheckBox checkBoxAnnotation = (CheckBox) ctWidgetField.getAnnotation(CheckBox.class);
		String inputValue = checkBoxAnnotation.inputValue();
		boolean checked = checkBoxAnnotation.checked();

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		CheckBoxWidget widget= new CheckBoxWidget(inputValue, checked, fieldLabel, fieldDescription, !isRequired, hideLabel,
			defaultValue, name, fieldName, additionalProperties);
		
		setListeners(widget,dialogFieldAnnotation.listeners());
		
		return widget;
	}

}
