package com.citytechinc.cq.component.dialog.maker.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.FieldConfig;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.factory.WidgetFactory;
import com.citytechinc.cq.component.dialog.impl.BasicFieldConfig;

import com.citytechinc.cq.component.dialog.impl.MultiValueWidget;
import com.citytechinc.cq.component.dialog.maker.AbstractWidgetMaker;

public class MultifieldWidgetMaker extends AbstractWidgetMaker{

	@Override
	public DialogElement make(String xtype, Field widgetField, CtField ctWidgetField,
			Class<?> containingClass, CtClass ctContainingClass, Map<Class<?>, String> xtypeMap)
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

		String innerXType = getInnerXTypeForMultiField(widgetField, dialogFieldAnnotation, xtypeMap);

		if (innerXType == null) {
			throw new InvalidComponentFieldException("Invalid or unsupported field annotation on a multi valued field");
		}

		BasicFieldConfig fieldConfig = new BasicFieldConfig(innerXType, null);

		return new MultiValueWidget(
				WidgetFactory.MULTIFIELD_XTYPE,
				name,
				fieldName,
				fieldLabel,
				fieldDescription,
				isRequired,
				defaultValue,
				additionalProperties,
				fieldConfig);

	}

	private static final String getInnerXTypeForMultiField(Field widgetField, DialogField fieldAnnotation, Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {

		List<FieldConfig> fieldConfigs = Arrays.asList(fieldAnnotation.fieldConfigs());

		/*
		 * Check to see if a field configuration annotation is present.  If so, use that
		 */
		if (!fieldConfigs.isEmpty()) {
			return fieldConfigs.get(0).xtype();
		}

		/*
		 * Otherwise, see if we can derive the xtype from the parameterized field
		 */
		String innerXType = getInnerXTypeForField(widgetField, xtypeMap);

		if (innerXType != null) {
			return innerXType;
		}

		return null;
	}

	private static final String getInnerXTypeForField(Field widgetField, Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {

		Class<?> fieldClass = widgetField.getType();

		if (List.class.isAssignableFrom(fieldClass)) {
			return getInnerXTypeForListField(widgetField, xtypeMap);
		}
		if (fieldClass.isArray()) {
			return getInnerXTypeForArrayField(widgetField, xtypeMap);
		}

		throw new InvalidComponentFieldException("List dialog property found with a paramaterized type count not equal to 1");

	}

	private static final String getInnerXTypeForListField(Field widgetField, Map<Class<?>, String> xtypeMap) throws InvalidComponentFieldException {
		ParameterizedType parameterizedType = (ParameterizedType) widgetField.getGenericType();

		if (parameterizedType.getActualTypeArguments().length == 0 || parameterizedType.getActualTypeArguments().length > 1) {
			throw new InvalidComponentFieldException("List dialog property found with a paramaterized type count not equal to 1");
		}

		String simpleXtype = getSimpleXTypeForClass((Class<?>) parameterizedType.getActualTypeArguments()[0], xtypeMap);

		return simpleXtype;
	}

	private static final String getInnerXTypeForArrayField(Field widgetField, Map<Class<?>, String> xtypeMap) {
		Class<?> fieldClass = widgetField.getType();

		return getSimpleXTypeForClass(fieldClass.getComponentType(), xtypeMap);
	}

	private static final String getSimpleXTypeForClass(Class<?> fieldClass, Map<Class<?>, String> xtypeMap) {

		/*
		 * Handle custom types
		 */
		for (Class<?> curCustomClass : xtypeMap.keySet()) {
			if (curCustomClass.isAssignableFrom(fieldClass)) {
				return xtypeMap.get(curCustomClass);
			}
		}

		/*
		 * numberfield
		 */
		if (
				Number.class.isAssignableFrom(fieldClass) ||
				fieldClass.equals(int.class) ||
				fieldClass.equals(double.class) ||
				fieldClass.equals(float.class)) {
			return WidgetFactory.NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return WidgetFactory.TEXTFIELD_XTYPE;
		}

		/*
		 *  pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return WidgetFactory.PATHFIELD_XTYPE;
		}

		return null;

	}

}
