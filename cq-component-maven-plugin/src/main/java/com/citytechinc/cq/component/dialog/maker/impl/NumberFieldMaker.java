package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.AccessibleObject;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMember;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.NumberField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.impl.NumberFieldWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class NumberFieldMaker extends AbstractWidgetMaker {

	@Override
	public DialogElement make(String xtype, AccessibleObject widgetField, CtMember ctWidgetField, Class<?> containingClass,
		CtClass ctContainingClass, Map<Class<?>, WidgetConfigHolder> xtypeMap,
		Map<String, WidgetMaker> xTypeToWidgetMakerMap, ClassLoader classLoader, ClassPool classPool,
		boolean useDotSlashInName) throws ClassNotFoundException, InvalidComponentFieldException,
		CannotCompileException, NotFoundException {
		NumberField numberFieldAnnotation = (NumberField) ctWidgetField.getAnnotation(NumberField.class);
		DialogField dialogFieldAnnotation = (DialogField) ctWidgetField.getAnnotation(DialogField.class);

		boolean allowDecimals = true;
		boolean allowNegative = true;
		int decimalPrecision = 2;
		String decimalSeparator = ".";
		if (numberFieldAnnotation != null) {
			allowDecimals = numberFieldAnnotation.allowDecimals();
			allowNegative = numberFieldAnnotation.allowNegative();
			decimalPrecision = numberFieldAnnotation.decimalPrecision();
			decimalSeparator = numberFieldAnnotation.decimalSeparator();
		}

		String name = getNameForField(dialogFieldAnnotation, widgetField, useDotSlashInName);
		String fieldName = getFieldNameForField(dialogFieldAnnotation, widgetField);
		String fieldLabel = getFieldLabelForField(dialogFieldAnnotation, widgetField);
		String fieldDescription = getFieldDescriptionForField(dialogFieldAnnotation);
		Boolean isRequired = getIsRequiredForField(dialogFieldAnnotation);
		Map<String, String> additionalProperties = getAdditionalPropertiesForField(dialogFieldAnnotation);
		String defaultValue = getDefaultValueForField(dialogFieldAnnotation);
		boolean hideLabel = dialogFieldAnnotation.hideLabel();

		return new NumberFieldWidget(allowDecimals, allowNegative, decimalPrecision, decimalSeparator, fieldLabel,
			fieldDescription, !isRequired, hideLabel, defaultValue, name, fieldName, additionalProperties);
	}

}
