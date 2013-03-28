package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

import org.codehaus.plexus.util.StringUtils;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.dialog.DialogElement;
import com.citytechinc.cq.component.dialog.exception.InvalidComponentFieldException;
import com.citytechinc.cq.component.dialog.maker.WidgetMaker;


public class WidgetFactory {

	public static final String TEXTFIELD_XTYPE = "textfield";
	public static final String NUMBERFIELD_XTYPE = "numberfield";
	public static final String PATHFIELD_XTYPE = "pathfield";
	public static final String SELECTION_XTYPE = "selection";
	public static final String MULTIFIELD_XTYPE = "multifield";
	public static final String HTML5SMARTIMAGE_XTYPE = "html5smartimage";

	public static DialogElement make(
			CtClass componentClass,
			CtField annotatedWidgetField,
			Field widgetField,
			Map<Class<?>, String> classToXTypeMap,
			Map<String, WidgetMaker> xTypeToWidgetMakerMap,
			ClassLoader classLoader,
			ClassPool classPool)
			throws InvalidComponentFieldException, ClassNotFoundException, CannotCompileException, NotFoundException {

		DialogField propertyAnnotation = (DialogField) annotatedWidgetField.getAnnotation(DialogField.class);

		if (propertyAnnotation == null) {
			throw new InvalidComponentFieldException();
		}

		String xtype = getXTypeForField(widgetField, annotatedWidgetField, propertyAnnotation, classToXTypeMap, classLoader, classPool);

		if (!xTypeToWidgetMakerMap.containsKey(xtype)) {
			throw new InvalidComponentFieldException("xType determined to be " + xtype + " but no Class implementing WidgetMaker is specified for this xtype");
		}

		Class<?> containingClass = classLoader.loadClass(componentClass.getName());

		return xTypeToWidgetMakerMap.get(xtype).make(xtype, widgetField, annotatedWidgetField, containingClass, componentClass, classToXTypeMap);

	}

	private static final String getXTypeForField(Field widgetField, CtField ctWidgetField, DialogField propertyAnnotation, Map<Class<?>, String> classToXTypeMap, ClassLoader classLoader, ClassPool classPool) throws InvalidComponentFieldException, CannotCompileException, NotFoundException, ClassNotFoundException {

		/*
		 * Handle annotated xtypes
		 *
		 * The xtype property on the DialogField annotation takes precedence over all other mechanisms
		 * of determining xtype.
		 */
		String overrideXType = propertyAnnotation.xtype();

		if (StringUtils.isNotEmpty(overrideXType)) {
			return overrideXType;
		}

		Class<?> fieldClass = widgetField.getType();

		/*
		 * Handle custom types.
		 *
		 * Custom types may be either Classes or Annotations.
		 */
		for (Class<?> curCustomClass : classToXTypeMap.keySet()) {
			if (curCustomClass.isAnnotation()) {

				if (ctWidgetField.hasAnnotation(curCustomClass)) {
					return classToXTypeMap.get(curCustomClass);
				}

			}
			else if (curCustomClass.isAssignableFrom(fieldClass)) {
				return classToXTypeMap.get(curCustomClass);
			}
		}

		/*
		 * Handle standard types
		 */

		/*
		 * numberfield
		 */
		if (
				Number.class.isAssignableFrom(fieldClass) ||
				fieldClass.equals(int.class) ||
				fieldClass.equals(double.class) ||
				fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 *  pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		/*
		 * selection
		 */
		if (fieldClass.isEnum()) {
			return SELECTION_XTYPE;
		}

		if (List.class.isAssignableFrom(fieldClass) || fieldClass.isArray()) {

			String simpleXtype = getInnerXTypeForField(widgetField, classToXTypeMap);

			/*
			 * TODO: This is where the multicompositefield would end up being selected once implemented
			 */
			if (simpleXtype == null) {
				throw new InvalidComponentFieldException("Parameterized class for List is not of a supported type.  Currently supported types are numbers, strings, and links");
			}

			return MULTIFIELD_XTYPE;

		}

		/*
		 * If we could not determine an xtype, return textfield
		 *
		 * TODO: Determine if this is appropriate or if this should throw an Exception
		 */
		return TEXTFIELD_XTYPE;
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
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 *  pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		return null;

	}
}
