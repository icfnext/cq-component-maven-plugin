package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.DateTimeWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class DateTimeWidgetMaker extends AbstractWidgetMaker{

	@Override
	public DialogElement make(String xtype, Field widgetField,
			CtField ctWidgetField, Class<?> containingClass,
			CtClass ctContainingClass, Map<Class<?>, String> xtypeMap)
			throws ClassNotFoundException, InvalidComponentFieldException,
			CannotCompileException, NotFoundException {
	
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		
		String name = getNameForField(dialogFieldAnnotation, widgetField);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel=dialogFieldAnnotation.hideLabel();
		
		return new DateTimeWidget(fieldLabel, fieldDescription, !isRequired, hideLabel,defaultValue, name, fieldName, additionalProperties);
	}

}
