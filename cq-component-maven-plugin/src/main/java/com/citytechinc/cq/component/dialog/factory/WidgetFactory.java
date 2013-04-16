package com.citytechinc.cq.component.dialog.factory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.citytechinc.cq.component.maven.util.WidgetConfigHolder;

public class WidgetFactory {

	public static final String TEXTFIELD_XTYPE = "textfield";
	public static final String NUMBERFIELD_XTYPE = "numberfield";
	public static final String PATHFIELD_XTYPE = "pathfield";
	public static final String SELECTION_XTYPE = "selection";
	public static final String MULTIFIELD_XTYPE = "multifield";
	public static final String HTML5SMARTIMAGE_XTYPE = "html5smartimage";

	private WidgetFactory() {
	}

	public static DialogElement make(CtClass componentClass, CtField annotatedWidgetField, Field widgetField,
		Map<Class<?>, WidgetConfigHolder> classToXTypeMap, Map<String, WidgetMaker> xTypeToWidgetMakerMap,
		ClassLoader classLoader, ClassPool classPool, boolean useDotSlashInName, int rankingCeiling)
		throws InvalidComponentFieldException, ClassNotFoundException, CannotCompileException, NotFoundException,
		SecurityException, NoSuchFieldException {

		DialogField propertyAnnotation = (DialogField) annotatedWidgetField.getAnnotation(DialogField.class);

		if (propertyAnnotation == null) {
			throw new InvalidComponentFieldException();
		}

		String xtype = getXTypeForField(widgetField, annotatedWidgetField, propertyAnnotation, classToXTypeMap,
			classLoader, classPool, rankingCeiling);

		if (!xTypeToWidgetMakerMap.containsKey(xtype)) {
			throw new InvalidComponentFieldException("xType determined to be " + xtype
				+ " but no Class implementing WidgetMaker is specified for this xtype");
		}

		Class<?> containingClass = classLoader.loadClass(componentClass.getName());

		return xTypeToWidgetMakerMap.get(xtype).make(xtype, widgetField, annotatedWidgetField, containingClass,
			componentClass, classToXTypeMap, xTypeToWidgetMakerMap, classLoader, classPool, useDotSlashInName);

	}

	/**
	 * <p>
	 * Three mechanisms are employed in attempting to determine the xtype of a
	 * particular field.
	 * </p>
	 * 
	 * <ol>
	 * <li>Declared xtype: If the xtype property of the DialogField annotation
	 * is set, this xtype is used</li>
	 * <li>Mapped Classes: If an xtype is defined explicitly for the class of
	 * the field or if a stacked annotation is present to which an xtype is
	 * mapped, this xtype is used</li>
	 * <li>Guessed xtype: If mechanism 1 and 2 do not produce an xtype, a
	 * guessed xtype may be provided for a number of simple xtypes.</li>
	 * </ol>
	 * 
	 * <p>
	 * If none of these mechanisms produce an xtype, an
	 * InvalidComponentFieldException is thrown.
	 * </p>
	 * 
	 * <p>
	 * The following mapping is the basis for xtype guessing:
	 * </p>
	 * 
	 * <ul>
	 * <li>Numbers and primative numeric types -&gt; numberfield</li>
	 * <li>String -&gt; textfield</li>
	 * <li>URI and URL -&gt; pathfield</li>
	 * <li>Enum -&gt; selection</li>
	 * <li>List and Array -&gt; multifield</li>
	 * </ul>
	 * 
	 * @param widgetField
	 * @param ctWidgetField
	 * @param propertyAnnotation
	 * @param classToXTypeMap
	 * @param classLoader
	 * @param classPool
	 * @return
	 * @throws InvalidComponentFieldException
	 * @throws CannotCompileException
	 * @throws NotFoundException
	 * @throws ClassNotFoundException
	 */
	private static final String getXTypeForField(Field widgetField, CtField ctWidgetField,
		DialogField propertyAnnotation, Map<Class<?>, WidgetConfigHolder> classToXTypeMap, ClassLoader classLoader,
		ClassPool classPool, int rankingCeiling) throws InvalidComponentFieldException, CannotCompileException,
		NotFoundException, ClassNotFoundException {

		/*
		 * Handle annotated xtypes
		 * 
		 * The xtype property on the DialogField annotation takes precedence
		 * over all other mechanisms of determining xtype.
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
		Set<WidgetConfigHolder> possibleMatches = new HashSet<WidgetConfigHolder>();
		for (Class<?> curCustomClass : classToXTypeMap.keySet()) {
			if (curCustomClass.isAnnotation()) {

				if (ctWidgetField.hasAnnotation(curCustomClass)) {
					WidgetConfigHolder widget = classToXTypeMap.get(curCustomClass);
					if (isBelowRankingCeiling(rankingCeiling, widget)) {
						possibleMatches.add(widget);
					}
				}

			} else if (curCustomClass.isAssignableFrom(fieldClass)) {
				WidgetConfigHolder widget = classToXTypeMap.get(curCustomClass);
				if (isBelowRankingCeiling(rankingCeiling, widget)) {
					possibleMatches.add(widget);
				}
			}
		}
		if (possibleMatches.size() > 0) {
			WidgetConfigHolder match = null;
			for (WidgetConfigHolder possibleMatch : possibleMatches) {
				if (match == null || match.getRanking() < possibleMatch.getRanking()) {
					match = possibleMatch;
				}
			}
			return match.getXtype();
		}

		/*
		 * Handle standard types
		 */

		/*
		 * numberfield
		 */
		if (Number.class.isAssignableFrom(fieldClass) || fieldClass.equals(int.class)
			|| fieldClass.equals(double.class) || fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 * pathfield
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

			if (simpleXtype == null) {
				throw new InvalidComponentFieldException(
					"Parameterized class for List is not of a supported type.  Currently supported types are numbers, strings, and links");
			}

			return MULTIFIELD_XTYPE;

		}

		/*
		 * If we could not determine an xtype, throw an exception
		 */
		throw new InvalidComponentFieldException("An xtype could not be determined for the field "
			+ widgetField.getName());
	}

	private static final String getInnerXTypeForField(Field widgetField, Map<Class<?>, WidgetConfigHolder> xtypeMap)
		throws InvalidComponentFieldException {

		Class<?> fieldClass = widgetField.getType();

		if (List.class.isAssignableFrom(fieldClass)) {
			return getInnerXTypeForListField(widgetField, xtypeMap);
		}
		if (fieldClass.isArray()) {
			return getInnerXTypeForArrayField(widgetField, xtypeMap);
		}

		throw new InvalidComponentFieldException(
			"List dialog property found with a paramaterized type count not equal to 1");

	}

	private static final String getInnerXTypeForListField(Field widgetField, Map<Class<?>, WidgetConfigHolder> xtypeMap)
		throws InvalidComponentFieldException {
		ParameterizedType parameterizedType = (ParameterizedType) widgetField.getGenericType();

		if (parameterizedType.getActualTypeArguments().length == 0
			|| parameterizedType.getActualTypeArguments().length > 1) {
			throw new InvalidComponentFieldException(
				"List dialog property found with a paramaterized type count not equal to 1");
		}

		String simpleXtype = getSimpleXTypeForClass((Class<?>) parameterizedType.getActualTypeArguments()[0], xtypeMap);

		return simpleXtype;
	}

	private static final String getInnerXTypeForArrayField(Field widgetField, Map<Class<?>, WidgetConfigHolder> xtypeMap) {
		Class<?> fieldClass = widgetField.getType();

		return getSimpleXTypeForClass(fieldClass.getComponentType(), xtypeMap);
	}

	private static final String getSimpleXTypeForClass(Class<?> fieldClass, Map<Class<?>, WidgetConfigHolder> xtypeMap) {

		/*
		 * Handle custom types
		 */
		for (Class<?> curCustomClass : xtypeMap.keySet()) {
			if (curCustomClass.isAssignableFrom(fieldClass)) {
				return xtypeMap.get(curCustomClass).getXtype();
			}
		}

		/*
		 * numberfield
		 */
		if (Number.class.isAssignableFrom(fieldClass) || fieldClass.equals(int.class)
			|| fieldClass.equals(double.class) || fieldClass.equals(float.class)) {
			return NUMBERFIELD_XTYPE;
		}

		/*
		 * textfield
		 */
		if (fieldClass.equals(String.class)) {
			return TEXTFIELD_XTYPE;
		}

		/*
		 * pathfield
		 */
		if (URI.class.isAssignableFrom(fieldClass) || URL.class.isAssignableFrom(fieldClass)) {
			return PATHFIELD_XTYPE;
		}

		return null;

	}

	private static final boolean isBelowRankingCeiling(int rankingCeiling, WidgetConfigHolder widget) {
		if (rankingCeiling > 0 && widget.getRanking() >= rankingCeiling) {
			return false;
		} else {
			return true;
		}
	}
}
