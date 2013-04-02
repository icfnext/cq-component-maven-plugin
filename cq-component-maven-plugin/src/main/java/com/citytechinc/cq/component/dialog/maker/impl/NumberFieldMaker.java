package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.NumberFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class NumberFieldMaker extends AbstractWidgetMaker{

	@Override
	public DialogElement make(String xtype, Field widgetField,
			CtField ctWidgetField, Class<?> containingClass,
			CtClass ctContainingClass, Map<Class<?>, String> xtypeMap)
			throws ClassNotFoundException, InvalidComponentFieldException,
			CannotCompileException, NotFoundException {
		NumberField numberFieldAnnotation = (NumberField) ctWidgetField.getAnnotation(NumberField.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);
		
		boolean allowDecimals=true;
		boolean allowNegative=true;
		int decimalPrecision=2;
		String decimalSeparator=".";
		if(numberFieldAnnotation!=null){
			allowDecimals=numberFieldAnnotation.allowDecimals();
			allowNegative=numberFieldAnnotation.allowNegative();
			decimalPrecision=numberFieldAnnotation.decimalPrecision();
			decimalSeparator=numberFieldAnnotation.decimalSeparator();			
		}
		
		String name = getNameForField(dialogFieldAnnotation, widgetField);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		
		
		return new NumberFieldWidget(allowDecimals, allowNegative, decimalPrecision, decimalSeparator, fieldLabel, fieldDescription, !isRequired, defaultValue, name, fieldName, additionalProperties);
	}

}
